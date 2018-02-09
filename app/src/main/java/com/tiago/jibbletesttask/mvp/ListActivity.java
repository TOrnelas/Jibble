package com.tiago.jibbletesttask.mvp;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.tiago.jibbletesttask.R;
import com.tiago.jibbletesttask.Utils;
import com.tiago.jibbletesttask.adapters.ContentAdapter;
import com.tiago.jibbletesttask.databinding.ActivityListBinding;
import com.tiago.jibbletesttask.models.custom.Content;
import com.tiago.jibbletesttask.repositories.JsonPlaceholderRepository;

import java.util.List;

public class ListActivity extends AppCompatActivity implements ListActivityView, View.OnClickListener {

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

        //listeners
        binding.retryBt.setOnClickListener(this);
    }

    @Override
    protected void onResume() {

        super.onResume();
        if (!adapter.hasItems()) presenter.loadContent();
    }

    @Override
    protected void onPause() {

        super.onPause();
        presenter.cancelDataFetching();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.clearDisposables();
    }

    private void setStyles() {

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.removeItem(viewHolder.getAdapterPosition());
            }});


        itemTouchHelper.attachToRecyclerView(binding.contentRv);
        binding.contentRv.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void displayError() {

        binding.errorContainerLl.setVisibility(View.VISIBLE);
    }

    @Override
    public void displayItems(List<Content> contentList) {

        adapter.addItems(contentList);

        if(Utils.isFirstTime(this)){

            new AlertDialog.Builder(this).setTitle(getString(R.string.popup_title))
                    .setMessage(getString(R.string.popup_text))
                    .setPositiveButton(getString(R.string.ok), null)
                    .show();
        }
    }

    @Override
    public void displayIsLoading(boolean isLoading) {

        binding.loadingPb.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        binding.contentRv.setVisibility(!adapter.hasItems() ? View.GONE : View.VISIBLE);
        binding.errorContainerLl.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {

        presenter.loadContent();
    }
}