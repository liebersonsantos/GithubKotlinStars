package br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.liebersonsantos.githubkotlinstars.R
import br.com.liebersonsantos.githubkotlinstars.base.BaseFragment
import br.com.liebersonsantos.githubkotlinstars.data.core.Status
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import br.com.liebersonsantos.githubkotlinstars.databinding.FragmentHomeBinding
import br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.adapter.HomeAdapter
import br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.viewmodel.HomeViewModel
import br.com.liebersonsantos.githubkotlinstars.util.hasInternet
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

const val URL = "URL"
const val CACHE_REPOSITORY = "CACHE_REPOSITORY"

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter
    private var domainList = mutableListOf<RepositoryDomain>()
    private var pastVisibleItems = 0
    private var totalItemCount = 0
    private var loading = false
    private var pageLoad = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkConnection()
        setAdapter()
        setRv()
        observeVMEvents()

        binding.spLayout.setOnRefreshListener {
            checkConnection()
        }
    }

    private fun observeVMEvents(){
        viewModel.repositories.observe(viewLifecycleOwner){
            if (this.lifecycle.currentState != Lifecycle.State.RESUMED) return@observe
            when(it.status){
                Status.SUCCESS ->{
                    it.data?.let { data ->
                        domainList.addAll(data)
                        viewModel.putCache(domainList)
                        setFlipper(domainList)
                        homeAdapter.submitList(domainList)
                        binding.spLayout.isRefreshing = false
                        loading = false
                    }
                }
                Status.ERROR -> {
                    binding.spLayout.isRefreshing = false
                    Timber.tag("repositories").i(it.error)
                }
            }
        }

        viewModel.cacheRepositories.observe(viewLifecycleOwner){
            when(it.status){
                Status.SUCCESS -> {
                    it.data?.let { data ->
                        domainList.addAll(data)
                        setFlipper(domainList)
                        homeAdapter.submitList(domainList)
                        binding.spLayout.isRefreshing = false
                    }
                }
                Status.ERROR -> {
                    binding.spLayout.isRefreshing = false
                    Timber.tag("repositories").i(it.error)
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner){
            if (it) binding.spLayout.isRefreshing = it
        }
    }

    private fun getRepositories(page: Int){
        viewModel.getRepositories(page = page)
    }

    private fun setFlipper(list: MutableList<RepositoryDomain>) {
        binding.flipper.setList(list)
        binding.flipper.setLayout()
    }

    private fun setRv(){
        binding.rvRepositories.run {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
            adapter = homeAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (dy > 0) {
                        totalItemCount = (layoutManager as GridLayoutManager).itemCount
                        pastVisibleItems =
                            (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

                        if (!loading) {
                            if (pastVisibleItems >= totalItemCount - 1) {
                                loading = true
                                pageLoad++
                                this@HomeFragment.getRepositories(pageLoad)
                            }
                        }
                    }
                }
            })
        }
    }

    private fun setAdapter() {
        homeAdapter = HomeAdapter {
            findNavController().navigate(R.id.action_homeFragment_to_repositoryFragment,
            Bundle().apply { putString(URL, it.htmlUrl) })
        }
    }

    private fun showMessage(message: String) =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    override fun checkConnection() {
        if (hasInternet(requireContext())) {
            getRepositories(pageLoad)
        } else {
            viewModel.getCacheRepositories(CACHE_REPOSITORY)
            showMessage(getString(R.string.check_your_internet))
        }
    }
}