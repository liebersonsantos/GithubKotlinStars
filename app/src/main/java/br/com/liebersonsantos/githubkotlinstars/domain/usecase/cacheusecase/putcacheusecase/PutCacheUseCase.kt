package br.com.liebersonsantos.githubkotlinstars.domain.usecase.cacheusecase.putcacheusecase

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
interface PutCacheUseCase {
    operator fun <T> invoke(key: String, value: T)
}