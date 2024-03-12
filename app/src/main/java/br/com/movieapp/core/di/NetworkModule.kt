package br.com.movieapp.core.di

import br.com.movieapp.BuildConfig
import br.com.movieapp.core.data.remote.MovieService
import br.com.movieapp.core.data.remote.ParamsInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMOUT_SECONDS = 15L

    @Provides
    fun providesParamsInterceptor(): ParamsInterceptor {
        return ParamsInterceptor()
    }

    // Configurando para mostrar as requisicoes no log
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
        }
    }

    // Essa funçao é responsavel por fazer a requisicao http
    // adiciono os interceptors (paramsInterceptor e loggingInterceptor) na cadeia de interceptadors do okhttp
    // alem disso vai definir um timout de leitura e conecçao de 15 segundos

    @Provides
    fun provideOkHttpClient(
        paramsInterceptor: ParamsInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(paramsInterceptor)
            .addInterceptor(loggingInterceptor)
            .readTimeout(TIMOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMOUT_SECONDS, TimeUnit.SECONDS)
            .build()
    }

    // essa funcao é responsavel por converter os objetos JSON em classes
    // e vice versa
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    // essa funcao é responsavel por criar uma instancia do retrofit com a baseurl
    // e vai retornar meu MovieService por de baixo dos panos tudo configurado
    @Provides
    fun provideMovieService(
        client: OkHttpClient,
        converterFactory: GsonConverterFactory
    ): MovieService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()
            .create(MovieService::class.java)

    }


}