package com.jymun.harusekki.di.data

import android.content.Context
import com.jymun.harusekki.data.service.Constant
import com.jymun.harusekki.data.service.SearchRecipeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        val cache = Cache(context.cacheDir, 20 * 1024 * 1024)
        val interceptor = Interceptor { chain ->
            val request = chain.request()
            request.newBuilder().header("Cache-Control", "public, max-age=5").build()

            chain.proceed(request)
        }

        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(interceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideSearchRecipeService(retrofit: Retrofit): SearchRecipeService =
        retrofit.create(SearchRecipeService::class.java)
}