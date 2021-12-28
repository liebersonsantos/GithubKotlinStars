package br.com.liebersonsantos.githubkotlinstars.data.api

import br.com.liebersonsantos.githubkotlinstars.data.model.Repositories
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by lieberson on 12/24/21.
 * @author lieberson.xsantos@gmail.com
 */
interface Api {

    @GET("search/repositories")
    suspend fun getRepositories(
        @Query("q") language: String,
        @Query("sort") sort: String,
        @Query("page") page: Int
    ): Repositories
}