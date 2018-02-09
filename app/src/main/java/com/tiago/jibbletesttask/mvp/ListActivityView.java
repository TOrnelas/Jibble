package com.tiago.jibbletesttask.mvp;

import com.tiago.jibbletesttask.models.custom.Content;

import java.util.List;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public interface ListActivityView {

    void displayError();

    void displayItems(List<Content> contentList);

    void displayIsLoading(boolean isLoading);
}