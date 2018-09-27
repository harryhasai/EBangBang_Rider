package com.pywl.ebangbang_rider.function.main;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.blankj.utilcode.util.ToastUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.home.HomeFragment;
import com.pywl.ebangbang_rider.function.personal_center.PersonalCenterFragment;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationView bottomNavigationView;

    private HomeFragment homeFragment;
    private PersonalCenterFragment personalCenterFragment;

    @Override
    protected int setupView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        setupBottomNavigationBar();

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
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
