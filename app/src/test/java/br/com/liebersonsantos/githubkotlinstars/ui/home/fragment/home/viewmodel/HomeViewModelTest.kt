package br.com.liebersonsantos.githubkotlinstars.ui.home.fragment.home.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.liebersonsantos.githubkotlinstars.data.core.State
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase.GetCacheUseCaseImpl
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase.PutCacheUseCaseImpl
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase.GetRepositoriesUseCaseImpl
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.exceptions.base.MockitoException
import java.io.IOException

/**
 * Created by lieberson on 12/26/21.
 *
 * @author lieberson.xsantos@gmail.com
 */
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private var mockGetRepositoriesUseCase = mock(GetRepositoriesUseCaseImpl::class.java)
    private var mockPutCacheUseCaseImpl = mock(PutCacheUseCaseImpl::class.java)
    private var mockgetCacheUseCaseImpl = mock(GetCacheUseCaseImpl::class.java)

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup(){
        Dispatchers.setMain(Dispatchers.Unconfined)
        homeViewModel = HomeViewModel(testCoroutineDispatcher, mockGetRepositoriesUseCase, mockPutCacheUseCaseImpl, mockgetCacheUseCaseImpl)
    }

    @After
    fun tearDown(){
        testCoroutineDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `testing get repositories success`() = testCoroutineDispatcher.runBlockingTest {
        testCoroutineDispatcher.pauseDispatcher()

        doReturn(mock()).`when`(mockGetRepositoriesUseCase).invoke(anyString(), anyString(), anyInt())

        homeViewModel.getRepositories(anyString(), anyString(), anyInt())

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(homeViewModel.repositories.value).isEqualTo(State.success(mock()))

    }

    @Test (expected = MockitoException::class)
    fun `testing get repositories error state`() = testCoroutineDispatcher.runBlockingTest{
        testCoroutineDispatcher.pauseDispatcher()

        doThrow(IOException::class.java).`when`(mockGetRepositoriesUseCase).invoke(
            anyString(),
            anyString(),
            anyInt()
        )

        homeViewModel.getRepositories(anyString(), anyString(), anyInt())

        testCoroutineDispatcher.resumeDispatcher()
        Truth.assertThat(homeViewModel.repositories.value).isEqualTo(State.error<RepositoryDomain>(IOException()))
    }

    private fun mock() = mutableListOf(RepositoryDomain(
        "okhttp" ,
        "square",
        "https://avatars.githubusercontent.com/u/82592?v=4",
        "Square’s meticulous HTTP client for the JVM, Android, and GraalVM.",
        8724,
        41337,
        "https://github.com/square/okhttp"
    ), RepositoryDomain(
        "architecture-samples",
        "android",
        "https://avatars.githubusercontent.com/u/32689599?v=4",
        "A collection of samples to discuss and showcase different architectural tools and patterns for Android apps.",
        10941,
        39922,
        "https://github.com/android/architecture-samples"
    ))

}