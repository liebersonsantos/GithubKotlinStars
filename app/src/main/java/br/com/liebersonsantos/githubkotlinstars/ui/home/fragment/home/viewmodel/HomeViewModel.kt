package br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.liebersonsantos.githubkotlinstars.data.core.State
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase.GetCacheUseCase
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase.PutCacheUseCase
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase.GetRepositoriesUseCase
import br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.CACHE_REPOSITORY
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("io") private val ioDispatcher: CoroutineDispatcher,
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val putCacheUseCase: PutCacheUseCase,
    private val getCacheUseCase: GetCacheUseCase
) : ViewModel() {

    private val _repositories = MutableLiveData<State<MutableList<RepositoryDomain>>>()
    val repositories: LiveData<State<MutableList<RepositoryDomain>>>
        get() = _repositories

    private val _cacheRepositories = MutableLiveData<State<MutableList<RepositoryDomain>>>()
    val cacheRepositories: LiveData<State<MutableList<RepositoryDomain>>>
        get() = _cacheRepositories

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun getRepositories(language: String = "language:kotlin", sort: String = "stars", page: Int) =
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = withContext(ioDispatcher) {
                    getRepositoriesUseCase.invoke(language, sort, page)
                }
                putCacheUseCase.invoke(CACHE_REPOSITORY, response)
                _repositories.value = State.success(response)
                _loading.value = false
            } catch (e: Exception) {
                _repositories.value = State.error(e)
                _loading.value = false
            }
        }

    fun getCacheRepositories(key: String) =
        try {
           val response =  getCacheUseCase.invoke(key)
            Timber.tag("CACHES").i(Gson().toJson(response))
            _cacheRepositories.value = State.success(response)
        } catch (e: Exception){
            _cacheRepositories.value = State.error(e)
        }
}