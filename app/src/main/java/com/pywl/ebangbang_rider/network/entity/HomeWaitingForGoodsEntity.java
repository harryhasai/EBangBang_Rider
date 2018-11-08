package com.pywl.ebangbang_rider.network.entity;

import java.util.List;

/**
 * Created by Harry on 2018/11/8.
 */
public class HomeWaitingForGoodsEntity {

    /**
     * msg : 查询成功
     * headPortraitLink : http://127.0.0.1:8080/guozongapp/file/
     * code : 1
     * data : [{"shioLatitude":"34.667874","orderFormStatus":3,"getCreateTime":1540533578000,"orderNumber":"1540533578603678690","addresseePhone":"18766132222","shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095947758_327.jpg","goodsId":3,"goodsPrice":0,"num":1,"goodsUnit":"个1","goodsName":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":0,"num":1,"goodsUnit":"斤","goodsName":"牛肉"}],"remark":"多放辣","addresseeName":"Kk2","addresseeLongitude":"116.413882","addresseeLatitude":"39.922979","DispatchingMoney":3,"addresseeSite":"18766132222","shopLongitude":"112.4349","ActualMoney":0.01,"id":11,"shopId":10},{"shioLatitude":"34.667874","orderFormStatus":3,"getCreateTime":1537410284000,"orderNumber":"34342342","addresseePhone":"113245545","shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":0,"num":2,"goodsUnit":"斤","goodsName":"牛肉"},{"imgLink":"20181108095947758_327.jpg","goodsId":3,"goodsPrice":0,"num":1,"goodsUnit":"个1","goodsName":"火腿1"}],"remark":"aaaaaaaa","addresseeName":"刘先生","addresseeLongitude":"11","addresseeLatitude":"111","DispatchingMoney":66.56,"addresseeSite":"河南洛阳","shopLongitude":"112.4349","ActualMoney":41.45,"id":2,"shopId":10},{"shioLatitude":"34.667874","orderFormStatus":3,"getCreateTime":1538013787000,"orderNumber":"456456455","addresseePhone":"113245545","shopName":"刘海鸥DE店","goods":[{"imgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":0,"num":2,"goodsUnit":"斤","goodsName":"牛肉"},{"imgLink":"20180926160337796_238.jpg","goodsId":5,"goodsPrice":0.01,"num":2,"goodsUnit":"斤","goodsName":"牛火腿"}],"remark":"不要嘛不啦","addresseeName":"刘先生","addresseeLongitude":"11","addresseeLatitude":"111","DispatchingMoney":5,"addresseeSite":"河南洛阳","shopLongitude":"112.4349","ActualMoney":45,"id":3,"shopId":10}]
     */

    public String msg;
    public String headPortraitLink;
    public int code;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * shioLatitude : 34.667874
         * orderFormStatus : 3
         * getCreateTime : 1540533578000
         * orderNumber : 1540533578603678690
         * addresseePhone : 18766132222
         * shopName : 刘海鸥DE店
         * goods : [{"imgLink":"20181108095947758_327.jpg","goodsId":3,"goodsPrice":0,"num":1,"goodsUnit":"个1","goodsName":"火腿1"},{"imgLink":"20181108095927871_558.jpg","goodsId":4,"goodsPrice":0,"num":1,"goodsUnit":"斤","goodsName":"牛肉"}]
         * remark : 多放辣
         * addresseeName : Kk2
         * addresseeLongitude : 116.413882
         * addresseeLatitude : 39.922979
         * DispatchingMoney : 3
         * addresseeSite : 18766132222
         * shopLongitude : 112.4349
         * ActualMoney : 0.01
         * id : 11
         * shopId : 10
         */

        public String shopLatitude;
        public int orderFormStatus;
        public long getCreateTime;
        public String orderNumber;
        public String addresseePhone;
        public String shopName;
        public String remark;
        public String addresseeName;
        public String addresseeLongitude;
        public String addresseeLatitude;
        public double DispatchingMoney;
        public String addresseeSite;
        public String shopLongitude;
        public String shopSitedDetail;
        public double ActualMoney;
        public int id;
        public int shopId;
        public List<GoodsBean> goods;

        public static class GoodsBean {
            /**
             * imgLink : 20181108095947758_327.jpg
             * goodsId : 3
             * goodsPrice : 0
             * num : 1
             * goodsUnit : 个1
             * goodsName : 火腿1
             */

            public String imgLink;
            public int goodsId;
            public double goodsPrice;
            public int num;
            public String goodsUnit;
            public String goodsName;
        }
    }
}
