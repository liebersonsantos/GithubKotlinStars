package br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository

import br.com.liebersonsantos.githubkotlinstars.data.cache.Cache
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import javax.inject.Inject

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
class CacheRepositoryImpl @Inject constructor(private val cache: Cache): CacheRepository {

    override fun get(key: String): MutableList<RepositoryDomain> = cache.get(key)

    override fun <T> put(key: String, value: T) {
        if (cache.contains(key)){
            cache.delete(key)
        }

        cache.put(key, value)
    }

    override fun delete(key: String) {
        cache.delete(key)
    }
}