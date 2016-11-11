package com.miao.android.knowledges.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.miao.android.knowledges.adapter.HotNewsAdapter;
import com.miao.android.knowledges.bean.HotNewsBean;
import com.miao.android.knowledges.interfaces.CustomClickListener;
import com.miao.android.knowledges.ui.activity.ContentActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/10.
 */

public class HotNews extends Fragment {

    private RecyclerView hotRecycler;
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hotnews, container, false);
        initViews(view);

        queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        load();

        return view;
    }

    private void load() {

        String hotUrl = "http://news-at.zhihu.com/api/3/news/hot";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, hotUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("recent");
                            final List<HotNewsBean> beanList = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);
                                String newsId = object.getString("news_id");
                                String imageUrl = object.getString("thumbnail");
                                String title = object.getString("title");

                                HotNewsBean bean = new HotNewsBean(newsId, imageUrl, title);
                                beanList.add(bean);
                            }

                            HotNewsAdapter adapter = new HotNewsAdapter(getActivity(), beanList);
                            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                            hotRecycler.setLayoutManager(manager);
                            hotRecycler.setAdapter(adapter);
                            adapter.setItemClickListener(new CustomClickListener() {
                                @Override
                                public void OnItemClick(View v, int position) {
                                    Intent intent = new Intent(getActivity(), ContentActivity.class);
                                    intent.putExtra("id", beanList.get(position).getNews_id());
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
                Log.e("ERROR!",error.getMessage());
            }
        });
        queue.add(request);
    }

    private void initViews(View view) {
        hotRecycler = (RecyclerView) view.findViewById(R.id.hot_recycler);
    }
}
