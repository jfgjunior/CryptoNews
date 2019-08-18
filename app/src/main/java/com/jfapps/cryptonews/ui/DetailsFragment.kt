package com.jfapps.cryptonews.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.jfapps.cryptonews.R
import com.jfapps.cryptonews.databinding.FragmentDetailsBinding
import com.jfapps.cryptonews.extensions.inject
import com.jfapps.cryptonews.extensions.viewModel
import com.jfapps.cryptonews.viewmodel.DetailsViewModel

class DetailsFragment : Fragment() {
    private val viewModel: DetailsViewModel by viewModel {
        inject.detailsViewModelFactory.create(
            DetailsFragmentArgs.fromBundle(arguments!!).news
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDetailsBinding>(
            inflater, R.layout.fragment_details, container, false
        )
        binding.viewModel = viewModel
        return binding.root
    }
}