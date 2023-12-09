package yet.another.kotlin.implementation.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import yet.another.kotlin.implementation.R
import yet.another.kotlin.implementation.databinding.FragmentTimerBinding

class Timer : Fragment() {

    private var _binding: FragmentTimerBinding? = null
    private val binding get() = _binding!!

    private lateinit var timer: CountDownTimer
    private val timerDuration: Long
        get() {
            val minutes = binding.minutePicker.value
            val seconds = binding.secondPicker.value
            return (minutes * 60 + seconds) * 1000L
        }
    private var isTimerRunning = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTimerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTimerPicker()

        binding.startButton.setOnClickListener {
            startTimer()
        }

        binding.stopButton.setOnClickListener {
            stopTimer()
        }
    }

    private fun setupTimerPicker() {
        // Setup NumberPicker for minutes
        binding.minutePicker.maxValue = 59
        binding.minutePicker.minValue = 0

        // Setup NumberPicker for seconds
        binding.secondPicker.maxValue = 59
        binding.secondPicker.minValue = 0
    }

    private fun startTimer() {
        if (!isTimerRunning) {
            val minutes = binding.minutePicker.value
            val seconds = binding.secondPicker.value

            val totalMillis = (minutes * 60 + seconds) * 1000L

            timer = object : CountDownTimer(totalMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    updateTimerText(millisUntilFinished)
                }

                override fun onFinish() {
                    showPopup()
                    resetTimer()
                }
            }

            timer.start()
            isTimerRunning = true
        }
    }

    private fun stopTimer() {
        if (isTimerRunning) {
            timer.cancel()
            resetTimer()
        }
    }

    private fun resetTimer() {
        isTimerRunning = false
        binding.minutePicker.value = 0
        binding.secondPicker.value = 0
    }

    private fun updateTimerText(millisUntilFinished: Long) {
        val seconds = millisUntilFinished / 1000
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
    }

    private fun showPopup() {
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.timer_popup, null)

        AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
