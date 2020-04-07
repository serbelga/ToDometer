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

package com.sergiobelda.androidtodometer.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.snackbar.Snackbar
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MainActivityBinding
import com.sergiobelda.androidtodometer.model.TaskState
import com.sergiobelda.androidtodometer.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * [AppCompatActivity] Main Activity
 * Contains the a NavHostFragment and listens the destinations changes
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding

    private val mainViewModel by viewModel<MainViewModel>()

    private var dialog = MenuBottomSheetDialogFragment(R.menu.main_menu) {
        onMainMenuItemSelected(it.itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bottomAppBar)
        setNavigation()

        mainViewModel.projectTaskListingList.observe(this, Observer { list ->
            val doneCount = list.filter { it.task.taskState == TaskState.DONE }.size
            val progress = ((doneCount.toDouble() / list.size.toDouble()) * 100).toInt()
            binding.progressBar.progress = progress
            binding.progressTextView.text = "$progress%"
        })
    }

    private fun setNavigation() {
        val navController = findNavController(R.id.nav_host_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.tasksFragment, R.id.projectsFragment -> {
                    binding.bottomAppBar.navigationIcon = getDrawable(R.drawable.ic_menu_24dp)
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.createButton.setImageDrawable(getDrawable(R.drawable.ic_add_24dp))
                    binding.createButton.setOnClickListener {
                        navController.navigate(R.id.addToDoFragment)
                    }
                    binding.appBarLayout.setExpanded(true)
                }
                R.id.taskFragment, R.id.projectFragment -> {
                    binding.bottomAppBar.navigationIcon = null
                    binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                    binding.createButton.setImageDrawable(getDrawable(R.drawable.ic_create_24dp))
                    binding.appBarLayout.setExpanded(false)
                }
                R.id.addToDoFragment, R.id.editTaskFragment, R.id.editProjectFragment -> {
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
