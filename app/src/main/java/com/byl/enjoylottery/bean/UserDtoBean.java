package com.byl.enjoylottery.bean;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class UserDtoBean {

    /**
     * message : 注册成功
     * status : true
     * user : {"id":"4a84c638-dd1e-4a0b-b357-fc4fbb8198f4","code":null,"name":"大黄","touXiang":null,"touXiangImg":null,"password":"FB35AB14DD5EEE7D299E39768EBAEC4A3B5DCE7EAC251A477054850A","telephone":"11111111114","isPhone":"1","isRobot":"0","isStationOwner":"0","fromApp":"1","provinceCode":null,"cityCode":null,"inviteCode":null,"regionCode":null,"address":null,"coordinate":null,"postCode":null,"isVirtual":"0","isExpert":"0","handSel":0,"colorCoins":0,"createTimeStr":null,"yanzhengma":"1111","idNumberFrontImgId":null,"idNumberBackImgId":null,"idNumberFrontImg":null,"idNumberBackImg":null,"token":"EAFd3yFVijIl+SE4nqUHwX4CNsTtCpmrK578tPrdFDbWKM2nsUF5+6V5Lnmf0Kve3LljvMAylmCb+pk66SGJjAP4AJ3MOzwwhV0AWUJrXI/uAZ82u6Mkgkd9xUY0H0jRJ9NoMjMcfGY=","cailiaoName":null,"sex":null,"signature":null}
     */

    private String message;
    private boolean status;
    private UserBean user;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 4a84c638-dd1e-4a0b-b357-fc4fbb8198f4
         * code : null
         * name : 大黄
         * touXiang : null
         * touXiangImg : null
         * password : FB35AB14DD5EEE7D299E39768EBAEC4A3B5DCE7EAC251A477054850A
         * telephone : 11111111114
         * isPhone : 1
         * isRobot : 0
         * isStationOwner : 0
         * fromApp : 1
         * provinceCode : null
         * cityCode : null
         * inviteCode : null
         * regionCode : null
         * address : null
         * coordinate : null
         * postCode : null
         * isVirtual : 0
         * isExpert : 0
         * handSel : 0
         * colorCoins : 0
         * createTimeStr : null
         * yanzhengma : 1111
         * idNumberFrontImgId : null
         * idNumberBackImgId : null
         * idNumberFrontImg : null
         * idNumberBackImg : null
         * token : EAFd3yFVijIl+SE4nqUHwX4CNsTtCpmrK578tPrdFDbWKM2nsUF5+6V5Lnmf0Kve3LljvMAylmCb+pk66SGJjAP4AJ3MOzwwhV0AWUJrXI/uAZ82u6Mkgkd9xUY0H0jRJ9NoMjMcfGY=
         * cailiaoName : null
         * sex : null
         * signature : null
         */

        private String id;
        private Object code;
        private String name;
        private Object touXiang;
        private Object touXiangImg;
        private String password;
        private String telephone;
        private String isPhone;
        private String isRobot;
        private String isStationOwner;
        private String fromApp;
        private Object provinceCode;
        private Object cityCode;
        private Object inviteCode;
        private Object regionCode;
        private Object address;
        private Object coordinate;
        private Object postCode;
        private String isVirtual;
        private String isExpert;
        private int handSel;
        private int colorCoins;
        private Object createTimeStr;
        private String yanzhengma;
        private Object idNumberFrontImgId;
        private Object idNumberBackImgId;
        private Object idNumberFrontImg;
        private Object idNumberBackImg;
        private String token;
        private Object cailiaoName;
        private Object sex;
        private Object signature;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTouXiang() {
            return touXiang;
        }

        public void setTouXiang(Object touXiang) {
            this.touXiang = touXiang;
        }

        public Object getTouXiangImg() {
            return touXiangImg;
        }

        public void setTouXiangImg(Object touXiangImg) {
            this.touXiangImg = touXiangImg;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getIsPhone() {
            return isPhone;
        }

        public void setIsPhone(String isPhone) {
            this.isPhone = isPhone;
        }

        public String getIsRobot() {
            return isRobot;
        }

        public void setIsRobot(String isRobot) {
            this.isRobot = isRobot;
        }

        public String getIsStationOwner() {
            return isStationOwner;
        }

        public void setIsStationOwner(String isStationOwner) {
            this.isStationOwner = isStationOwner;
        }

        public String getFromApp() {
            return fromApp;
        }

        public void setFromApp(String fromApp) {
            this.fromApp = fromApp;
        }

        public Object getProvinceCode() {
            return provinceCode;
        }

        public void setProvinceCode(Object provinceCode) {
            this.provinceCode = provinceCode;
        }

        public Object getCityCode() {
            return cityCode;
        }

        public void setCityCode(Object cityCode) {
            this.cityCode = cityCode;
        }

        public Object getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(Object inviteCode) {
            this.inviteCode = inviteCode;
        }

        public Object getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(Object regionCode) {
            this.regionCode = regionCode;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getCoordinate() {
            return coordinate;
        }

        public void setCoordinate(Object coordinate) {
            this.coordinate = coordinate;
        }

        public Object getPostCode() {
            return postCode;
        }

        public void setPostCode(Object postCode) {
            this.postCode = postCode;
        }

        public String getIsVirtual() {
            return isVirtual;
        }

        public void setIsVirtual(String isVirtual) {
            this.isVirtual = isVirtual;
        }

        public String getIsExpert() {
            return isExpert;
        }

        public void setIsExpert(String isExpert) {
            this.isExpert = isExpert;
        }

        public int getHandSel() {
            return handSel;
        }

        public void setHandSel(int handSel) {
            this.handSel = handSel;
        }

        public int getColorCoins() {
            return colorCoins;
        }

        public void setColorCoins(int colorCoins) {
            this.colorCoins = colorCoins;
        }

        public Object getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(Object createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public String getYanzhengma() {
            return yanzhengma;
        }

        public void setYanzhengma(String yanzhengma) {
            this.yanzhengma = yanzhengma;
        }

        public Object getIdNumberFrontImgId() {
            return idNumberFrontImgId;
        }

        public void setIdNumberFrontImgId(Object idNumberFrontImgId) {
            this.idNumberFrontImgId = idNumberFrontImgId;
        }

        public Object getIdNumberBackImgId() {
            return idNumberBackImgId;
        }

        public void setIdNumberBackImgId(Object idNumberBackImgId) {
            this.idNumberBackImgId = idNumberBackImgId;
        }

        public Object getIdNumberFrontImg() {
            return idNumberFrontImg;
        }

        public void setIdNumberFrontImg(Object idNumberFrontImg) {
            this.idNumberFrontImg = idNumberFrontImg;
        }

        public Object getIdNumberBackImg() {
            return idNumberBackImg;
        }

        public void setIdNumberBackImg(Object idNumberBackImg) {
            this.idNumberBackImg = idNumberBackImg;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getCailiaoName() {
            return cailiaoName;
        }

        public void setCailiaoName(Object cailiaoName) {
            this.cailiaoName = cailiaoName;
        }

        public Object getSex() {
            return sex;
        }

        public void setSex(Object sex) {
            this.sex = sex;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }
    }
}
