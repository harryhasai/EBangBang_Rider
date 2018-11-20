package com.pywl.ebangbang_rider.function.personal_center;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ConvertUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.app_final.UserInfo;
import com.pywl.ebangbang_rider.base.BaseFragment;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.function.about.AboutActivity;
import com.pywl.ebangbang_rider.function.cancel_order.CancelOrderActivity;
import com.pywl.ebangbang_rider.function.completed.CompletedActivity;
import com.pywl.ebangbang_rider.function.feedback.FeedbackActivity;
import com.pywl.ebangbang_rider.function.login.LoginActivity;
import com.pywl.ebangbang_rider.function.personal_center.be_sending_out.BeSendingOutActivity;
import com.pywl.ebangbang_rider.function.personal_center.health.HealthActivity;
import com.pywl.ebangbang_rider.function.personal_center.order_taking_manage.OrderTakingManageActivity;
import com.pywl.ebangbang_rider.function.personal_information.PersonalInformationActivity;
import com.pywl.ebangbang_rider.function.rules_of_management.RulesOfManagementActivity;
import com.pywl.ebangbang_rider.utils.PicassoCircleTransform;
import com.pywl.ebangbang_rider.utils.SPUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

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
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    Unbinder unbinder;

    @Override
    protected int setupView() {
        return R.layout.fragment_personal_center;
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);

        initUserInfo();
    }

    private void initUserInfo() {
        Picasso.with(mActivity)
                .load(SPUtils.getString(UserInfo.BASE_HEADER.name(), "") + SPUtils.getString(UserInfo.HEAD_PORTRAIT.name(), ""))
                .error(R.drawable.ic_rider_header)
                .transform(new PicassoCircleTransform())
                .resize(ConvertUtils.dp2px(57), ConvertUtils.dp2px(57))
                .centerCrop()
                .into(ivUserHeader);
        tvUsername.setText(SPUtils.getString(UserInfo.NAME.name(), ""));
        tvPhone.setText(SPUtils.getString(UserInfo.USER_PHONE.name(), ""));
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
        //包括裁剪和压缩后的缓存，要在上传成功后调用，注意：需要系统sd卡权限(缓存清除)
        PictureFileUtils.deleteCacheDirFile(mActivity); // TODO: 2018/9/30 暂时先放在这里
    }

    @OnClick({R.id.tv_pending_distribution, R.id.tv_sign_out,
            R.id.tv_completed, R.id.tv_be_replaced, R.id.fl_health, /*R.id.fl_personal,*/
            R.id.fl_setting, R.id.fl_manage, R.id.fl_feedback, R.id.fl_about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_out:      //退出登录:
                signOut();
                break;
            case R.id.tv_pending_distribution:      //待配送
                startActivity(new Intent(mActivity, BeSendingOutActivity.class));
                break;
            case R.id.tv_completed:     //已完成
                startActivity(new Intent(mActivity, CompletedActivity.class));
                break;
            case R.id.tv_be_replaced:       //被退换
                startActivity(new Intent(mActivity, CancelOrderActivity.class));
                break;
//            case R.id.fl_personal:      //个人信息
//                startActivity(new Intent(mActivity, PersonalInformationActivity.class));
//                break;
            case R.id.fl_health:       //健康证
                startActivity(new Intent(mActivity, HealthActivity.class));
                break;
            case R.id.fl_setting:       //接单设置
                startActivity(new Intent(mActivity, OrderTakingManageActivity.class));
                break;
            case R.id.fl_manage:       //管理细则
                startActivity(new Intent(mActivity, RulesOfManagementActivity.class));
                break;
            case R.id.fl_feedback:       //意见反馈
                startActivity(new Intent(mActivity, FeedbackActivity.class));
                break;
            case R.id.fl_about:       //关于E棒棒
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
        }
    }

    /**
     * 退出登录
     */
    private void signOut() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setMessage("确认退出登录吗?")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(mActivity, LoginActivity.class));
                        SPUtils.putBoolean(UserInfo.IS_LOGIN.name(), false);
                        mActivity.finish();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
