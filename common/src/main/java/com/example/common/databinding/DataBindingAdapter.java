package com.example.common.databinding;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DataBindingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Items mItems;

    private final ItemBinderManager mItemHolderManager;

    private OnListChangeCallback mOnListChangeCallback;

    public DataBindingAdapter() {
        mItemHolderManager = new ItemBinderManager();
        mItems = new Items();
        mItems.setAdapter(this);
        mOnListChangeCallback = new OnListChangeCallback(this);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int index) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        ViewHolderBinder<?, ?> viewHolderWrapper = mItemHolderManager.getViewHolderWrapper(index);

        return viewHolderWrapper.createViewHolder(inflater, parent);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object object = mItems.get(position);

        ViewHolderBinder viewHolder = mItemHolderManager.getViewHolderWrapper(holder.getItemViewType());

        viewHolder.onBindViewHolder((BindingViewHolder) holder, object, position);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List<Object> payloads) {
        if (payloads != null && !payloads.isEmpty()) {
            Object object = mItems.get(position);

            ViewHolderBinder viewHolder = mItemHolderManager.getViewHolderWrapper(holder.getItemViewType());

            viewHolder.onBindViewHolder((BindingViewHolder) holder, object, position, payloads);
        } else {
            onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    @Override
    public int getItemViewType(int position) {
        Object object = mItems.get(position);
        return mItemHolderManager.findItemIndex(object);
    }

    public <T> DataBindingAdapter map(Class<? extends T> item, ViewHolderBinder<T, ?> holder) {
        mItemHolderManager.addItem(item, holder);
        return this;
    }

    public void setItems(Items items) {
        mItems.clear();
        mItems = items;
        mItems.addOnListChangedCallback(mOnListChangeCallback);
        mItems.setAdapter(this);
        notifyDataSetChanged();
    }

}
