package com.pywl.ebangbang_rider.network.entity;

/**
 * Created by Harry on 2018/11/7.
 */
public class LoginEntity {

    /**
     * msg : 登录成功
     * code : 1
     * userData : {"id":4,"loginName":"qishou","password":"0ac372901b1731d6a44fc70500f274b8ae73f429","salt":"dba00f8571f58b14","phone":"19999999999","state":false,"type":"ride","remark":"骑手","lastLoginTime":null,"createTime":1538012894000,"createUser":1,"updateTime":null,"updateUser":null,"isDelete":false,"plainPassword":null,"nickname":null,"shippingAddress":null,"birthday":null,"siteLongitude":null,"siteLatitude":null}
     * rideData : {"id":6,"phone":"15036441575","name":"王五","headPortrait":"20180918160606383_217.png","gender":null,"identityCard1":null,"identityCard2":null,"emergencyContact":null,"emergencyContactPhone":null,"isDelete":null,"createId":null,"createTime":null,"updateId":null,"updateTime":null,"userId":null,"takeoutNum":null,"distance":null,"cou":null,"longitude":"11111.54545","latitude":"454545.455"}
     * token : F97854A2456D5F42F6BCA4B671B58403
     * headPortraitLink : http://127.0.0.1:8080/guozongapp/file/
     */

    public String msg;
    public int code;
    public UserDataBean userData;
    public RideDataBean rideData;
    public String token;
    public String headPortraitLink;

    public static class UserDataBean {
        /**
         * id : 4
         * loginName : qishou
         * password : 0ac372901b1731d6a44fc70500f274b8ae73f429
         * salt : dba00f8571f58b14
         * phone : 19999999999
         * state : false
         * type : ride
         * remark : 骑手
         * lastLoginTime : null
         * createTime : 1538012894000
         * createUser : 1
         * updateTime : null
         * updateUser : null
         * isDelete : false
         * plainPassword : null
         * nickname : null
         * shippingAddress : null
         * birthday : null
         * siteLongitude : null
         * siteLatitude : null
         */

        public int id;
        public String loginName;
        public String password;
        public String salt;
        public String phone;
        public boolean state;
        public String type;
        public String remark;
        public Object lastLoginTime;
        public long createTime;
        public int createUser;
        public Object updateTime;
        public Object updateUser;
        public boolean isDelete;
        public Object plainPassword;
        public Object nickname;
        public Object shippingAddress;
        public Object birthday;
        public Object siteLongitude;
        public Object siteLatitude;
    }

    public static class RideDataBean {
        /**
         * id : 6
         * phone : 15036441575
         * name : 王五
         * headPortrait : 20180918160606383_217.png
         * gender : null
         * identityCard1 : null
         * identityCard2 : null
         * emergencyContact : null
         * emergencyContactPhone : null
         * isDelete : null
         * createId : null
         * createTime : null
         * updateId : null
         * updateTime : null
         * userId : null
         * takeoutNum : null
         * distance : null
         * cou : null
         * longitude : 11111.54545
         * latitude : 454545.455
         */

        public int id;
        public String phone;
        public String name;
        public String headPortrait;
        public Object gender;
        public Object identityCard1;
        public Object identityCard2;
        public Object emergencyContact;
        public Object emergencyContactPhone;
        public Object isDelete;
        public Object createId;
        public Object createTime;
        public Object updateId;
        public Object updateTime;
        public Object userId;
        public Object takeoutNum;
        public Object distance;
        public Object cou;
        public String longitude;
        public String latitude;
    }
}
