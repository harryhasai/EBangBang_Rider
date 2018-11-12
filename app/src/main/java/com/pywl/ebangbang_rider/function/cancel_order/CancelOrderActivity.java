package com.pywl.ebangbang_rider.function.cancel_order;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.function.home_be_sending_out.detail.HomeBeSendingOutDetailActivity;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/12.
 * 被退换
 */
public class CancelOrderActivity extends BaseActivity<CancelOrderPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private List<HomeWaitingForGoodsEntity.DataBean> mList;
    private CancelOrderAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.activity_cancel_order;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        mList = new ArrayList<>();
        tvTitle.setText("已完成");

        initSwipeRefreshLayout();
        initRecyclerView();

        mPresenter.getDataList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.CANCEL_ORDER_ACTIVITY_GET_DATA_LIST);
        return tags;
    }

    @Override
    protected CancelOrderPresenter bindPresenter() {
        return new CancelOrderPresenter();
    }

    private void initSwipeRefreshLayout() {
        // 设置下拉进度的背景颜色，默认就是白色的
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDataList();
            }
        });
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CancelOrderAdapter(R.layout.item_cancel_order, mList, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeWaitingForGoodsEntity.DataBean bean = mList.get(position);
                switch (view.getId()) {
                    case R.id.iv_to_detail:
                        Intent intent = new Intent(CancelOrderActivity.this, HomeBeSendingOutDetailActivity.class);
                        intent.putExtra("data", bean);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    /**
     * @param data 从网络获取到的数据
     */
    public void getDataList(List<HomeWaitingForGoodsEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
