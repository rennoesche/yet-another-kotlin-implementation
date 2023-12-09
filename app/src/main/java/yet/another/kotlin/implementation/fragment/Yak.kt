package yet.another.kotlin.implementation.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import yet.another.kotlin.implementation.R
import yet.another.kotlin.implementation.databinding.FragmentYakBinding

class Yak : Fragment() {

    private var _binding: FragmentYakBinding? = null
    private val binding get() = _binding!!

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentYakBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
        val topAppBar = binding.topAppBar
        if (sharedPreferences.contains(KEY_NAME)) {
            topAppBar.visibility = View.VISIBLE
            topAppBar.setOnMenuItemClickListener { item ->
                onOptionsItemSelected(item)
            }
        } else {
            topAppBar.visibility = View.GONE
        }
        binding.buttonSave.setOnClickListener {
            saveName()
        }

        loadName()
    }

    private fun saveName() {
        val name = binding.editTextName.text.toString().trim()
        if (name.isNotEmpty()) {
            editor.putString(KEY_NAME, name)
            editor.apply()
            loadName()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.hapus -> {
                clearData()
                binding.editTextName.visibility = View.VISIBLE
                binding.buttonSave.visibility = View.VISIBLE
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun loadName() {
        val savedName = sharedPreferences.getString(KEY_NAME, "")

        if (savedName?.isNotEmpty() == true) {
            binding.textGreeting.text = getString(R.string.greeting_message, savedName)
            binding.editTextName.visibility = View.GONE
            binding.buttonSave.visibility = View.GONE
        } else {
            binding.textGreeting.text = getString(R.string.default_greeting)
            binding.editTextName.visibility = View.VISIBLE
            binding.buttonSave.visibility = View.VISIBLE
        }
    }
    private fun clearData() {
        editor.clear()
        editor.apply()
        binding.textGreeting.text = getString(R.string.default_greeting)
    }

    companion object {
        const val KEY_NAME = "user_name"
    }
}
