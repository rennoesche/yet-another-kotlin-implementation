package yet.another.kotlin.implementation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.core.view.WindowCompat

class Splash : AppCompatActivity() {
    private lateinit var splashTextView: TextView
    private val textToType = "Yet Another Kotlin Implementation!"
    private val typingSpeed: Long = 100 // Waktu tunggu antara setiap karakter (dalam milidetik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        @LayoutRes
        fun getLayoutResourceId(): Int {
            return yet.another.kotlin.implementation.R.layout.activity_splash
        }

        setContentView(getLayoutResourceId())

        splashTextView = findViewById(R.id.typingTextView)

        // Mulai animasi mengetik
        startTypingAnimation()
    }

    private fun startTypingAnimation() {
        val handler = Handler(Looper.getMainLooper())

        val charArray = textToType.toCharArray()
        var currentIndex = 0

        handler.post(object : Runnable {
            override fun run() {
                if (currentIndex <= charArray.size - 1) {
                    splashTextView.append(charArray[currentIndex].toString())
                    currentIndex++
                    handler.postDelayed(this, typingSpeed)
                } else {
                    // Animasi selesai, beralih ke MainActivity
                    startActivity(Intent(this@Splash, MainActivity::class.java))
                    finish()
                }
            }
        })
    }
}