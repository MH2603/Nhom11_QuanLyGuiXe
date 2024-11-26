package com.example.quanlyguixe.util.base;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

public abstract class BaseViewHolder<T, VB extends ViewBinding> extends RecyclerView.ViewHolder {

    private T item;

    protected VB viewBinding;

    public BaseViewHolder(@NonNull VB binding) {

        super(binding.getRoot());
        this.viewBinding = binding;
    }

    public void bindItem(T item) {
        this.item = item;
    }


}
