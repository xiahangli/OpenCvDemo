package com.example.common.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;


public abstract class ViewHolderBinder<D, T extends BindingViewHolder> {


    public abstract T createViewHolder(LayoutInflater inflater, ViewGroup parent);

    public abstract void onBindViewHolder(T holder, D data,int position);

    public void onBindViewHolder(T holder, D data, int position ,List<Object> payloads) {
        onBindViewHolder(holder, data,position);
    }

}
