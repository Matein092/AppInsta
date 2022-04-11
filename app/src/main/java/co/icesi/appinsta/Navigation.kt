package co.icesi.appinsta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import co.icesi.appinsta.databinding.ActivityNavigationBinding

class Navigation : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding

    private lateinit var navigationFragment: NavigationFragment
    private lateinit var addPhoto: AddPhoto
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationFragment = NavigationFragment.newInstance()
        profileFragment = ProfileFragment.newInstance()
        addPhoto = AddPhoto.newInstance()

        showFragment(navigationFragment)

        binding.menu.setOnItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.home) {
                showFragment(navigationFragment)
            } else if (menuItem.itemId == R.id.publish) {
                showFragment(addPhoto)
            } else if (menuItem.itemId == R.id.profile) {
                showFragment(profileFragment)
            }
            true
        }

    }

    fun showFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.posts, fragment)
        transaction.commit()
    }
}