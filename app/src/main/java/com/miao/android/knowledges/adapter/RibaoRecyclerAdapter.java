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
import com.miao.android.knowledges.bean.StoriesBean;
import com.miao.android.knowledges.interfaces.CustomClickListener;

import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */

public class RibaoRecyclerAdapter extends RecyclerView.Adapter<
        RibaoRecyclerAdapter.RibaoViewHolder> {

    private List<StoriesBean> mStoriesList;
    private final Context mContext;
    private final LayoutInflater mInflater;
    private CustomClickListener mListener;

    public RibaoRecyclerAdapter(Context context, List<StoriesBean> storiesList) {
        mContext = context;
        mStoriesList = storiesList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RibaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.universial_layout, parent, false);
        return new RibaoViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final RibaoViewHolder holder, int position) {
        StoriesBean story = mStoriesList.get(position);
        holder.mTextView.setText(story.getTitle());
        if (story.getImages().get(0) == null) {
            holder.mImageView.setImageResource(R.drawable.background);
        }else {
            Glide.with(mContext)
                    .load(story.getImages().get(0))
                    .error(R.drawable.background)
                    .centerCrop()
                    .into(holder.mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mStoriesList.size();
    }

    public void setItemClickListener(CustomClickListener listener) {
        mListener = listener;
    }

    class RibaoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImageView;
        private TextView mTextView;
        private CustomClickListener listener;

        public RibaoViewHolder(View itemView, CustomClickListener listener) {
            super(itemView);
            this.listener = listener;

            mImageView = (ImageView) itemView.findViewById(R.id.picture_imageView);
            mTextView = (TextView) itemView.findViewById(R.id.title_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.OnItemClick(view, getLayoutPosition());
        }
    }
}
