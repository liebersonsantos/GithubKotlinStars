package br.com.liebersonsantos.githubkotlinstars.data.cache.cacharepository

import br.com.liebersonsantos.githubkotlinstars.data.cache.Cache
import com.google.common.truth.Truth
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

/**
 * Created by lieberson on 12/28/21.
 *
 * @author lieberson.xsantos@gmail.com
 */
@ExperimentalCoroutinesApi
class CacheRepositoryImplTest: TestCase(){
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val cache = mock(Cache::class.java)

    @Test
    fun `test put data`() = testCoroutineDispatcher.runBlockingTest {
        `when`(cache.put("key", "value")).thenReturn(true)

        Truth.assertThat(cache.put("key", "value")).isTrue()
    }

    @Test
    fun `test put data fails`() = testCoroutineDispatcher.runBlockingTest {
        `when`(cache.put("key", "value")).thenReturn(false)

        Truth.assertThat(cache.put("key", "value")).isFalse()
    }

    @Test
    fun `test get data`() = testCoroutineDispatcher.runBlockingTest {
        `when`(cache.get<Any>("key")).thenReturn("value")

        Truth.assertThat(cache.get<Any>("key")).isEqualTo("value")
    }

    @Test
    fun `test delete data`() = testCoroutineDispatcher.runBlockingTest {
        `when`(cache.delete("key")).thenReturn(true)

        Truth.assertThat(cache.delete("key")).isTrue()
    }

    @Test
    fun `test contains`(){
        `when`(cache.contains("key")).thenReturn(true)

        Truth.assertThat(cache.contains("key")).isTrue()
    }

}