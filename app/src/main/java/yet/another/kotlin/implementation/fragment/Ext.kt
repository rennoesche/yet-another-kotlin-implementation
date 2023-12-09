package yet.another.kotlin.implementation.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import yet.another.kotlin.implementation.adapter.UserAdapter
import yet.another.kotlin.implementation.databinding.FragmentExtBinding
import yet.another.kotlin.implementation.room.User
import yet.another.kotlin.implementation.vm.ExtViewModel

class Ext : Fragment() {

    private var _binding: FragmentExtBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ExtViewModel
    private lateinit var userAdapter: UserAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExtBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userAdapter = UserAdapter(object : UserAdapter.OnItemAction {
            override fun onItemClick(data: User) {
                val dialog = ExtDialog()

                val args = Bundle()
                args.putParcelable(ExtDialog.user_x, data)
                dialog.arguments = args

                dialog.show(parentFragmentManager, "ExtDialog")
            }
        })

        viewModel = ViewModelProvider(this).get(ExtViewModel::class.java)

        binding.recyclerViewExt.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }

        viewModel.user.observe(viewLifecycleOwner) {
            userAdapter.setData(it)
        }

        viewModel.ambilUser(requireContext())

        binding.btnAddUser.setOnClickListener {
            showDialog()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100) {
            viewModel.ambilUser(requireContext())
        }
    }
    fun showDialog() {
        val fragmentManager = parentFragmentManager
        val newFragment = ExtDialog()
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction
            .add(android.R.id.content, newFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
