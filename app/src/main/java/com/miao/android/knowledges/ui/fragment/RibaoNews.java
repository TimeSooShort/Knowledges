package com.miao.android.knowledges.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.miao.android.knowledges.R;
import com.miao.android.knowledges.adapter.MyViewPagerAdapter;
import com.miao.android.knowledges.adapter.RibaoRecyclerAdapter;
import com.miao.android.knowledges.bean.StoriesBean;
import com.miao.android.knowledges.interfaces.CustomClickListener;
import com.miao.android.knowledges.ui.activity.ContentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/11.
 */

public class RibaoNews extends Fragment {

    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;
    private RequestQueue queue;
    private List<StoriesBean> storiesBeanList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ribao_fragment, container, false);
        initViews(view);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        String url = "http://news-at.zhihu.com/api/4/news/latest";
        //String url = "http://news.at.zhihu.com/api/4/news/before/20161014";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray storiesArray = response.getJSONArray("stories");
                            for (int i = 0; i < storiesArray.length(); i++) {
                                JSONObject storyItem = storiesArray.getJSONObject(i);
                                String title = storyItem.getString("title");
                                String id = storyItem.getString("id");
                                JSONArray imageUrls = storyItem.getJSONArray("images");
                                List<String> imageUrlList = new ArrayList<>();
                                for (int j = 0; j < imageUrls.length(); j++) {
                                    String imageUrl = imageUrls.getString(j);
                                    imageUrlList.add(imageUrl);
                                }
                                StoriesBean storiesBean = new StoriesBean(id, title, imageUrlList);
                                storiesBeanList.add(storiesBean);
                            }

                            JSONArray topStoriesArray = response.getJSONArray("top_stories");
                            List<Fragment> topPicturesList = new ArrayList<>();
                            for (int o = 0; o < topStoriesArray.length(); o++) {
                                JSONObject topStoryItem = topStoriesArray.getJSONObject(o);
                                String topTitle = topStoryItem.getString("title");
                                String topImage = topStoryItem.getString("image");
                                String topId = topStoryItem.getString("id");
                                TopPictures topPictures = new TopPictures(topId, topTitle, topImage);
                                topPicturesList.add(topPictures);
                            }
                            MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(
                                    getChildFragmentManager(), topPicturesList);
                            mViewPager.setAdapter(viewPagerAdapter);

                            RibaoRecyclerAdapter recyclerAdapter = new RibaoRecyclerAdapter(
                                    getActivity(), storiesBeanList);
                            mRecyclerView.setAdapter(recyclerAdapter);
                            recyclerAdapter.setItemClickListener(new CustomClickListener() {
                                @Override
                                public void OnItemClick(View v, int position) {
                                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                                    intent.putExtra("id", storiesBeanList.get(position).getId());
                                    startActivity(intent);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
        return view;
    }

    private void initViews(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.ri_view_pager);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ri_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
