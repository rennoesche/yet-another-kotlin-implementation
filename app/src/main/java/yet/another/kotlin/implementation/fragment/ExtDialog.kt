package yet.another.kotlin.implementation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import yet.another.kotlin.implementation.databinding.FragmentExtDialogBinding
import yet.another.kotlin.implementation.room.*
import yet.another.kotlin.implementation.vm.*

class ExtDialog : DialogFragment() {
    private var _binding: FragmentExtDialogBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ExtDialogViewModel

    private var isEdit = false
    private var data: User? = null

    companion object {
        const val user_x = "user"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExtDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        data = arguments?.getParcelable(user_x)

        if (data != null) {
            isEdit = true
            binding.hapus.visibility = View.VISIBLE
            binding.inputLayoutNama.editText?.setText(data?.nama)
            binding.inputLayoutAsal.editText?.setText(data?.asal)
        } else {
            isEdit = false
            binding.hapus.visibility = View.GONE
        }

        viewModel = ViewModelProvider(this)[ExtDialogViewModel::class.java]

        binding.simpan.setOnClickListener {
            nambahUser()
        }

        binding.hapus.setOnClickListener {
            data?.let {
                viewModel.deleteUser(requireContext(), it) {
                    Toast.makeText(requireContext(), "Berhasil Menghapus User", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            }
        }
    }

    private fun nambahUser() {
        if (binding.inputLayoutNama.editText?.text.toString().isEmpty() || binding.inputLayoutNama.editText?.text.toString().isEmpty()) {
            Toast.makeText(requireContext(), "Isi Form yang tersedia", Toast.LENGTH_SHORT).show()
        } else {
            if (isEdit) {
                data?.let {
                    viewModel.updateUser(requireContext(), it.id, binding.inputLayoutNama.editText?.text.toString(), binding.inputLayoutAsal.editText?.text.toString()) {
                        suksesNotif()
                    }
                }
            } else {
                viewModel.nambahUser(requireContext(), binding.inputLayoutNama.editText?.text.toString(), binding.inputLayoutAsal.editText?.text.toString()) {
                    suksesNotif()
                }
            }
        }
    }

    private fun suksesNotif() {
        Toast.makeText(requireContext(), "Berhasil Menyimpan User", Toast.LENGTH_SHORT).show()
        dismiss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
