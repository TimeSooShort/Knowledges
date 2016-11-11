package com.miao.android.knowledges.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2016/10/9.
 */

public class Ribao extends Fragment {
    /**
    private static final int SHOW_RESPONSE = 0;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerView;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_RESPONSE:
                    InfoBean bean = (InfoBean) msg.obj;
                    List<Fragment> fragmentList = new ArrayList<>();
                    List<InfoBean.Top_Stories> topStoriesLists = bean.getTop_stories();
                    List<Integer> idList = new ArrayList<>();
                    for (int i = 0; i < topStoriesLists.size(); i++) {
                        InfoBean.Top_Stories topStoryItem = bean.getTop_stories().get(i);
                        String title = topStoryItem.getTitle();
                        int id = topStoryItem.getId();
                        String imageUrl = topStoryItem.getImage();
                        TopPictures topPictures = new TopPictures(id, title, imageUrl);
                        fragmentList.add(topPictures);
                        idList.add(id);
                    }
                    MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(
                            getChildFragmentManager(), fragmentList);
                    mViewPager.setAdapter(myViewPagerAdapter);
                    List<InfoBean.Stories> storiesList = bean.getStories();
                    RibaoRecyclerAdapter adapter = new RibaoRecyclerAdapter(getActivity(), storiesList);
                    LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                    mRecyclerView.setLayoutManager(manager);
                    mRecyclerView.setAdapter(adapter);
                    /**
                    adapter.setItemClickListener(new CustomClickListener() {
                        @Override
                        public void OnItemClick(View v, int position) {
                            Intent intent = new Intent(getActivity(), ContentActivity.class);
                            int id = bean.getStories().get(position).getId();
                            intent.putExtra("id", id);
                            startActivity(intent);
                        }
                    });
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ribao_fragment, container, false);
        initViews(view);
        requestData();
        return view;
    }

    private void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://news-at.zhihu.com/api/4/news/latest";
                String method = "GET";
                String response = HttpUtil.sendRequest(method, url);
                Log.e("RESPONSE",response);
                InfoBean bean = parseData(response);
                Message message = new Message();
                message.what = SHOW_RESPONSE;
                message.obj = bean;
                handler.sendMessage(message);
            }
        }).start();
    }

    private InfoBean parseData(String response) {
        InfoBean bean = new InfoBean();
        try {
            JSONObject object = new JSONObject(response);
            int date = object.getInt("date");
            bean.setDate(date);
            JSONArray arrayStories = object.getJSONArray("stories");
            JSONArray arrayTopStories = object.getJSONArray("top_stories");

            List<InfoBean.Stories> storiesList = new ArrayList<>();
            for (int i = 0; i < arrayStories.length(); i++) {
                JSONObject storyItem = arrayStories.getJSONObject(i);
                String title = storyItem.getString("title");
                int id = storyItem.getInt("id");
                JSONArray imageArray = storyItem.getJSONArray("images");
                List<String> imageList = new ArrayList<>();
                for (int j = 0; j < imageArray.length(); j++) {
                    String image = (String) imageArray.get(i);
                    imageList.add(image);
                }
                InfoBean.Stories storiesBean = new InfoBean.Stories();
                storiesBean.setId(id);
                storiesBean.setTitle(title);
                storiesBean.setImages(imageList);

                storiesList.add(storiesBean);
            }
            bean.setStories(storiesList);

            List<InfoBean.Top_Stories> topStoriesList = new ArrayList<>();
            for (int i = 0; i < arrayTopStories.length(); i++) {
                JSONObject topStoriesItem = arrayTopStories.getJSONObject(i);
                String title = topStoriesItem.getString("title");
                int id = topStoriesItem.getInt("id");
                String topImage = topStoriesItem.getString("image");
                InfoBean.Top_Stories topStoriesBean = new InfoBean.Top_Stories();
                topStoriesBean.setImage(topImage);
                topStoriesBean.setTitle(title);
                topStoriesBean.setId(id);

                topStoriesList.add(topStoriesBean);
            }
            bean.setTop_stories(topStoriesList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private void initViews(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.ri_view_pager);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.ri_recycler_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }*/
}
