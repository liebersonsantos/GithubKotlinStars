package br.com.liebersonsantos.githubkotlinstars.domain.usecase.getrepositoriesusecase

import br.com.liebersonsantos.githubkotlinstars.data.model.Repositories
import br.com.liebersonsantos.githubkotlinstars.data.model.RepositoryDomain
import br.com.liebersonsantos.githubkotlinstars.data.repository.Repository
import javax.inject.Inject

/**
 * Created by lieberson on 12/25/21.
 * @author lieberson.xsantos@gmail.com
 */
class GetRepositoriesUseCaseImpl@Inject constructor(private val repository: Repository):
    GetRepositoriesUseCase {
    override suspend fun invoke(language: String, sort: String, page: Int): MutableList<RepositoryDomain> =
        converterToDomain(repository.getRepositories(language, sort, page))

}

fun converterToDomain(repositories: Repositories): MutableList<RepositoryDomain> {
    val repositoryDomain = mutableListOf<RepositoryDomain>()
    repositories.items.forEach {
        val repository = RepositoryDomain(repositoryName = it.name,
            userName = it.owner.login,
            avatar = it.owner.avatarUrl,
            description = it.description,
            forks = it.forks,
            stars = it.stargazersCount,
            htmlUrl = it.htmlUrl)

        repositoryDomain.add(repository)
    }

    return repositoryDomain
}
