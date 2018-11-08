package com.pywl.ebangbang_rider.function.personal_information;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pywl.ebangbang_rider.R;
import com.pywl.ebangbang_rider.base.BaseActivity;
import com.pywl.ebangbang_rider.base.presenter.BasePresenter;
import com.pywl.ebangbang_rider.network.entity.PersonalInformationMultiItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/10/8.
 * 个人信息页面
 */
public class PersonalInformationActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private List<PersonalInformationMultiItem> mList;

    @Override
    protected int setupView() {
        return R.layout.activity_personal_information;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("个人信息");

        initRecyclerView();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        return null;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    private void initRecyclerView() {
        mList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 0) {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.TOP));
            } else if (i == 1) {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.CENTER));
            } else {
                mList.add(new PersonalInformationMultiItem(PersonalInformationMultiItem.BOTTOM));
            }
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PersonalInformationReviewsAdapter adapter = new PersonalInformationReviewsAdapter(mList, this);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.ll_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

}
