package br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase

import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
interface GetRepositoriesUseCase {
    suspend operator fun invoke(language: String, sort: String, page: Int): MutableList<RepositoryDomain>
}