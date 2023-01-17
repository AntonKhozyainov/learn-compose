package ru.khozyainov.amphibians.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.khozyainov.amphibians.data.AmphibiansApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        //Подключение интерцептора для лога запросов TAG: okhttp.OkHttpClient
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .followRedirects(true)
        .build()

    @Provides
    @Singleton
    fun providesRetrofit(okhttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://android-kotlin-fun-mars-server.appspot.com/")
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
//                    .add(ItemPhotoListAdapterJSON())
//                    .add(SearchItemPhotoAdapterJSON(ItemPhotoListAdapterJSON()))
//                    .add(PhotoDetailAdapterJSON())
                    .build()
            )
        )
        .client(okhttpClient)
        .build()

    @Provides
    @Singleton
    fun providesNetworkSubredditApi(retrofit: Retrofit): AmphibiansApi =
        retrofit.create(AmphibiansApi::class.java)
}