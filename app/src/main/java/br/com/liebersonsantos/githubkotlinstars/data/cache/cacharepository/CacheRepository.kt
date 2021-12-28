package br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository

import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
interface CacheRepository {
    fun get(key: String): MutableList<RepositoryDomain>
    fun <T> put(key: String, value: T)
    fun delete(key: String)
}