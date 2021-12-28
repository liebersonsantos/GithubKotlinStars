package br.com.liebersonsantos.githubkotlinstars.domain.di

import br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase.GetRepositoriesUseCase
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase.GetRepositoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface DomainModule {

    @Binds
    fun bindGetRepositories(useCase: GetRepositoriesUseCaseImpl): GetRepositoriesUseCase
}