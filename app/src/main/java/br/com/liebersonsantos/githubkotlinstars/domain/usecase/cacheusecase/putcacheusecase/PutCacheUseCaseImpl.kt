package br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase

import br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository.CacheRepository
import javax.inject.Inject

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
class PutCacheUseCaseImpl @Inject constructor(private val repository: CacheRepository): PutCacheUseCase {
    override fun <T> invoke(key: String, value: T) = repository.put(key, value)
}