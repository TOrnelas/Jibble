package com.tiago.jibbletesttask.repositories;

import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class JsonPlaceholderRepository implements DataRepository {

    @Override
    public Single<List<Post>> fetchPosts() {

        return Single.just(Collections.<Post>emptyList());
    }

    @Override
    public Single<List<User>> fetchUsers() {

        return Single.just(Collections.<User>emptyList());
    }

    @Override
    public Single<List<Album>> fetchAlbums() {

        return Single.just(Collections.<Album>emptyList());
    }
}
