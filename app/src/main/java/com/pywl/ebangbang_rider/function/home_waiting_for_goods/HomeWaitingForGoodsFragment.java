package com.pywl.ebangbang_rider.function.home_waiting_for_goods;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.event_bus.ReceiveMessageEvent;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
        EventBus.getDefault().register(this);

        mList = new ArrayList<>();
        initSwipeRefreshLayout();
        initRecyclerView();

        mPresenter.getDataList();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.HOME_WAITING_FOR_GOODS_FRAGMENT_GET_DATA_LIST);
        tags.add(DisposableFinal.HOME_WAITING_FOR_GOODS_FRAGMENT_ARRIVAL);
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

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.btn_call_phone:  //联系
                        if (mList.size() != 0) {
                            mPresenter.callPhone(mList.get(position).shopContactsPhone);
                        }
                        break;
                    case R.id.btn_arrival:  //上报到店
                        if (mList.size() != 0) {
                            mPresenter.arrival(String.valueOf(mList.get(position).id));
                        }
                        break;
                }
            }
        });
    }

    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }

    /**
     * 由于setRefreshing(boolean)方法将mNotify置为false，所以必然不会执行到mListener.onRefresh()方法。
     * 如果想通过手动设置刷新并且触发事件则需要调用
     * private void setRefreshing(boolean refreshing, final boolean notify)
     */
    public void setRefreshing() {
        try {
            Class clazz = swipeRefreshLayout.getClass();
            Method method = clazz.getDeclaredMethod("setRefreshing", boolean.class, boolean.class);
            method.setAccessible(true);
            method.invoke(swipeRefreshLayout, true, true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    /**
     * @param data 从网络获取到的数据
     */
    public void getDataList(List<HomeWaitingForGoodsEntity.DataBean> data) {
        mList.clear();
        mList.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessage(ReceiveMessageEvent event) {
        if (!TextUtils.isEmpty(event.getMessage())) {
            //接收到消息 刷新待取货的列表
//            mPresenter.getDataList();
            setRefreshing();
        }
    }
}
