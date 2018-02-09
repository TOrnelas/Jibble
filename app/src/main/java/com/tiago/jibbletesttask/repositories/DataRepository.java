package com.tiago.jibbletesttask.repositories;

import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public interface DataRepository {

    Single<List<Post>> fetchPosts();

    Single<List<User>> fetchUsers();

    Single<List<Album>> fetchAlbums();
}