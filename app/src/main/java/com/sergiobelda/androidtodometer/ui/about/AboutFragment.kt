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

package com.sergiobelda.androidtodometer.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import com.sergiobelda.androidtodometer.R
import com.sergiobelda.androidtodometer.databinding.AboutFragmentBinding
import dev.sergiobelda.android.companion.content.launchActivity
import dev.sergiobelda.android.companion.material.createMaterialDialog
import dev.sergiobelda.android.companion.material.message
import dev.sergiobelda.android.companion.material.positiveButton
import dev.sergiobelda.android.companion.material.title

/**
 * [Fragment] showing App information.
 */
class AboutFragment : Fragment() {
    private var _binding: AboutFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = AboutFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.packageName?.let {
            val packageInfo = context?.packageManager?.getPackageInfo(it, 0)
            val versionName = packageInfo?.versionName
            binding.versionNumberTextView.text = versionName
            if (versionName?.contains(getString(R.string.beta)) == true) {
                binding.releaseChannelTextView.visibility = View.VISIBLE
                binding.releaseChannelTextView.text = getString(R.string.beta)
            }
            if (versionName?.contains(getString(R.string.alpha)) == true) {
                binding.releaseChannelTextView.visibility = View.VISIBLE
                binding.releaseChannelTextView.text = getString(R.string.alpha)
            }
        }
        binding.githubCard.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(getString(R.string.github_url))
            startActivity(intent)
        }
        binding.openSourceLicensesCard.setOnClickListener {
            context?.launchActivity<OssLicensesMenuActivity> {
                putExtra("title", getString(R.string.open_source_licenses))
            }
        }
        binding.privacyPolicyCard.setOnClickListener {
            val htmlBody =
                Html.fromHtml(getString(R.string.privacy_policy_body), Html.FROM_HTML_MODE_COMPACT)
            createMaterialDialog(requireContext()) {
                title(R.string.privacy_policy)
                message(htmlBody)
                positiveButton(R.string.accept)
            }.show()
        }
    }
}
