package com.example.quanlyguixe.util.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {

    protected VB viewBinding;

    public abstract VB inflateViewBinding();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = inflateViewBinding();
        setContentView(viewBinding.getRoot());

        initScreenData();
        addEvent();
        bindToViewModel();
    }

    @Override
    protected void onDestroy() {
        viewBinding = null;
        super.onDestroy();
    }

    protected abstract void initScreenData();

    protected abstract void addEvent();

    protected abstract void bindToViewModel();

}
