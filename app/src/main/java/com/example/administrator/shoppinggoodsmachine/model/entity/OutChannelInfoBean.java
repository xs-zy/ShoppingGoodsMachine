package com.example.administrator.shoppinggoodsmachine.model.entity;

import java.util.List;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/1 13:57
 * 邮箱：xzy7319@sina.com
 */

public class OutChannelInfoBean {

    /**
     * data : [{"id":"1168043558663655424","machineId":"1168043440027766784","index":1,"code":"353216","type":"11","maxQuality":11,"goodsId":"1168043284091932672","failureTimes":0,"channelStatus":0,"faultTime":null,"locked":false,"delFlg":false,"createTime":null,"creater":null,"updateTime":null,"updater":null,"currentQuality":11}]
     * message : 操作成功
     * status : 1
     */

    private String message;
    private String status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1168043558663655424
         * machineId : 1168043440027766784
         * index : 1
         * code : 353216
         * type : 11
         * maxQuality : 11
         * goodsId : 1168043284091932672
         * failureTimes : 0
         * channelStatus : 0
         * faultTime : null
         * locked : false
         * delFlg : false
         * createTime : null
         * creater : null
         * updateTime : null
         * updater : null
         * currentQuality : 11
         */

        private String id;
        private String machineId;
        private int index;
        private String code;
        private String type;
        private int maxQuality;
        private String goodsId;
        private int failureTimes;
        private int channelStatus;
        private Object faultTime;
        private boolean locked;
        private boolean delFlg;
        private Object createTime;
        private Object creater;
        private Object updateTime;
        private Object updater;
        private int currentQuality;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMachineId() {
            return machineId;
        }

        public void setMachineId(String machineId) {
            this.machineId = machineId;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getMaxQuality() {
            return maxQuality;
        }

        public void setMaxQuality(int maxQuality) {
            this.maxQuality = maxQuality;
        }

        public String getGoodsId() {
            return goodsId;
        }

        public void setGoodsId(String goodsId) {
            this.goodsId = goodsId;
        }

        public int getFailureTimes() {
            return failureTimes;
        }

        public void setFailureTimes(int failureTimes) {
            this.failureTimes = failureTimes;
        }

        public int getChannelStatus() {
            return channelStatus;
        }

        public void setChannelStatus(int channelStatus) {
            this.channelStatus = channelStatus;
        }

        public Object getFaultTime() {
            return faultTime;
        }

        public void setFaultTime(Object faultTime) {
            this.faultTime = faultTime;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        public boolean isDelFlg() {
            return delFlg;
        }

        public void setDelFlg(boolean delFlg) {
            this.delFlg = delFlg;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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

        public Object getUpdater() {
            return updater;
        }

        public void setUpdater(Object updater) {
            this.updater = updater;
        }

        public int getCurrentQuality() {
            return currentQuality;
        }

        public void setCurrentQuality(int currentQuality) {
            this.currentQuality = currentQuality;
        }
    }
}
