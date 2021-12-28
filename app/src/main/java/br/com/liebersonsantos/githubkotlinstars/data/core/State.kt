package br.com.liebersonsantos.githubkotlinstars.data.core
/**
 * Created by lieberson on 8/17/21.
 * @author lieberson.xsantos@gmail.com
 */
data class State<out T>(
    val status: Status,
    val data: T?,
    val error: Throwable?
) {
    companion object {
        fun <T> success(data: T?): State<T> {
            return State(Status.SUCCESS, data = data, error = null)
        }

        fun <T> error(error: Throwable): State<T> {
            return State(Status.ERROR, data = null, error = error)
        }
    }
}