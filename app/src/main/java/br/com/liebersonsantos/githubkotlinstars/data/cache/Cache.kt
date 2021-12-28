package br.com.liebersonsantos.githubkotlinstars.data.cache

import android.content.Context
import androidx.annotation.NonNull
import com.orhanobut.hawk.Hawk

/**
 * Created by lieberson on 12/26/21.
 * @author lieberson.xsantos@gmail.com
 */
object Cache {

    fun init(context: Context) = Hawk.init(context).build()
    fun contains(@NonNull key: String): Boolean = Hawk.contains(key)
    fun <T> get(@NonNull key: String): T = Hawk.get(key)
    fun <T> put(@NonNull key: String, @NonNull value: T) = Hawk.put(key, value)
    fun delete(@NonNull key: String) = Hawk.delete(key)

}