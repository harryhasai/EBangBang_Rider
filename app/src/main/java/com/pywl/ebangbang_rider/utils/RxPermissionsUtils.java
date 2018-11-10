package com.pywl.ebangbang_rider.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Harry on 2018/9/30.
 * 注册运行时权限
 */
public class RxPermissionsUtils {

    private static final String TAG = "registerPermissions";
    public static String[] permissionArray = {Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @SuppressLint("CheckResult")
    public static void registerPermissions(AppCompatActivity activity) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            ToastUtils.showShort("您拒绝了权限, 可能造成某些功能不能准确的执行");
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            ToastUtils.showShort("您拒绝了权限, 可能造成某些功能不能准确的执行");
                            Log.d(TAG, permission.name + " is denied.");
                        }

                    }
                });
    }

    @SuppressLint("CheckResult")
    public static void registerPermissions(AppCompatActivity activity, Consumer<Permission> permissionConsumer) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.requestEach(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(permissionConsumer);
    }

    @SuppressLint("CheckResult")
    public static void registerPermissions(Fragment fragment) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        rxPermissions.requestEach(Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            ToastUtils.showShort("您拒绝了权限, 可能造成某些功能不能准确的执行");
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            ToastUtils.showShort("您拒绝了权限, 可能造成某些功能不能准确的执行");
                            Log.d(TAG, permission.name + " is denied.");
                        }

                    }
                });
    }

    /**
     * 检测权限
     *
     * @param activity   上下文
     * @param permission 权限
     * @return true 表示已有当前权限
     */
    public static boolean checkPermissions(AppCompatActivity activity, String permission) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        return rxPermissions.isGranted(permission);
    }

    public static boolean checkPermissions(Fragment fragment, String permission) {
        RxPermissions rxPermissions = new RxPermissions(fragment);
        return rxPermissions.isGranted(permission);
    }
}
