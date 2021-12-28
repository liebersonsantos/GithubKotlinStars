package br.com.liebersonsantos.githubkotlinstars.domain.di

import br.com.liebersonsantos.githubkotlinstars.data.cache.Cache
import br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository.CacheRepository
import br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository.CacheRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class CacheDataModule {

    @Singleton
    @Provides
    fun provideCacheRepository(): CacheRepository = CacheRepositoryImpl(Cache)

}