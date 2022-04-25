package andrefigas.com.github.myapplication

import android.app.Application
import org.koin.android.ext.koin.androidContext

import org.koin.core.context.startKoin

class SwApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@SwApplication)
            modules(listOf(mealServiceModule))
        }
    }
}

