package com.pywl.ebangbang_rider.function.personal_center;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.data_setting.DataSettingActivity;
import com.pywl.ebangbang_rider.function.personal_information.PersonalInformationActivity;
import com.pywl.ebangbang_rider.utils.RxPermissionsUtils;
import com.ruffian.library.RTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Harry on 2018/9/27.
 * 个人中心
 */
public class PersonalCenterFragment extends BaseFragment {

    @BindView(R.id.iv_user_header)
    ImageView ivUserHeader;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_pending_distribution)
    RTextView tvPendingDistribution;
    @BindView(R.id.tv_completed)
    RTextView tvCompleted;
    @BindView(R.id.tv_be_replaced)
    RTextView tvBeReplaced;
    @BindView(R.id.fl_money)
    FrameLayout flMoney;
    @BindView(R.id.fl_personal)
    FrameLayout flPersonal;
    @BindView(R.id.fl_account)
    FrameLayout flAccount;
    @BindView(R.id.fl_setting)
    FrameLayout flSetting;
    Unbinder unbinder;

    @Override
    protected int setupView() {
        return R.layout.fragment_personal_center;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        checkPermissions();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限
        PictureFileUtils.deleteCacheDirFile(mActivity); // TODO: 2018/9/30 暂时先放在这里
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

    @OnClick({R.id.iv_user_header, R.id.tv_pending_distribution,
            R.id.tv_completed, R.id.tv_be_replaced, R.id.fl_money, R.id.fl_personal,
            R.id.fl_account, R.id.fl_setting, R.id.fl_data_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_user_header:   //头像
                pictureSelector();
                break;
            case R.id.tv_pending_distribution:      //待配送
                break;
            case R.id.tv_completed:     //已完成
                break;
            case R.id.tv_be_replaced:       //被退换
                break;
            case R.id.fl_money:     //金额管理
                break;
            case R.id.fl_personal:      //个人信息
                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
                break;
            case R.id.fl_account:       //账号管理
                break;
            case R.id.fl_setting:       //接单设置
                break;
            case R.id.fl_data_setting:   //资料设置:
                startActivity(new Intent(mActivity, DataSettingActivity.class));
                break;
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
                .enableCrop(true)// 是否裁剪 true or false
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
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    ToastUtils.showShort("选择完图片回调成功");
                    break;
            }
    }
}
