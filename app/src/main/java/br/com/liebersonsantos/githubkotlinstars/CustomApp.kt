package br.com.liebersonsantos.githubkotlinstars

import android.app.Application
import br.com.liebersonsantos.githubkotlinstars.data.cache.Cache
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by lieberson on 12/24/21.
 * @author lieberson.xsantos@gmail.com
 */
@HiltAndroidApp
class CustomApp: Application() {

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {Timber.plant(Timber.DebugTree())}

        initHawk()

    }

    private fun initHawk(){
        Cache.init(this)
    }
}