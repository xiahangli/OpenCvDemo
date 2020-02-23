package com.example.common.databinding;


import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

//这里需要dataBindng enable=true，则可以导入ViewDataBinding运行时的类库
public abstract class BindingViewHolder<T, D extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public D mBinding;

    public BindingViewHolder(D binding) {
        super(binding.getRoot());
        this.mBinding = binding;
    }

    public abstract void onBind(T data, int position);

    public void onBind(T data, int position, Object payload) {
        onBind(data, position);
    }

    public D getBinding() {
        return mBinding;
    }
}
