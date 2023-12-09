package yet.another.kotlin.implementation


import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import yet.another.kotlin.implementation.databinding.ActivityMainBinding
import yet.another.kotlin.implementation.fragment.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavBar = binding.bottomNavBar
        bottomNavBar.menu.findItem(R.id.nav_yak).setIcon(R.drawable.nav_yak_s)
        replaceFragment(Yak())
        bottomNavBar.setOnItemSelectedListener { menuItem ->
            resetAllIcons(bottomNavBar.menu)
            when (menuItem.itemId) {
                R.id.nav_yak -> {
                    menuItem.setIcon(R.drawable.nav_yak_s)
                    replaceFragment(Yak())
                    true
                }

                R.id.nav_ext -> {
                    menuItem.setIcon(R.drawable.nav_ext_s)
                    replaceFragment(Ext())
                    true
                }

                R.id.nav_timer -> {
                    menuItem.setIcon(R.drawable.nav_timer_s)
                    replaceFragment(Timer())
                    true
                }

                R.id.nav_profil -> {
                    menuItem.setIcon(R.drawable.nav_usr_s)
                    replaceFragment(Usr())
                    true
                }

                else -> false
            }
        }
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
    private fun resetAllIcons(menu: Menu) {
        for (i in 0 until menu.size()) {
            val menuItem = menu.getItem(i)
            when (menuItem.itemId) {
                R.id.nav_yak -> menuItem.setIcon(R.drawable.nav_yak)
                R.id.nav_ext -> menuItem.setIcon(R.drawable.nav_ext)
                R.id.nav_timer -> menuItem.setIcon(R.drawable.nav_timer)
                R.id.nav_profil -> menuItem.setIcon(R.drawable.nav_usr)
            }
        }
    }
}

