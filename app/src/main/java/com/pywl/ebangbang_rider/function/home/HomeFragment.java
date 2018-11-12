package com.pywl.ebangbang_rider.function.home;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.event_bus.ReceiveMessageEvent;
import com.pywl.ebangbang_rider.function.home_be_sending_out.HomeBeSendingOutFragment;
import com.pywl.ebangbang_rider.function.home_new_task.HomeNewTaskFragment;
import com.pywl.ebangbang_rider.function.home_waiting_for_goods.HomeWaitingForGoodsFragment;
import com.pywl.ebangbang_rider.function.message.MessageActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/9/27.
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tv_message_count)
    TextView tvMessageCount;
    @BindView(R.id.ll_Message)
    LinearLayout llMessage;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    Unbinder unbinder;

    private List<Fragment> fragmentList;

    @Override
    protected int setupView() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initFragmentList();
        initConfig();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initFragmentList() {
        fragmentList = new ArrayList<>();
//        HomeNewTaskFragment homeNewTaskFragment = new HomeNewTaskFragment();
        HomeBeSendingOutFragment homeBeSendingOutFragment = new HomeBeSendingOutFragment();
        HomeWaitingForGoodsFragment homeWaitingForGoodsFragment = new HomeWaitingForGoodsFragment();
//        fragmentList.add(homeNewTaskFragment);
        fragmentList.add(homeWaitingForGoodsFragment);
        fragmentList.add(homeBeSendingOutFragment);
    }

    private void initConfig() {
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new HomePagerAdapter(getFragmentManager()));

        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.select_tab_text));
        tabLayout.setSelectedTabIndicatorHeight(0);    // 下方滚动的下划线高度
//        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tab_text_color_selected));  // 下方滚动的下划线颜色
        tabLayout.setupWithViewPager(viewPager);

    }

    @OnClick({R.id.ll_Message})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_Message:   //消息
                startActivity(new Intent(mActivity, MessageActivity.class));
                break;
        }
    }

    private class HomePagerAdapter extends FragmentPagerAdapter {

        private String[] tabNames;

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
            tabNames = mActivity.getResources().getStringArray(R.array.home_tab_name);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }
    }

}
