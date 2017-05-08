package com.example.administrator.enjoylottery.bean;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class PersonInformationBean {

    /**
     * message : 修改成功
     * flag : true
     * userDto : {"id":"6661d32a-5660-49cc-805a-d6d14bc77d8a","code":null,"name":"娜娜","lastTouXiang":null,"touXiang":"514449cf-2145-40d3-bcb2-4c9a95675164","touXiangUrl":"/upload/dbf9ae89-63c4-49b5-ab46-ab369fa1ce78.jpg","password":"B5AC89FF1A1FB7353F9A74CF59EB1ACC9DBF1EB4DDA817B0B47F62EC","telephone":"13888888888","isPhone":"1","isRobot":"0","isStationOwner":"0","fromApp":"1","provinceCode":null,"cityCode":null,"inviteCode":null,"regionCode":null,"address":null,"coordinate":null,"postCode":null,"isVirtual":"0","isExpert":"0","handSel":0,"colorCoins":0,"createTimeStr":"2017-04-26 17:08:15","yanzhengma":null,"idNumberFrontImgId":null,"idNumberBackImgId":null,"idNumberFrontImg":null,"idNumberBackImg":null,"token":"i7W3aAdsXi8JY++dvj8x+n4CNsTtCpmrK578tPrdFDbWKM2nsUF5+zAZrvH2MaF3Ar8XBTXrkUFcAp703HSTntKV8ou4GjWfacIXVzKRWasdfWyE6KTVRHxoCeWxhdxjVUeleL4Q9/8=","cailiaoName":null,"sex":null,"signature":null}
     */

    private String message;
    private Boolean flag;
    private UserDtoBean userDto;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public UserDtoBean getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDtoBean userDto) {
        this.userDto = userDto;
    }

    public static class UserDtoBean {
        /**
         * id : 6661d32a-5660-49cc-805a-d6d14bc77d8a
         * code : null
         * name : 娜娜
         * lastTouXiang : null
         * touXiang : 514449cf-2145-40d3-bcb2-4c9a95675164
         * touXiangUrl : /upload/dbf9ae89-63c4-49b5-ab46-ab369fa1ce78.jpg
         * password : B5AC89FF1A1FB7353F9A74CF59EB1ACC9DBF1EB4DDA817B0B47F62EC
         * telephone : 13888888888
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
         * createTimeStr : 2017-04-26 17:08:15
         * yanzhengma : null
         * idNumberFrontImgId : null
         * idNumberBackImgId : null
         * idNumberFrontImg : null
         * idNumberBackImg : null
         * token : i7W3aAdsXi8JY++dvj8x+n4CNsTtCpmrK578tPrdFDbWKM2nsUF5+zAZrvH2MaF3Ar8XBTXrkUFcAp703HSTntKV8ou4GjWfacIXVzKRWasdfWyE6KTVRHxoCeWxhdxjVUeleL4Q9/8=
         * cailiaoName : null
         * sex : null
         * signature : null
         */

        private String id;
        private Object code;
        private String name;
        private Object lastTouXiang;
        private String touXiang;
        private String touXiangUrl;
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
        private String createTimeStr;
        private Object yanzhengma;
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

        public Object getLastTouXiang() {
            return lastTouXiang;
        }

        public void setLastTouXiang(Object lastTouXiang) {
            this.lastTouXiang = lastTouXiang;
        }

        public String getTouXiang() {
            return touXiang;
        }

        public void setTouXiang(String touXiang) {
            this.touXiang = touXiang;
        }

        public String getTouXiangUrl() {
            return touXiangUrl;
        }

        public void setTouXiangUrl(String touXiangUrl) {
            this.touXiangUrl = touXiangUrl;
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

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public Object getYanzhengma() {
            return yanzhengma;
        }

        public void setYanzhengma(Object yanzhengma) {
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
