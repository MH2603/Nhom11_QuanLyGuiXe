package com.example.quanlyguixe.util.interfaces;

public interface IStateChangeListener<T> {

    void onStateChange(T item, int position, boolean isChecked);
}
