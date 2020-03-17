package com.sergiobelda.androidtodometer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MainActivityBinding
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    private val mainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    private var dialog = MenuBottomSheetDialogFragment(R.menu.main_menu) {
        onMainMenuItemSelected(it.itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.bottomAppBar)
        setNavigation()
    }

    private fun setNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment -> {
                    binding.bottomAppBar.navigationIcon = getDrawable(R.drawable.ic_menu_24dp)
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.createButton.setImageDrawable(getDrawable(R.drawable.ic_add_24dp))
                    binding.createButton.setOnClickListener {
                        navController.navigate(R.id.addToDoFragment)
                    }
                    binding.appBarLayout.setExpanded(true)
                }
                R.id.projectsFragment -> {
                    binding.bottomAppBar.navigationIcon = getDrawable(R.drawable.ic_menu_24dp)
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.createButton.setImageDrawable(getDrawable(R.drawable.ic_add_24dp))
                    binding.createButton.setOnClickListener {
                        navController.navigate(R.id.addToDoFragment)
                    }
                    binding.appBarLayout.setExpanded(true)
                }
                R.id.addToDoFragment -> {
                    binding.bottomAppBar.navigationIcon = null
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                    binding.createButton.setImageDrawable(getDrawable(R.drawable.ic_check_24dp))
                    binding.appBarLayout.setExpanded(false)
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                showMenu()
                true
            }
            else -> false
        }
    }

    private fun showMenu() {
        if (dialog.isAdded) return
        dialog.show(supportFragmentManager, null)
    }

    private fun onMainMenuItemSelected(itemId: Int): Boolean {
        return when (itemId) {
            R.id.tasks -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.tasksFragment)
                true
            }
            R.id.projects -> {
                findNavController(R.id.nav_host_fragment).navigate(R.id.projectsFragment)
                true
            }
            else -> {
                false
            }
        }
    }

    fun showSnackbar(text: String) {
        Snackbar.make(binding.coordinatorLayout, text, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.createButton)
            .show()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
