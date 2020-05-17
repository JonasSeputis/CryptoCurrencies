package com.example.tribecrypto.di.module;

import com.example.tribecrypto.BuildConfig;
import com.example.tribecrypto.java.Api;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    @Provides
    public OkHttpClient provideOkHttpClient()  {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();
    }

    @Provides
    public Retrofit provideRetrofit(OkHttpClient okHttpClient, Gson gson)  {
        return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }

    @Provides
    public Gson providesGson() {
        return new GsonBuilder().create();
    }

    @Provides
    public Api provideNetworkService(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}
