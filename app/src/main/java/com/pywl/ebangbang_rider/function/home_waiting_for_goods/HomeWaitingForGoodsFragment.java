package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.CommonItem;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/9/27.
 * 首页 - 待取货
 */
public class HomeWaitingForGoodsFragment extends BaseFragment<HomeWaitingForGoodsPresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    Unbinder unbinder;

    private List<HomeWaitingForGoodsEntity.DataBean> mList;
    private HomeWaitingForGoodsAdapter adapter;

    @Override
    protected int setupView() {
        return R.layout.fragment_home_waiting_for_goods;
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
        tags.add(DisposableFinal.HOME_WAITING_FOR_GOODS_FRAGMENT_GET_DATA_LIST);
        return tags;
    }

    @Override
    protected HomeWaitingForGoodsPresenter bindPresenter() {
        return new HomeWaitingForGoodsPresenter();
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
        adapter = new HomeWaitingForGoodsAdapter(R.layout.item_home_waiting_for_goods, mList, mActivity);
        recyclerView.setAdapter(adapter);

    }

    public void stopRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * @param data 从网络获取到的数据
     */
    public void getDataList(List<HomeWaitingForGoodsEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }
}
