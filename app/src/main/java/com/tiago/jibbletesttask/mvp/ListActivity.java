package com.tiago.jibbletesttask.mvp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.tiago.jibbletesttask.R;
import com.tiago.jibbletesttask.adapters.ContentAdapter;
import com.tiago.jibbletesttask.databinding.ActivityListBinding;
import com.tiago.jibbletesttask.models.custom.Content;
import com.tiago.jibbletesttask.repositories.JsonPlaceholderRepository;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListActivityView {

    //presenter
    private ListActivityPresenter presenter;

    //binding
    private ActivityListBinding binding;

    //adapter
    private ContentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        //presenter
        presenter = new ListActivityPresenter(this, new JsonPlaceholderRepository());

        //adapter
        adapter = new ContentAdapter();
        binding.contentRv.setAdapter(adapter);

        setStyles();

        // TODO: 09/02/2018 fetch data

    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.loadContent();
    }

    @Override
    protected void onPause() {

        super.onPause();
        presenter.cancelDataFetching();
    }

    private void setStyles() {

        binding.contentRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError() {

        binding.errorContainerLl.setVisibility(View.VISIBLE);
        binding.errorTv.setText(getString(R.string.error));
    }

    @Override
    public void displayNoContent() {

        binding.errorContainerLl.setVisibility(View.VISIBLE);
        binding.errorTv.setText(getString(R.string.no_content));
    }

    @Override
    public void displayItems(List<Content> contentList) {

        adapter.addItems(contentList);
    }

    @Override
    public void displayIsLoading(boolean isLoading) {

        binding.loadingPb.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        binding.contentRv.setVisibility(!adapter.hasItems() ? View.GONE : View.VISIBLE);
        binding.errorContainerLl.setVisibility(View.GONE);
    }
}