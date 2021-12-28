package br.com.liebersonsantos.githubkotlinstars.data.model

import java.io.Serializable

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
data class RepositoryDomain(
    val repositoryName: String? = "",
    val userName: String? = "",
    val avatar: String? = "",
    val description: String? = "",
    val forks: Int? = 0,
    val stars: Int ? = 0,
    val htmlUrl: String? = ""
): Serializable
