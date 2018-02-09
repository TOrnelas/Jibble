package com.tiago.jibbletesttask.models.custom;

import com.tiago.jibbletesttask.models.dto.Album;
import com.tiago.jibbletesttask.models.dto.Post;
import com.tiago.jibbletesttask.models.dto.User;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class Content {

    public enum ContentType{

        album,
        user,
        post
    }

    private int id;
    private String title;
    private String description;
    private ContentType type;

    public Content(Album album) {

        this.id = album.getId();
        this.title = album.getTitle();
        this.type = ContentType.album;
    }

    public Content(User user) {

        this.id = user.getId();
        this.title = user.getName();
        this.description = user.getEmail();
        this.type = ContentType.user;
    }

    public Content(Post post) {

        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getBody().replace("\n", " "); //to remove new lines
        this.type = ContentType.post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }
}
