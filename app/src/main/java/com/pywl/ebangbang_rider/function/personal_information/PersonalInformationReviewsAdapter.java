package com.pywl.ebangbang_rider.function.personal_information;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.network.entity.PersonalInformationMultiItem;

import java.util.List;

/**
 * Created by Harry on 2018/10/8.
 */
public class PersonalInformationReviewsAdapter extends BaseMultiItemQuickAdapter<PersonalInformationMultiItem, BaseViewHolder> {

    private Activity mActivity;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public PersonalInformationReviewsAdapter(List<PersonalInformationMultiItem> data, Activity activity) {
        super(data);
        addItemType(PersonalInformationMultiItem.TOP, R.layout.item_personal_information_top);
        addItemType(PersonalInformationMultiItem.CENTER, R.layout.item_personal_information_center);
        addItemType(PersonalInformationMultiItem.BOTTOM, R.layout.item_personal_information_bottom);
        this.mActivity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, PersonalInformationMultiItem item) {
        switch (helper.getItemViewType()) {
            case PersonalInformationMultiItem.TOP:  //上半部分
                setupTopSwitch(helper);
                break;
            case PersonalInformationMultiItem.CENTER://中部
                setupCenterSwitch(helper);
                break;
            case PersonalInformationMultiItem.BOTTOM://底部列表

                break;

            default:
                break;
        }
    }

    private void setupCenterSwitch(BaseViewHolder helper) {
        final TextView tvCustomerReviews = helper.getView(R.id.tv_customer_reviews);//顾客评价
        final ImageView ivCustomerReviews = helper.getView(R.id.iv_customer_reviews);
        final TextView tvBusinessReviews = helper.getView(R.id.tv_business_reviews);//商家评价
        final ImageView ivBusinessReviews = helper.getView(R.id.iv_business_reviews);
        final TextView tvAllReviews = helper.getView(R.id.tv_all_reviews);//全部
        final TextView tvSatisfactionReviews = helper.getView(R.id.tv_satisfaction_reviews);//满意
        final TextView tvDiscontentReviews = helper.getView(R.id.tv_discontent_reviews);//不满意

        tvCustomerReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivCustomerReviews.getVisibility() == View.GONE) {
                    tvCustomerReviews.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
                    tvBusinessReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    ivCustomerReviews.setVisibility(View.VISIBLE);
                    ivBusinessReviews.setVisibility(View.GONE);
                }
            }
        });

        tvBusinessReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivBusinessReviews.getVisibility() == View.GONE) {
                    tvCustomerReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvBusinessReviews.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
                    ivCustomerReviews.setVisibility(View.GONE);
                    ivBusinessReviews.setVisibility(View.VISIBLE);
                }
            }
        });

        tvAllReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvAllReviews.getBackground() != mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
                    tvAllReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
                    tvAllReviews.setTextColor(mActivity.getResources().getColor(R.color.white));
                    tvSatisfactionReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvSatisfactionReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvDiscontentReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvDiscontentReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                }
            }
        });

        tvSatisfactionReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvSatisfactionReviews.getBackground() != mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
                    tvAllReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvAllReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvSatisfactionReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
                    tvSatisfactionReviews.setTextColor(mActivity.getResources().getColor(R.color.white));
                    tvDiscontentReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvDiscontentReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                }
            }
        });

        tvDiscontentReviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tvDiscontentReviews.getBackground() != mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432)) {
                    tvAllReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvAllReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvSatisfactionReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp));
                    tvSatisfactionReviews.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvDiscontentReviews.setBackground(mActivity.getDrawable(R.drawable.shape_round_rectangle_18dp_colorb73432));
                    tvDiscontentReviews.setTextColor(mActivity.getResources().getColor(R.color.white));
                }
            }
        });
    }

    private void setupTopSwitch(BaseViewHolder helper) {
        final TextView tvTodayRecord = helper.getView(R.id.tv_today_record);//今日战绩
        final ImageView ivTodayRecord = helper.getView(R.id.iv_today_record);
        final TextView tvThisMonthRecord = helper.getView(R.id.tv_this_month_record);//本月战绩
        final ImageView ivThisMonthRecord = helper.getView(R.id.iv_this_month_record);

        tvTodayRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivTodayRecord.getVisibility() == View.GONE) {
                    tvTodayRecord.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
                    tvThisMonthRecord.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    ivTodayRecord.setVisibility(View.VISIBLE);
                    ivThisMonthRecord.setVisibility(View.GONE);
                }
            }
        });

        tvThisMonthRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ivThisMonthRecord.getVisibility() == View.GONE) {
                    tvTodayRecord.setTextColor(mActivity.getResources().getColor(R.color.black2));
                    tvThisMonthRecord.setTextColor(mActivity.getResources().getColor(R.color.app_status_bar_color));
                    ivTodayRecord.setVisibility(View.GONE);
                    ivThisMonthRecord.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
