package com.pywl.ebangbang_rider.function.personal_information;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.PersonalInformationMultiItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/10/8.
 * 个人信息页面
 */
public class PersonalInformationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<PersonalInformationMultiItem> mList;

    @Override
    protected int setupView() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("个人信息");

        initRecyclerView();
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    private void initRecyclerView() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.TOP));
            } else if (i == 1) {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.CENTER));
            } else {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.BOTTOM));
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PersonalInformationReviewsAdapter adapter = new PersonalInformationReviewsAdapter(mList, this);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
//            case R.id.tv_today_record://今日战绩
//                if (ivTodayRecord.getVisibility() == View.GONE) {
//                    tvTodayRecord.setTextColor(getResources().getColor(R.color.app_status_bar_color));
//                    tvThisMonthRecord.setTextColor(getResources().getColor(R.color.black2));
//                    ivTodayRecord.setVisibility(View.VISIBLE);
//                    ivThisMonthRecord.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.tv_this_month_record://本月战绩
//                if (ivThisMonthRecord.getVisibility() == View.GONE) {
//                    tvTodayRecord.setTextColor(getResources().getColor(R.color.black2));
//                    tvThisMonthRecord.setTextColor(getResources().getColor(R.color.app_status_bar_color));
//                    ivTodayRecord.setVisibility(View.GONE);
//                    ivThisMonthRecord.setVisibility(View.VISIBLE);
//                }
//                break;
//            case R.id.tv_customer_reviews://顾客评价
//                if (ivCustomerReviews.getVisibility() == View.GONE) {
//                    tvCustomerReviews.setTextColor(getResources().getColor(R.color.app_status_bar_color));
//                    tvBusinessReviews.setTextColor(getResources().getColor(R.color.black2));
//                    ivCustomerReviews.setVisibility(View.VISIBLE);
//                    ivBusinessReviews.setVisibility(View.GONE);
//                }
//                break;
//            case R.id.tv_business_reviews://商家评价
//                if (ivBusinessReviews.getVisibility() == View.GONE) {
//                    tvCustomerReviews.setTextColor(getResources().getColor(R.color.black2));
//                    tvBusinessReviews.setTextColor(getResources().getColor(R.color.app_status_bar_color));
//                    ivCustomerReviews.setVisibility(View.GONE);
//                    ivBusinessReviews.setVisibility(View.VISIBLE);
//                }
//                break;
//            case R.id.tv_all_reviews://全部
//                if (tvAllReviews.getBackground() != getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
//                    tvAllReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
//                    tvAllReviews.setTextColor(getResources().getColor(R.color.white));
//                    tvSatisfactionReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvSatisfactionReviews.setTextColor(getResources().getColor(R.color.black2));
//                    tvDiscontentReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvDiscontentReviews.setTextColor(getResources().getColor(R.color.black2));
//                }
//                break;
//            case R.id.tv_satisfaction_reviews://满意
//                if (tvSatisfactionReviews.getBackground() != getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
//                    tvAllReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvAllReviews.setTextColor(getResources().getColor(R.color.black2));
//                    tvSatisfactionReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
//                    tvSatisfactionReviews.setTextColor(getResources().getColor(R.color.white));
//                    tvDiscontentReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvDiscontentReviews.setTextColor(getResources().getColor(R.color.black2));
//                }
//                break;
//            case R.id.tv_discontent_reviews://不满意
//                if (tvDiscontentReviews.getBackground() != getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
//                    tvAllReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvAllReviews.setTextColor(getResources().getColor(R.color.black2));
//                    tvSatisfactionReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp));
//                    tvSatisfactionReviews.setTextColor(getResources().getColor(R.color.black2));
//                    tvDiscontentReviews.setBackground(getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
//                    tvDiscontentReviews.setTextColor(getResources().getColor(R.color.white));
//                }
//                break;
        }
    }

}
