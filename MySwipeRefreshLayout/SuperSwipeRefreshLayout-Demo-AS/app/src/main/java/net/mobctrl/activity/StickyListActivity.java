package net.mobctrl.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import net.mobctrl.treerecyclerview.R;
import net.mobctrl.views.SuperSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * fd
 * Created by Administrator on 2016/8/23.
 */
public class StickyListActivity  extends Activity{

    private StickyListHeadersListView listView;
    private SuperSwipeRefreshLayout swipeRefreshLayout;
    // Footer View
    private ProgressBar footerProgressBar;
    private TextView footerTextView;
    private ImageView footerImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sticky_listview);
        listView = (StickyListHeadersListView) findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, getData()));
        swipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setFooterView(createFooterView());
//		listView.addFooterView(createFooterView());
        swipeRefreshLayout.setTargetScrollWithLayout(true);
        swipeRefreshLayout
                .setOnPullRefreshListener(new SuperSwipeRefreshLayout.OnPullRefreshListener() {

                    @Override
                    public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 20000);
                    }

                    @Override
                    public void onPullDistance(int distance) {
                        System.out.println("debug:distance = " + distance);
                        // myAdapter.updateHeaderHeight(distance);
                    }

                    @Override
                    public void onPullEnable(boolean enable) {
                    }
                });

        swipeRefreshLayout
                .setOnPushLoadMoreListener(new SuperSwipeRefreshLayout.OnPushLoadMoreListener() {

                    @Override
                    public void onLoadMore() {
                        footerTextView.setText("正在加载...");
                        footerImageView.setVisibility(View.GONE);
                        footerProgressBar.setVisibility(View.VISIBLE);
                        new Handler().postDelayed(new Runnable() {

                            @Override
                            public void run() {
                                footerImageView.setVisibility(View.VISIBLE);
                                footerProgressBar.setVisibility(View.GONE);
                                swipeRefreshLayout.setLoadMore(false);
                            }
                        }, 5000);
                    }

                    @Override
                    public void onPushEnable(boolean enable) {
                        footerTextView.setText(enable ? "松开加载" : "上拉加载");
                        footerImageView.setVisibility(View.VISIBLE);
                        footerImageView.setRotation(enable ? 0 : 180);
                    }

                    @Override
                    public void onPushDistance(int distance) {
                        // TODO Auto-generated method stub

                    }

                });
//		initDatas();
    }

    private View createFooterView() {
        View footerView = LayoutInflater.from(swipeRefreshLayout.getContext())
                .inflate(R.layout.layout_footer, null);
        footerProgressBar = (ProgressBar) footerView
                .findViewById(R.id.footer_pb_view);
        footerImageView = (ImageView) footerView
                .findViewById(R.id.footer_image_view);
        footerTextView = (TextView) footerView
                .findViewById(R.id.footer_text_view);
        footerProgressBar.setVisibility(View.GONE);
        footerImageView.setVisibility(View.VISIBLE);
        footerImageView.setImageResource(R.drawable.down_arrow);
        footerTextView.setText("上拉加载更多...");
        return footerView;
    }
    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("item -- " + i);
        }
        return data;
    }
}
