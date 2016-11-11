package com.miao.android.knowledges.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.miao.android.knowledges.R;
import com.miao.android.knowledges.ui.activity.ContentActivity;

/**
 * Created by Administrator on 2016/10/10.
 */

@SuppressLint("ValidFragment")
public class TopPictures extends Fragment {

    //private static final int IMAGE_RESPONSE = 1;
    private ImageView topPicture;
    private TextView topTitle;
    private String mTitle;
    private String mId;
    private String mUrl;

    @SuppressLint("ValidFragment")
    public TopPictures(String id, String title, String url) {
        mId = id;
        mTitle = title;
        mUrl = url;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.top_picture_fragment, container, false);
        initViews(view);
        topTitle.setText(mTitle);

        Glide.with(getActivity()).load(mUrl).error(R.drawable.background).centerCrop().into(topPicture);
        topPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContentActivity.class);
                intent.putExtra("id", mId);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initViews(View view) {
        topPicture = (ImageView) view.findViewById(R.id.top_picture);
        topTitle = (TextView) view.findViewById(R.id.top_title);
    }

}
