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

//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
//            @Override
//            public int getOldListSize() {
//
//                return items.size();
//            }
//
//            @Override
//            public int getNewListSize() {
//                return items.size() + newItems.size();
//            }
//
//            @Override
//            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
//
//                return items.get(oldItemPosition).getId() == newItems.get(newItemPosition).getId()
//                        && items.get(oldItemPosition).getType() == newItems.get(newItemPosition).getType();
//            }
//
//            @Override
//            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
//
//                return items.get(oldItemPosition).equals(newItems.get(newItemPosition));
//            }
//        });

        items.addAll(newItems);
        notifyDataSetChanged();

//        diffResult.dispatchUpdatesTo(this);
    }

    public boolean hasItems() {

        return items.size() > 0;
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