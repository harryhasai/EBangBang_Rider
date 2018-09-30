package com.pywl.ebangbang_rider.function.home_be_sending_out.detail;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

/**
 * Created by Harry on 2018/9/29.
 * 首页 - 正在派送中 - 详情
 */
public class HomeBeSendingOutDetailActivity extends BaseActivity {

    private MapView mapView;
    private AMapLocationClient mLocationClient;
    private LocationSource.OnLocationChangedListener mLocationChangedListener;
    private AMap aMap;
    // 标识首次定位
    private boolean isFirstLocation = true;


    @Override
    protected int setupView() {
        return R.layout.activity_home_be_sending_out_detail;
    }

    @Override
    protected void initView() {
        alertGPSWarning();
        initArrowView();
        initMap();
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
                            intent.setAction(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
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

    private void initMap() {
        mapView = findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        //初始化地图控制器对象
        aMap = mapView.getMap();
        // 隐藏缩放按钮
        aMap.getUiSettings().setZoomControlsEnabled(false);
        // 设置地图默认的指南针是否显示
        aMap.getUiSettings().setCompassEnabled(false);
        // 设置定位监听
        aMap.setLocationSource(new MyLocationSource());
        // 设置默认定位按钮是否显示
        aMap.getUiSettings().setMyLocationButtonEnabled(true);
        // 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        aMap.setMyLocationEnabled(true);
        aMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
        MyLocationStyle myLocationStyle = new MyLocationStyle();//设置定位蓝点的样式
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.ic_map_location_style));
        //自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(1);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(getResources().getColor(android.R.color.transparent));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(getResources().getColor(android.R.color.transparent));
        aMap.setMyLocationStyle(myLocationStyle);
        initGaoDeMap();
    }

    /**
     * 初始化高德地图参数
     */
    public void initGaoDeMap() {
        // 初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        // 设置高德地图定位回调监听
        mLocationClient.setLocationListener(new MyAMapLocationListener());
        // 初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        // 高精度定位模式：会同时使用网络定位和GPS定位，优先返回最高精度的定位结果，以及对应的地址描述信息
        // 设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // SDK默认采用连续定位模式，时间间隔2000ms
        // 设置定位间隔，单位毫秒，默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        // 设置定位同时是否需要返回地址描述
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        // 设置是否强制刷新WIFI，默认为强制刷新。每次定位主动刷新WIFI模块会提升WIFI定位精度，但相应的会多付出一些电量消耗。
        // 设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(true);
        // 设置是否允许模拟软件Mock位置结果，多为模拟GPS定位结果，默认为false，不允许模拟位置。
        // 设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        // 设置定位请求超时时间，默认为30秒
        // 单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(30000);
        // 设置是否开启定位缓存机制
        // 缓存机制默认开启，可以通过以下接口进行关闭。
        // 当开启定位缓存功能，在高精度模式和低功耗模式下进行的网络定位结果均会生成本地缓存，不区分单次定位还是连续定位。GPS定位结果不会被缓存。
        // 关闭缓存机制
        mLocationOption.setLocationCacheEnable(true);
        // 设置是否只定位一次，默认为false
        mLocationOption.setOnceLocation(false);
        // 给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        // 启动高德地图定位
        mLocationClient.startLocation();
    }

    private class MyLocationSource implements LocationSource {
        @Override
        public void activate(OnLocationChangedListener onLocationChangedListener) {
            mLocationChangedListener = onLocationChangedListener;
        }

        @Override
        public void deactivate() {
            mLocationChangedListener = null;
        }
    }

    /**
     * 定位回调
     */
    private class MyAMapLocationListener implements AMapLocationListener {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            // 解析AMapLocation对象
            // 判断AMapLocation对象不为空，当定位错误码类型为0时定位成功
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
//                    aMapLocation.getLocationType(); // 获取当前定位结果来源，如网络定位结果，详见定位类型表
//                    aMapLocation.getLatitude(); // 获取纬度
//                    aMapLocation.getLongitude(); // 获取经度
//                    aMapLocation.getAccuracy(); // 获取精度信息
//                    aMapLocation.getAddress(); // 地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    aMapLocation.getCountry(); // 国家信息
//                    aMapLocation.getProvince(); // 省信息
//                    aMapLocation.getCity(); // 城市信息
//                    aMapLocation.getDistrict(); // 城区信息
//                    aMapLocation.getStreet(); // 街道信息
//                    aMapLocation.getStreetNum(); // 街道门牌号信息
//                    aMapLocation.getCityCode(); // 城市编码
//                    aMapLocation.getAdCode(); // 地区编码
//                    aMapLocation.getAoiName(); // 获取当前定位点的AOI信息
//                    aMapLocation.getBuildingId(); // 获取当前室内定位的建筑物Id
//                    aMapLocation.getFloor(); // 获取当前室内定位的楼层
//                    aMapLocation.getGpsAccuracyStatus(); // 获取GPS的当前状态
//                    // 获取定位时间
//                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    Date date = new Date(aMapLocation.getTime());
//                    df.format(date);
                    if (isFirstLocation) {
                        // 设置缩放级别
                        aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                        // 将地图移动到定位点
                        aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                        // 点击定位按钮 能够将地图的中心移动到定位点
                        mLocationChangedListener.onLocationChanged(aMapLocation);
                        isFirstLocation = false;
                    }
                } else {
                    // 定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("HLQ_Struggle", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    }


    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }
}
