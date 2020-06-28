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

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.MenuRes
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.navigation.NavigationView
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.MenuBottomSheetDialogFragmentBinding

/**
 * [BottomSheetDialogFragment]
 */
class MenuBottomSheetDialogFragment(
    @MenuRes private val menuRes: Int
) : BottomSheetDialogFragment() {
    private var _binding: MenuBottomSheetDialogFragmentBinding? = null
    private val binding get() = _binding!!

    private var navigationView: NavigationView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MenuBottomSheetDialogFragmentBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationView = view.findViewById(R.id.navigation_view)
        navigationView?.inflateMenu(menuRes)
        navigationView?.setupWithNavController(findNavController())

        binding.aboutButton.setOnClickListener {
            dismiss()
            findNavController().navigate(R.id.aboutFragment)
        }
        binding.openSourceLicensesButton.setOnClickListener {
            dismiss()
            val intent = Intent(requireContext(), OssLicensesMenuActivity::class.java)
            intent.putExtra("title", getString(R.string.open_source_licenses))
            startActivity(intent)
        }
    }
}
