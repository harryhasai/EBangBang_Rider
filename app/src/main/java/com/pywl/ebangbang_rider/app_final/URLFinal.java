package com.pywl.ebangbang_rider.app_final;

/**
 * Created by Harry on 2018/9/25.
 * URL地址类
 */
public class URLFinal {
    /**
     * BaseUrl
     */
    public static final String BASE_URL = "http://116.62.218.136:9055/guozongApp/";

    /**
     * 登录
     */
    public static final String LOGIN = "modeleRider/personal/RideLogin";
    /**
     * 健康证
     */
    public static final String HEALTH = "modeleRider/personal/selectRideHealthCertificate";
    /**
     * 更新骑手位置
     */
    public static final String UPDATA_RIDE_SITE = "modeleRider/personal/updataRideSite";
    /**
     * 正在派送中的数据信息
     */
    public static final String GET_WAITING_FOR_GOODS = "modeleRider/personal/riderOrderForm";
    /**
     * 抵达商家 取货确认按钮
     */
    public static final String ARRIVAL = "modeleRider/personal/rideAffirmClaimGoods";
    /**
     * 抵达客户, 确认完成订单
     */
    public static final String COMPLETE = "modeleRider/personal/rideAffirmAccomplish";
    /**
     * 修改骑手接单状态
     */
    public static final String ORDER_TAKING_MANAGE_SAVE = "modeleRider/personal/updataRideStatus";
    /**
     * 获取骑手接单状态
     */
    public static final String ORDER_TAKING_MANAGE_GET_STATUS = "modeleRider/personal/selectRideStatus";
    /**
     * 意见反馈
     */
    public static final String FEEDBACK_COMMIT = "mobile/feedback/insertFeedback";
}
