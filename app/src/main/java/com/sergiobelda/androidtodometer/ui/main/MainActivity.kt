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
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import com.google.android.material.snackbar.Snackbar
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MainActivityBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * [AppCompatActivity] Main Activity.
 * Contains the NavHostFragment and listens the destinations changes.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()
        setBottomAppBar()
        binding.createButton.setOnClickListener {
            navController().navigate(R.id.addTaskFragment)
        }
    }

    private fun setBottomAppBar() {
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
        navController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment -> {
                    binding.bottomAppBar.visibility = View.VISIBLE
                    mainViewModel.projects.observe(this) {
                        if (!it.isNullOrEmpty()) {
                            binding.createButton.show()
                        } else {
                            binding.createButton.hide()
                        }
                    }
                }
                else -> {
                    binding.bottomAppBar.visibility = View.GONE
                    binding.createButton.hide()
                    mainViewModel.projects.removeObservers(this)
                }
            }
        }
    }

    private fun showMoreOptions() =
        navController().navigate(R.id.moreBottomSheetDialog)

    private fun showMenu() =
        navController().navigate(R.id.menuDialog)

    fun showSnackbar(text: String) {
        Snackbar.make(binding.coordinatorLayout, text, Snackbar.LENGTH_LONG)
            .setAnchorView(binding.createButton)
            .show()
    }

    private fun navController() = findNavController(R.id.nav_host_fragment)
}
