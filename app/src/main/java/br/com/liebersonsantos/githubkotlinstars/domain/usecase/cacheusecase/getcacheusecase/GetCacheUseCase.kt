package br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.getcacheusecase

import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetCacheUseCase {
    operator fun invoke(key: String): MutableList<RepositoryDomain>
}