package com.example.common.databinding;


import androidx.databinding.ObservableArrayList;

public class Items extends ObservableArrayList<Object> {


    DataBindingAdapter mAdapter;

    public Items() {
        super();
    }

    public void setAdapter(DataBindingAdapter adapter) {
        mAdapter = adapter;
    }


    public void notifyItemRangeChanged(int positionStart, int itemCount, Object payload) {
        mAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
    }

    public void notifyItemChanged(int position, Object payload) {
        mAdapter.notifyItemChanged(position, payload);
    }

}
