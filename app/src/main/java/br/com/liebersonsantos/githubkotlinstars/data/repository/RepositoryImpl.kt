package br.com.liebersonsantos.githubkotlinstars.data.repository

import br.com.liebersonsantos.githubkotlinstars.data.api.Api
import br.com.liebersonsantos.githubkotlinstars.data.model.Repositories
import javax.inject.Inject

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
class RepositoryImpl @Inject constructor(private val api: Api): Repository {
    override suspend fun getRepositories(language: String, sort: String, page: Int): Repositories =
        api.getRepositories(language, sort, page)
}