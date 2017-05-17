package com.byl.enjoylottery.bean;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class MapBean {

    /**
     * result : {"location":{"lat":40.173197,"lng":124.350321},"precise":0,"level":"区县","confidence":18}
     * status : OK
     */

    private ResultBean result;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * location : {"lat":40.173197,"lng":124.350321}
         * precise : 0
         * level : 区县
         * confidence : 18
         */

        private LocationBean location;
        private int precise;
        private String level;
        private int confidence;

        public LocationBean getLocation() {
            return location;
        }

        public void setLocation(LocationBean location) {
            this.location = location;
        }

        public int getPrecise() {
            return precise;
        }

        public void setPrecise(int precise) {
            this.precise = precise;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public int getConfidence() {
            return confidence;
        }

        public void setConfidence(int confidence) {
            this.confidence = confidence;
        }

        public static class LocationBean {
            /**
             * lat : 40.173197
             * lng : 124.350321
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
