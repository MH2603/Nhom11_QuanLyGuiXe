package com.example.quanlyguixe.screen.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.example.quanlyguixe.databinding.ItemHomeScreenBinding;
import com.example.quanlyguixe.util.base.BaseRecyclerViewAdapter;
import com.example.quanlyguixe.util.base.BaseViewHolder;
import com.example.quanlyguixe.util.interfaces.IOnClickItemListener;

public class HomeAdapter extends BaseRecyclerViewAdapter<ScreenEntry, ItemHomeScreenBinding, HomeAdapter.ViewHolder> {

    private IOnClickItemListener<ScreenEntry> onClickItemListener;

    public HomeAdapter() {
        super(ScreenEntry.getDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeScreenBinding binding =
                ItemHomeScreenBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent, false);

        return new ViewHolder(binding);
    }

    public void setOnClickItemListener(IOnClickItemListener<ScreenEntry> onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public class ViewHolder extends BaseViewHolder<ScreenEntry, ItemHomeScreenBinding> {

        public ViewHolder(@NonNull ItemHomeScreenBinding binding) {
            super(binding);
        }

        @Override
        public void bindItem(ScreenEntry item) {
            super.bindItem(item);
            viewBinding.imageCategoryScreen.setImageResource(item.getImageResourceID());

            viewBinding.textCategoryScreen.setText(viewBinding.getRoot().getContext().getString(item.getDisplayText()));

            viewBinding.getRoot().setOnClickListener(v -> {
                onClickItemListener.onClickItem(item);
            });
        }
    }
}
