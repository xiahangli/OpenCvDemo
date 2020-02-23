package com.example.common.databinding;


import java.util.ArrayList;
import java.util.List;

public class ItemBinderManager {


    private final List<Class<?>> dataTypeList;

    private final List<ViewHolderBinder<?, ?>> mViewHolderWrappers;

    public ItemBinderManager() {
        dataTypeList = new ArrayList<>();
        mViewHolderWrappers = new ArrayList<>();
    }


    public <T> void addItem(Class<? extends T> item, ViewHolderBinder<T, ?> holder) {
        dataTypeList.add(item);
        mViewHolderWrappers.add(holder);
    }


    public int findItemIndex(Object object) {
        int index = dataTypeList.indexOf(object.getClass());
        return index;
    }

    public ViewHolderBinder<?, ?> getViewHolderWrapper(int index) {
        return mViewHolderWrappers.get(index);
    }

}
