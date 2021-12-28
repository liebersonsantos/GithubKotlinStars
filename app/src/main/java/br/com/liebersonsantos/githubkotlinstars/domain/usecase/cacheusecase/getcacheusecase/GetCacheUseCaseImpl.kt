package br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase

import br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository.CacheRepository
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import javax.inject.Inject

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetCacheUseCaseImpl @Inject constructor(private val repository: CacheRepository): GetCacheUseCase {
    override fun invoke(key: String): MutableList<RepositoryDomain> = repository.get(key)
}