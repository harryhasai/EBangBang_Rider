package com.pywl.ebangbang_rider.function.feedback;

import android.graphics.BitmapFactory;
import android.view.View;

import com.luck.picture.lib.photoview.PhotoView;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Harry on 2018/11/10.
 * 反馈问题图片预览Activity
 */
public class FeedbackPreviewActivity extends BaseActivity {
    @Override
    protected int setupView() {
        return R.layout.activity_feedback_preview;
    }

    @Override
    protected void initView() {
        PhotoView ivPreview = findViewById(R.id.iv_preview);
        String path = getIntent().getStringExtra("path");
        ivPreview.setImageBitmap(BitmapFactory.decodeFile(path));
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }
}
