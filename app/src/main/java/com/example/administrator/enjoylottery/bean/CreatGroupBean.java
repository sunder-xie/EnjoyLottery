package com.example.administrator.enjoylottery.bean;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class CreatGroupBean {


    /**
     * message : 创建成功
     * flag : true
     * group : {"id":"3924d15d-5a37-4263-99c4-a7fd62896d37","name":"测试群","groupNumber":"00000008","groupLevel":"1","groupRobotID":"99d7be04-ac73-4161-bbdc-4c77835c3118","introduction":"嗯嗯嗯","touXiang":"d737125b-c24a-4e35-9fa5-2bf351082ebd","touXiangImgUrl":"/upload/0a4d2247-fa82-481a-8ea9-9c5532fcd814.jpg","touXiangImg":null,"ownerId":null,"lotteryType":"2","province":"330000","city":"330100","upLevel":null,"joinType":1,"fabuKj":0,"fabuZs":1,"ssYlChaxun":0,"ssZjChaxun":0,"ssKjChaxun":0,"createTimeStr":"2017-05-10 13:48:22"}
     */

    private String message;
    private Boolean flag;
    private GroupBean group;

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

    public GroupBean getGroup() {
        return group;
    }

    public void setGroup(GroupBean group) {
        this.group = group;
    }

    public static class GroupBean {
        /**
         * id : 3924d15d-5a37-4263-99c4-a7fd62896d37
         * name : 测试群
         * groupNumber : 00000008
         * groupLevel : 1
         * groupRobotID : 99d7be04-ac73-4161-bbdc-4c77835c3118
         * introduction : 嗯嗯嗯
         * touXiang : d737125b-c24a-4e35-9fa5-2bf351082ebd
         * touXiangImgUrl : /upload/0a4d2247-fa82-481a-8ea9-9c5532fcd814.jpg
         * touXiangImg : null
         * ownerId : null
         * lotteryType : 2
         * province : 330000
         * city : 330100
         * upLevel : null
         * joinType : 1
         * fabuKj : 0
         * fabuZs : 1
         * ssYlChaxun : 0
         * ssZjChaxun : 0
         * ssKjChaxun : 0
         * createTimeStr : 2017-05-10 13:48:22
         * groupQRImg：
         */

        private String id;
        private String name;
        private String groupQRImg;
        private String groupNumber;
        private String groupLevel;
        private String groupRobotID;
        private String introduction;
        private String touXiang;
        private String touXiangImgUrl;
        private Object touXiangImg;
        private Object ownerId;
        private String lotteryType;
        private String province;
        private String city;
        private Object upLevel;
        private int joinType;
        private int fabuKj;
        private int fabuZs;
        private int ssYlChaxun;
        private int ssZjChaxun;
        private int ssKjChaxun;
        private String createTimeStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroupNumber() {
            return groupNumber;
        }

        public void setGroupNumber(String groupNumber) {
            this.groupNumber = groupNumber;
        }

        public String getGroupLevel() {
            return groupLevel;
        }

        public void setGroupLevel(String groupLevel) {
            this.groupLevel = groupLevel;
        }

        public String getGroupRobotID() {
            return groupRobotID;
        }

        public void setGroupRobotID(String groupRobotID) {
            this.groupRobotID = groupRobotID;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getTouXiang() {
            return touXiang;
        }

        public void setTouXiang(String touXiang) {
            this.touXiang = touXiang;
        }

        public String getTouXiangImgUrl() {
            return touXiangImgUrl;
        }

        public void setTouXiangImgUrl(String touXiangImgUrl) {
            this.touXiangImgUrl = touXiangImgUrl;
        }

        public Object getTouXiangImg() {
            return touXiangImg;
        }

        public void setTouXiangImg(Object touXiangImg) {
            this.touXiangImg = touXiangImg;
        }

        public Object getOwnerId() {
            return ownerId;
        }

        public void setOwnerId(Object ownerId) {
            this.ownerId = ownerId;
        }

        public String getLotteryType() {
            return lotteryType;
        }

        public void setLotteryType(String lotteryType) {
            this.lotteryType = lotteryType;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Object getUpLevel() {
            return upLevel;
        }

        public void setUpLevel(Object upLevel) {
            this.upLevel = upLevel;
        }

        public int getJoinType() {
            return joinType;
        }

        public void setJoinType(int joinType) {
            this.joinType = joinType;
        }

        public int getFabuKj() {
            return fabuKj;
        }

        public void setFabuKj(int fabuKj) {
            this.fabuKj = fabuKj;
        }

        public int getFabuZs() {
            return fabuZs;
        }

        public void setFabuZs(int fabuZs) {
            this.fabuZs = fabuZs;
        }

        public int getSsYlChaxun() {
            return ssYlChaxun;
        }

        public void setSsYlChaxun(int ssYlChaxun) {
            this.ssYlChaxun = ssYlChaxun;
        }

        public int getSsZjChaxun() {
            return ssZjChaxun;
        }

        public void setSsZjChaxun(int ssZjChaxun) {
            this.ssZjChaxun = ssZjChaxun;
        }

        public int getSsKjChaxun() {
            return ssKjChaxun;
        }

        public void setSsKjChaxun(int ssKjChaxun) {
            this.ssKjChaxun = ssKjChaxun;
        }

        public String getCreateTimeStr() {
            return createTimeStr;
        }

        public void setCreateTimeStr(String createTimeStr) {
            this.createTimeStr = createTimeStr;
        }

        public String getGroupQRImg() {
            return groupQRImg;
        }

        public void setGroupQRImg(String groupQRImg) {
            this.groupQRImg = groupQRImg;
        }
    }
}
