package br.com.liebersonsantos.githubkotlinstars.domain.di

import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase.GetCacheUseCase
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase.GetCacheUseCaseImpl
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase.PutCacheUseCase
import br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase.PutCacheUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(ViewModelComponent::class)
interface CacheModule {

    @Binds
    fun bindPutCacheRepository(useCase: PutCacheUseCaseImpl): PutCacheUseCase
    @Binds
    fun getCacheRepository(useCase: GetCacheUseCaseImpl): GetCacheUseCase
}