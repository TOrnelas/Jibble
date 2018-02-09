package com.tiago.jibbletesttask.mvp;

import com.tiago.jibbletesttask.models.custom.Content;
import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;
import com.tiago.jibbletesttask.repositories.DataRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class ListActivityPresenter {

    private static final int LIMIT_FOR_EACH_CONTENT = 20;

    private ListActivityView view;
    private DataRepository repository;
    private CompositeDisposable compositeDisposable;

    public ListActivityPresenter(ListActivityView view, DataRepository repository) {

        this.view = view;
        this.repository = repository;
        this.compositeDisposable = new CompositeDisposable();
    }

    public void loadContent(){

        view.displayIsLoading(true);

        repository.fetchPosts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new SingleObserver<List<Post>>() {
                @Override
                public void onSubscribe(Disposable d) {

                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(List<Post> posts) {

                    List<Content> postAsContent = new ArrayList<>();

                    for (int i = 0; i < posts.size(); i ++){

                        if (i < LIMIT_FOR_EACH_CONTENT)
                            postAsContent.add(new Content(posts.get(i)));
                        else
                            break;
                    }

                    view.displayItems(postAsContent);

                    getAlbums();
                }

                @Override
                public void onError(Throwable e) {

                    getAlbums();
                }
            });
    }

    private void getAlbums() {

        repository.fetchAlbums()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new SingleObserver<List<Album>>() {
                @Override
                public void onSubscribe(Disposable d) {

                    compositeDisposable.add(d);
                }

                @Override
                public void onSuccess(List<Album> albums) {

                    List<Content> albumsAsContent = new ArrayList<>();

                    for (int i = 0; i < albums.size(); i ++){

                        if (i < LIMIT_FOR_EACH_CONTENT)
                            albumsAsContent.add(new Content(albums.get(i)));
                        else
                            break;
                    }

                    view.displayItems(albumsAsContent);

                    fetchUsers();
                }

                @Override
                public void onError(Throwable e) {

                    fetchUsers();
                }
            });
    }

    private void fetchUsers() {

        repository.fetchUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new SingleObserver<List<User>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<User> users) {


                        List<Content> usersAsContent = new ArrayList<>();

                        for (int i = 0; i < users.size(); i ++){

                            if (i < LIMIT_FOR_EACH_CONTENT)
                                usersAsContent.add(new Content(users.get(i)));
                            else
                                break;
                        }

                        view.displayIsLoading(false);
                        view.displayItems(usersAsContent);
                    }

                    @Override
                    public void onError(Throwable e) {

                        view.displayIsLoading(false);
                        view.displayError();
                    }
                });
    }

    public void cancelDataFetching(){

        compositeDisposable.clear();
    }

    public void clearDisposables() {

        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}