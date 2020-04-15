package com.yuriy.githubmvvm.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yuriy.githubmvvm.mvvm.GitHubViewModel
import com.yuriy.githubmvvm.mvvm.Repository
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
class AppModule {

    @MapKey
    @Target(AnnotationTarget.FUNCTION)
    annotation class ViewModelKey(
        val value: KClass<out ViewModel>
    )

    /* Singleton factory that searches generated map for specific provider and
       uses it to get a ViewModel instance */
    @Provides
    @Singleton
    fun provideViewModelFactory(
        providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
    ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return requireNotNull(providers[modelClass as Class<out ViewModel>]).get() as T
        }
    }

    @Provides
    @IntoMap
    @ViewModelKey(GitHubViewModel::class)
    fun provideGithubViewModel(repository: Repository): ViewModel {
        return GitHubViewModel(repository)
    }
}