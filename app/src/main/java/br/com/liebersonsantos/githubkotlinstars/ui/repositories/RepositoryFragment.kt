package br.com.liebersonsantos.githubkotlinstars.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import br.com.liebersonsantos.githubkotlinstars.base.BaseFragment
import br.com.liebersonsantos.githubkotlinstars.databinding.FragmentRepositoryBinding
import br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.URL
import br.com.liebersonsantos.githubkotlinstars.util.DialogInfo
import br.com.liebersonsantos.githubkotlinstars.util.hasInternet
import dagger.hilt.android.AndroidEntryPoint

const val REPOSITORY_FRAGMENT = "REPOSITORY_FRAGMENT"

@AndroidEntryPoint
class RepositoryFragment : BaseFragment() {
    private lateinit var binding: FragmentRepositoryBinding
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(URL)?.let {
            url = it
        }
        checkConnection()
    }

    private fun openRepository() {
        binding.run {
            web.loadUrl(url)
        }
    }

    override fun checkConnection() {
        if (hasInternet(requireContext())) {
            openRepository()
        } else {
            DialogInfo().apply {
                okListener {
                    dismiss()
                    findNavController().popBackStack()
                }
            }.show(parentFragmentManager, REPOSITORY_FRAGMENT)
        }
    }
}