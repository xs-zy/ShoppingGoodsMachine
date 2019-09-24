package com.example.administrator.shoppinggoodsmachine.model.entity;

/**
 * 作者：create by ZhiYuan Xue on 2019/8/31 14:44
 * 邮箱：xzy7319@sina.com
 */

public class MachineBean {


    /**
     * data : {"id":"1168043440027766790","name":null,"password":null,"machineCode":"phixcdfc41b8c54c464aa5df6e94e369023a","machineType":null,"customCode":null,"channelSpec":null,"gpsStatus":null,"groupId":null,"lastReviseTime":null,"lastConfirmTime":null,"lastSyncTime":null,"latitude":null,"longitude":null,"adMenuId":null,"adPlay":null,"onlineStatus":null,"locked":null,"delFlg":null,"createTime":1567324456044,"creater":null,"updateTime":null,"deviceNum":"2W:4R:5T:4F","updater":null}
     * message : 操作成功
     * status : 1
     */

    private DataBean data;
    private String message;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * id : 1168043440027766790
         * name : null
         * password : null
         * machineCode : phixcdfc41b8c54c464aa5df6e94e369023a
         * machineType : null
         * customCode : null
         * channelSpec : null
         * gpsStatus : null
         * groupId : null
         * lastReviseTime : null
         * lastConfirmTime : null
         * lastSyncTime : null
         * latitude : null
         * longitude : null
         * adMenuId : null
         * adPlay : null
         * onlineStatus : null
         * locked : null
         * delFlg : null
         * createTime : 1567324456044
         * creater : null
         * updateTime : null
         * deviceNum : 2W:4R:5T:4F
         * updater : null
         */

        private String id;
        private Object name;
        private Object password;
        private String machineCode;
        private Object machineType;
        private Object customCode;
        private Object channelSpec;
        private Object gpsStatus;
        private Object groupId;
        private Object lastReviseTime;
        private Object lastConfirmTime;
        private Object lastSyncTime;
        private Object latitude;
        private Object longitude;
        private Object adMenuId;
        private Object adPlay;
        private Object onlineStatus;
        private Object locked;
        private Object delFlg;
        private long createTime;
        private Object creater;
        private Object updateTime;
        private String deviceNum;
        private Object updater;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getMachineCode() {
            return machineCode;
        }

        public void setMachineCode(String machineCode) {
            this.machineCode = machineCode;
        }

        public Object getMachineType() {
            return machineType;
        }

        public void setMachineType(Object machineType) {
            this.machineType = machineType;
        }

        public Object getCustomCode() {
            return customCode;
        }

        public void setCustomCode(Object customCode) {
            this.customCode = customCode;
        }

        public Object getChannelSpec() {
            return channelSpec;
        }

        public void setChannelSpec(Object channelSpec) {
            this.channelSpec = channelSpec;
        }

        public Object getGpsStatus() {
            return gpsStatus;
        }

        public void setGpsStatus(Object gpsStatus) {
            this.gpsStatus = gpsStatus;
        }

        public Object getGroupId() {
            return groupId;
        }

        public void setGroupId(Object groupId) {
            this.groupId = groupId;
        }

        public Object getLastReviseTime() {
            return lastReviseTime;
        }

        public void setLastReviseTime(Object lastReviseTime) {
            this.lastReviseTime = lastReviseTime;
        }

        public Object getLastConfirmTime() {
            return lastConfirmTime;
        }

        public void setLastConfirmTime(Object lastConfirmTime) {
            this.lastConfirmTime = lastConfirmTime;
        }

        public Object getLastSyncTime() {
            return lastSyncTime;
        }

        public void setLastSyncTime(Object lastSyncTime) {
            this.lastSyncTime = lastSyncTime;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getAdMenuId() {
            return adMenuId;
        }

        public void setAdMenuId(Object adMenuId) {
            this.adMenuId = adMenuId;
        }

        public Object getAdPlay() {
            return adPlay;
        }

        public void setAdPlay(Object adPlay) {
            this.adPlay = adPlay;
        }

        public Object getOnlineStatus() {
            return onlineStatus;
        }

        public void setOnlineStatus(Object onlineStatus) {
            this.onlineStatus = onlineStatus;
        }

        public Object getLocked() {
            return locked;
        }

        public void setLocked(Object locked) {
            this.locked = locked;
        }

        public Object getDelFlg() {
            return delFlg;
        }

        public void setDelFlg(Object delFlg) {
            this.delFlg = delFlg;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getCreater() {
            return creater;
        }

        public void setCreater(Object creater) {
            this.creater = creater;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public Object getUpdater() {
            return updater;
        }

        public void setUpdater(Object updater) {
            this.updater = updater;
        }
    }
}
