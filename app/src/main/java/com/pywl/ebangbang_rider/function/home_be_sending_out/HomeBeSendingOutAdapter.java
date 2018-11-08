package com.pywl.ebangbang_rider.function.home_be_sending_out;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.network.entity.CommonEntity;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Harry on 2018/9/27.
 */
public class HomeBeSendingOutAdapter extends BaseQuickAdapter<HomeWaitingForGoodsEntity.DataBean, BaseViewHolder> {

    private Context mContext;

    public HomeBeSendingOutAdapter(int layoutResId, @Nullable List<HomeWaitingForGoodsEntity.DataBean> data, Context context) {
        super(layoutResId, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeWaitingForGoodsEntity.DataBean item) {
        final ImageView ivArrow = helper.getView(R.id.iv_arrow);
        final LinearLayout llMore = helper.getView(R.id.ll_more);
        ivArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llMore.getVisibility() == View.GONE) {
                    llMore.setVisibility(View.VISIBLE);
                    ivArrow.setRotation(90);
                } else {
                    llMore.setVisibility(View.GONE);
                    ivArrow.setRotation(0);
                }
            }
        });
        helper.addOnClickListener(R.id.iv_to_detail);

        LinearLayout container = helper.getView(R.id.ll_container);
        for (HomeWaitingForGoodsEntity.DataBean.GoodsBean bean : item.goods) {
            View view = View.inflate(mContext, R.layout.item_food_detail, null);
            ImageView ivFoodImage = view.findViewById(R.id.iv_food_image);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodMoney = view.findViewById(R.id.tv_food_money);
            TextView tvFoodCount = view.findViewById(R.id.tv_food_count);
            Picasso.with(mContext)
                    .load(SPUtils.getString(UserInfo.BASE_HEADER.name(), "") + bean.imgLink)
                    .error(R.drawable.ic_food)
                    //.transform(new PicassoCircleTransform())
                    //.resize(ConvertUtils.dp2px(65), ConvertUtils.dp2px(65))
                    //.centerCrop()
                    .into(ivFoodImage);
            tvFoodName.setText(bean.goodsName);
            tvFoodMoney.setText("¥" + bean.goodsPrice);
            tvFoodCount.setText("x " + bean.num);
            container.addView(view);
        }

        long waitingTime = System.currentTimeMillis() - item.getCreateTime;
        helper.setText(R.id.tv_waiting_time, "顾客已等" + waitingTime / 1000 / 60 + "分钟")
                .setText(R.id.tv_name, item.shopName)
                .setText(R.id.tv_address, "地址: " + item.shopSitedDetail)
                .setText(R.id.tv_money, "¥" + item.DispatchingMoney)
                .setText(R.id.tv_remark, item.remark)
                .setText(R.id.tv_name_and_phone, item.addresseeName + " " + item.addresseePhone)
                .setText(R.id.tv_receive_address, "地址: " + item.addresseeSite);

    }
}
