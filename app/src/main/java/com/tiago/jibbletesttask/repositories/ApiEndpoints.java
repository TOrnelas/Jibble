package com.tiago.jibbletesttask.repositories;

import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public interface ApiEndpoints {

    @GET("users")
    Single<List<User>> getUsers();

    @GET("posts")
    Single<List<Post>> getPosts();

    @GET("albums")
    Single<List<Album>> getAlbums();
}
