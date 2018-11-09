package com.pywl.ebangbang_rider.function.home_be_sending_out.detail;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.HomeWaitingForGoodsEntity;
import com.pywl.ebangbang_rider.utils.RxPermissionsUtils;
import com.pywl.ebangbang_rider.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/9/29.
 * 首页 - 正在派送中 - 详情
 */
public class HomeBeSendingOutDetailActivity extends BaseActivity {

    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_waiting_time)
    TextView tvWaitingTime;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_remark)
    TextView tvRemark;
    @BindView(R.id.tv_name_and_phone)
    TextView tvNameAndPhone;
    @BindView(R.id.tv_receive_address)
    TextView tvReceiveAddress;
    @BindView(R.id.iv_call_phone)
    ImageView ivCallPhone;

    private double addresseeLatitude;
    private double addresseeLongitude;
    private MapView mMapView;


    @Override
    protected int setupView() {
        return R.layout.activity_home_be_sending_out_detail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        //询问用户注册权限
        for (String aPermissionArray : RxPermissionsUtils.permissionArray) {
            boolean isRegister = RxPermissionsUtils.checkPermissions(this, aPermissionArray);
            if (!isRegister) {
                RxPermissionsUtils.registerPermissions(this);
            }
        }

        tvTitle.setText("派送详情");
        setupData();
        alertGPSWarning();
        initArrowView();
        initMap();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    private void alertGPSWarning() {
        if (!isOPen(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("检测到您当前未开启GPS, 是否去设置开启?")
                    .setCancelable(false)
                    .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                            dialog.dismiss();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ToastUtils.showShort("为了提高您的定位精准度, 建议您开启GPS定位");
                    dialog.dismiss();
                }
            }).show();
        }
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context context
     * @return true 表示开启
     */
    private boolean isOPen(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps || network;
    }

    private void initArrowView() {
        final ImageView ivArrow = findViewById(R.id.iv_arrow);
        final LinearLayout llMore = findViewById(R.id.ll_more);
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
    }

    /**
     * 设置数据给控件
     */
    private void setupData() {
        HomeWaitingForGoodsEntity.DataBean bean = (HomeWaitingForGoodsEntity.DataBean) getIntent().getSerializableExtra("data");
        for (HomeWaitingForGoodsEntity.DataBean.GoodsBean childBean : bean.goods) {
            View view = View.inflate(this, R.layout.item_food_detail, null);
            ImageView ivFoodImage = view.findViewById(R.id.iv_food_image);
            TextView tvFoodName = view.findViewById(R.id.tv_food_name);
            TextView tvFoodMoney = view.findViewById(R.id.tv_food_money);
            TextView tvFoodCount = view.findViewById(R.id.tv_food_count);
            Picasso.with(this)
                    .load(SPUtils.getString(UserInfo.BASE_HEADER.name(), "") + childBean.imgLink)
                    .error(R.drawable.ic_food)
                    //.transform(new PicassoCircleTransform())
                    //.resize(ConvertUtils.dp2px(65), ConvertUtils.dp2px(65))
                    //.centerCrop()
                    .into(ivFoodImage);
            tvFoodName.setText(childBean.goodsName);
            tvFoodMoney.setText("¥" + childBean.goodsPrice);
            tvFoodCount.setText("x " + childBean.num);
            llContainer.addView(view);
        }

        long waitingTime = System.currentTimeMillis() - bean.getCreateTime;
        tvWaitingTime.setText("顾客已等" + waitingTime / 1000 / 60 + "分钟");
        tvName.setText(bean.shopName);
        tvAddress.setText("地址: " + bean.shopSitedDetail);
        tvMoney.setText("¥" + bean.DispatchingMoney);
        tvRemark.setText(bean.remark);
        tvNameAndPhone.setText(bean.addresseeName + " " + bean.addresseePhone);
        tvReceiveAddress.setText("地址: " + bean.addresseeSite);

        addresseeLatitude = Double.parseDouble(bean.addresseeLatitude);
        addresseeLongitude = Double.parseDouble(bean.addresseeLongitude);

    }

    @OnClick({R.id.ll_back, R.id.iv_call_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_call_phone:
                if (!RxPermissionsUtils.checkPermissions(this, Manifest.permission.CALL_PHONE)) {
                    ToastUtils.showShort("拨打电话权限被拒绝, 无法使用该功能");
                    return;
                } else {
                    callPhone("123456");
                }
                break;
        }
    }

    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    private void initMap() {
        //获取地图控件引用
        mMapView = findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        AMap aMap = mMapView.getMap();

        //绘制标记点
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(new LatLng(addresseeLatitude, addresseeLongitude));//位置
        markerOptions.draggable(false);//是否可拖拽
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_style));//自定义图标
        aMap.addMarker(markerOptions);

        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(addresseeLatitude, addresseeLongitude), 15f));
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
}
