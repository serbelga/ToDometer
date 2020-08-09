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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MainActivityBinding
import com.sergiobelda.androidtodometer.ui.project.ProjectsFragmentDirections
import com.sergiobelda.androidtodometer.ui.task.TasksFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * [AppCompatActivity] Main Activity
 * Contains the a NavHostFragment and listens the destinations changes.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private var dialog = MenuBottomSheetDialogFragment(R.menu.main_menu)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        binding.bottomAppBar.setNavigationOnClickListener {
            showMenu()
        }
        binding.bottomAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.more -> {
                    showMoreOptions()
                    true
                }
                else -> false
            }
        }
    }

    private fun setNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment -> {
                    binding.bottomAppBar.performShow()
                    binding.createButton.show()
                    binding.createButton.setOnClickListener {
                        val action = TasksFragmentDirections.navToAddTask()
                        navController.navigate(action)
                    }
                }
                R.id.projectsFragment -> {
                    binding.bottomAppBar.performShow()
                    binding.createButton.show()
                    binding.createButton.setOnClickListener {
                        val action = ProjectsFragmentDirections.navToAddProject()
                        navController.navigate(action)
                    }
                }
                else -> {
                    binding.bottomAppBar.performHide()
                    binding.createButton.hide()
                }
            }
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
