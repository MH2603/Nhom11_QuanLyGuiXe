package com.example.quanlyguixe.util.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.viewbinding.ViewBinding;

import java.util.concurrent.Executors;

public abstract class BaseRecyclerViewAdapter<T, VB extends ViewBinding, VH extends BaseViewHolder<T, VB>>
        extends ListAdapter<T, VH> {
    protected BaseRecyclerViewAdapter(@NonNull DiffUtil.ItemCallback<T> diffCallback) {
        super(new AsyncDifferConfig.Builder(diffCallback)
                .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
                .build());
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if(position < getCurrentList().size()) {
            holder.bindItem(getItem(position));
        }
    }
}
