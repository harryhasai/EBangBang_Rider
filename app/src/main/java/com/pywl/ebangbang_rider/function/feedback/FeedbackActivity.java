package com.pywl.ebangbang_rider.function.feedback;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.DisposableFinal;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.utils.ImageUtil;
import com.pywl.ebangbang_rider.utils.RxPermissionsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/11/10.
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity<FeedbackPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_desc)
    EditText etDesc;
    @BindView(R.id.iv_photo)
    ImageView ivPhoto;
    @BindView(R.id.iv_preview)
    ImageView ivPreview;
    /**
     * 原图路径
     */
    private String path;
    /**
     * 压缩后的图片路径
     */
    private String compressPath;

    @Override
    protected int setupView() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("意见反馈");
        checkPermissions();

        ivPreview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ivPreview.setImageBitmap(null);
                ivPreview.setVisibility(View.GONE);//长按删除图片
                return true;
            }
        });
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.FEEDBACK_COMMIT);
        return tags;
    }

    @Override
    protected FeedbackPresenter bindPresenter() {
        return new FeedbackPresenter();
    }

    @OnClick({R.id.ll_back, R.id.iv_photo, R.id.iv_preview, R.id.btn_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_photo:
                pictureSelector();
                break;
            case R.id.iv_preview:
                Intent intent = new Intent(this, FeedbackPreviewActivity.class);
                intent.putExtra("path", path);
                startActivity(intent);
                break;
            case R.id.btn_commit:
                String desc = etDesc.getText().toString().trim();
                if (TextUtils.isEmpty(desc)) {
                    ToastUtils.showShort("问题描述不能为空");
                    return;
                }
                if (TextUtils.isEmpty(compressPath)) {
                    mPresenter.commit(desc, null);
                } else {
                    mPresenter.commit(desc, ImageUtil.image2Base64(compressPath));
                }
                break;
        }
    }

    /**
     * 检查权限
     */
    private void checkPermissions() {
        boolean isGranted = RxPermissionsUtils.checkPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                && RxPermissionsUtils.checkPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                && RxPermissionsUtils.checkPermissions(this, Manifest.permission.CAMERA);
        if (!isGranted) {
            RxPermissionsUtils.registerPermissions(this);
        }
    }

    private void pictureSelector() {
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
//                .theme()//主题样式(不设置为默认样式) 也可参考demo values/styles下 例如：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量 int
                .minSelectNum(1)// 最小选择数量 int
                .imageSpanCount(4)// 每行显示个数 int
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片 true or false
//                .previewVideo(false)// 是否可预览视频 true or false
//                .enablePreviewAudio(false) // 是否可播放音频 true or false
                .isCamera(true)// 是否显示拍照按钮 true or false
                .imageFormat(PictureMimeType.PNG)// 拍照保存图片格式后缀,默认jpeg
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                .sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .setOutputCameraPath("/CustomPath")// 自定义拍照保存路径,可不填
                .enableCrop(false)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
//                .glideOverride()// int glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio()// int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示 true or false
//                .isGif()// 是否显示gif图片 true or false
                .compressSavePath("/CustomPath")//压缩图片保存地址
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽 true or false
                .circleDimmedLayer(false)// 是否圆形裁剪 true or false
                .showCropFrame(true)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
                .openClickSound(false)// 是否开启点击声音 true or false
//                .selectionMedia()// 是否传入已选图片 List<LocalMedia> list
                .previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中) true or false
//                .cropCompressQuality()// 裁剪压缩质量 默认90 int
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .synOrAsy(false)//同步true或异步false 压缩 默认同步
//                .cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效 int
                .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
                .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
//                .videoQuality()// 视频录制质量 0 or 1 int
//                .videoMaxSecond(15)// 显示多少秒以内的视频or音频也可适用 int
//                .videoMinSecond(10)// 显示多少秒以内的视频or音频也可适用 int
//                .recordVideoSecond()//视频秒数录制 默认60s int
                .isDragFrame(true)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                path = selectList.get(0).getPath();
                if (!TextUtils.isEmpty(path)) {
                    ivPreview.setImageBitmap(BitmapFactory.decodeFile(path));
                    ivPreview.setVisibility(View.VISIBLE);
                }
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                compressPath = selectList.get(0).getCompressPath();
                break;
        }
    }
}
