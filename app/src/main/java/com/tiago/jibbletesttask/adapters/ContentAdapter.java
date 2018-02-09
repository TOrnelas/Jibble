package com.tiago.jibbletesttask.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tiago.jibbletesttask.R;
import com.tiago.jibbletesttask.databinding.ListItemContentBinding;
import com.tiago.jibbletesttask.models.custom.Content;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tiagoornelas on 09/02/2018.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>{

    List<Content> items;

    public ContentAdapter() {

        this.items = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemContentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_item_content, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    public void addItems(final List<Content> newItems){

        items.addAll(newItems);
        notifyDataSetChanged();
    }

    public boolean hasItems() {

        return items.size() > 0;
    }

    public void removeItem(int adapterPosition) {

        items.remove(adapterPosition);
        notifyItemRemoved(adapterPosition);
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ListItemContentBinding binding;

        public ViewHolder(ListItemContentBinding binding) {

            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Content content){

            binding.setContent(content);
        }
    }
}