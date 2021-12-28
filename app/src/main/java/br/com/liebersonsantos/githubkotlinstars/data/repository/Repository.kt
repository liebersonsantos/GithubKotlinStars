package br.com.liebersonsantos.githubkotlinstars.data.repository

import br.com.liebersonsantos.githubkotlinstars.data.model.Repositories

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
interface Repository {
    suspend fun getRepositories(language: String, sort: String, page: Int): Repositories
}