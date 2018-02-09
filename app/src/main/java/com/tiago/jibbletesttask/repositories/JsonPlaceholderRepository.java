package com.tiago.jibbletesttask.repositories;

import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class JsonPlaceholderRepository implements DataRepository {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private Retrofit getRetrofitInstance(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new HttpLoggingInterceptor());
        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public Single<List<Post>> fetchPosts() {

        return getRetrofitInstance().create(ApiEndpoints.class).getPosts();
    }

    @Override
    public Single<List<User>> fetchUsers() {

        return getRetrofitInstance().create(ApiEndpoints.class).getUsers();
    }

    @Override
    public Single<List<Album>> fetchAlbums() {

        return getRetrofitInstance().create(ApiEndpoints.class).getAlbums();
    }
}