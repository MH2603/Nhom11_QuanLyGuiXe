package com.example.quanlyguixe.util.interfaces;

public interface IUpdateDeleteListener<T> {

    public void onUpdate(T item);

    public void onDelete(T item, int position);
}
