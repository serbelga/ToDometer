/*
 * Copyright 2020 Sergio Belda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sergiobelda.androidtodometer.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MainActivityBinding

/**
 * [AppCompatActivity] Main Activity
 * Contains the a NavHostFragment and listens the destinations changes.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private var dialog = MenuBottomSheetDialogFragment(R.menu.main_menu)

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bottomAppBar)
        setNavigation()

        setClickListeners()
    }

    private fun setClickListeners() {
        binding.bottomMenuButton.setOnClickListener {
            showMenu()
        }
        binding.createButton.setOnClickListener {
            val navController = findNavController(R.id.nav_host_fragment)
            navController.navigate(R.id.addToDoFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_app_bar_menu, menu)
        return true
    }

    private fun setNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.projectsFragment, R.id.tasksFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment -> {
                    supportActionBar?.show()
                    binding.createButton.show()
                    binding.bottomMenuButton.text = getString(R.string.tasks)
                }
                R.id.projectsFragment -> {
                    supportActionBar?.show()
                    binding.createButton.show()
                    binding.bottomMenuButton.text = getString(R.string.projects)
                }
                else -> {
                    supportActionBar?.hide()
                    binding.createButton.hide()
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
            R.id.more -> {
                showMoreOptions()
                true
            }
            else -> false
        }
    }

    private fun showMoreOptions() {
        val dialog =
            MoreBottomSheetDialogFragment()
        dialog.show(
            supportFragmentManager,
            TAG
        )
    }

    private fun showMenu() {
        if (dialog.isAdded) return
        dialog.show(supportFragmentManager, null)
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
