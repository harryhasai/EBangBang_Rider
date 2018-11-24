package com.pywl.ebangbang_rider.function.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.function.home.HomeFragment;
import com.pywl.ebangbang_rider.function.personal_center.PersonalCenterFragment;
import com.pywl.ebangbang_rider.service.WebSocketService;
import com.pywl.ebangbang_rider.utils.LocationUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private PersonalCenterFragment personalCenterFragment;
    private Intent serviceIntent;
    private LocationUtil locationUtil;
    private double longitude;
    private double latitude;

    @Override
    protected int setupView() {
        return R.layout.activity_main;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        // super.onSaveInstanceState(outState, outPersistentState);
        //不保存因为异常原因丢失掉的界面状态
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

        serviceIntent = new Intent(this, WebSocketService.class);
        startService(serviceIntent);

        setupBottomNavigationBar();

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        initGPS();

    }

    private void initGPS() {
        alertGPSWarning();//提示用户开启GPS
        //注册相关权限

        locationUtil = LocationUtil.getInstance();
        locationUtil.initLocation(getApplicationContext());
        locationUtil.startLocation(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 0) {
                    longitude = aMapLocation.getLongitude();
                    latitude = aMapLocation.getLatitude();
                    mCountDownTimer.start();
                } else {
                    Log.w("MainActivity", "错误码:" + aMapLocation.getErrorCode());
                    Log.w("MainActivity", "错误信息:" + aMapLocation.getErrorInfo());
                    Log.w("MainActivity", "错误描述:" + aMapLocation.getLocationDetail());
                }
            }
        });
    }

    /**
     * 定时器, 不停的往服务器发送位置信息
     */
    CountDownTimer mCountDownTimer = new CountDownTimer(1000 * 60 * 60 * 24 * 10, 1000 * 60) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (longitude != 0 && latitude != 0) {
                mPresenter.postAddress(String.valueOf(longitude), String.valueOf(latitude));
            }
        }

        @Override
        public void onFinish() {
            locationUtil.stopLocation();
        }
    };

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MAIN_ACTIVITY_POST_ADDRESS);
        return tags;
    }

    @Override
    protected MainPresenter bindPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(serviceIntent);
        locationUtil.destroyLocation();
        mCountDownTimer.cancel();
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

    /**
     * 初始化底部导航栏
     */
    private void setupBottomNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();

                if (homeFragment != null) ft.hide(homeFragment);
                if (personalCenterFragment != null) ft.hide(personalCenterFragment);

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        if (homeFragment == null) {
                            homeFragment = new HomeFragment();
                            ft.add(R.id.fl_container, homeFragment);
                        } else {
                            ft.show(homeFragment);
                        }
                        break;
                    case R.id.navigation_my_center:
                        if (personalCenterFragment == null) {
                            personalCenterFragment = new PersonalCenterFragment();
                            ft.add(R.id.fl_container, personalCenterFragment);
                        } else {
                            ft.show(personalCenterFragment);
                        }
                        break;
                }
                ft.commit();
                return true;
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {     //判断当前是否点击的是back键
            exitSystemByDoubleClick();
        }
        return false;
    }

    /**
     * 是否双击退出应用程序
     */
    private boolean isExit = false;

    /**
     * 双击退出应用程序
     */
    private void exitSystemByDoubleClick() {
        Timer timer;
        if (!isExit) {
            isExit = true;
            ToastUtils.showShort("再按一次退出程序");
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; //取消退出
                }
            }, 2000);       // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}
