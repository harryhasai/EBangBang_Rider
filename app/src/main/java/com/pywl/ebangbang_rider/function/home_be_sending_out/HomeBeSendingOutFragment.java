package com.pywl.ebangbang_rider.function.home_be_sending_out;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.home_be_sending_out.detail.HomeBeSendingOutDetailActivity;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/9/27.
 * 首页 - 正在派送中
 */
public class HomeBeSendingOutFragment extends BaseFragment<HomeBeSendingOutPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private List<HomeWaitingForGoodsEntity.DataBean> mList;
    private HomeBeSendingOutAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_home_be_sending_out;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        mList = new ArrayList<>();

        initSwipeRefreshLayout();
        initRecyclerView();

        mPresenter.getDataList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_BE_SENDING_OUT_GET_DATA_LIST);
        tags.add(DisposableFinal.HOME_BE_SENDING_OUT_COMPLETE);
        return tags;
    }

    @Override
    protected HomeBeSendingOutPresenter bindPresenter() {
        return new HomeBeSendingOutPresenter();
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
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        adapter = new HomeBeSendingOutAdapter(R.layout.item_home_be_sending_out, mList, mActivity);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                HomeWaitingForGoodsEntity.DataBean bean = mList.get(position);
                switch (view.getId()) {
                    case R.id.iv_to_detail:
                        Intent intent = new Intent(mActivity, HomeBeSendingOutDetailActivity.class);
                        intent.putExtra("data", bean);
                        startActivity(intent);
                        break;
                    case R.id.btn_complete:
                        mPresenter.complete(String.valueOf(bean.id));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
