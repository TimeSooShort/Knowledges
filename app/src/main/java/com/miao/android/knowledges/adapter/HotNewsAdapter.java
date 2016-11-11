package com.miao.android.knowledges.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miao.android.knowledges.R;
import com.miao.android.knowledges.bean.HotNewsBean;
import com.miao.android.knowledges.interfaces.CustomClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HotNewsAdapter extends RecyclerView.Adapter<HotNewsAdapter.HotViewHolder>{

    private final Context mContext;
    private final LayoutInflater mInflater;
    private List<HotNewsBean> mBeanList;
    private CustomClickListener mListener;

    public HotNewsAdapter(Context context, List<HotNewsBean> beanList) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBeanList = beanList;
    }

    @Override
    public HotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.universial_layout, parent, false);
        return new HotViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(HotViewHolder holder, int position) {
        holder.hotTitle.setText(mBeanList.get(position).getTitle());
        if (mBeanList.get(position).getThumbnail() == null) {
            holder.hotImageView.setImageResource(R.drawable.background);
        }else {
            Glide.with(mContext)
                    .load(mBeanList.get(position).getThumbnail())
                    .error(R.drawable.background)
                    .centerCrop()
                    .into(holder.hotImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void setItemClickListener (CustomClickListener listener) {
        mListener = listener;
    }

    class HotViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView hotImageView;
        private TextView hotTitle;
        private CustomClickListener listener;

        public HotViewHolder(View itemView, CustomClickListener listener) {
            super(itemView);

            this.listener = listener;
            hotImageView = (ImageView) itemView.findViewById(R.id.picture_imageView);
            hotTitle = (TextView) itemView.findViewById(R.id.title_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.OnItemClick(view, getLayoutPosition());
        }
    }
}
