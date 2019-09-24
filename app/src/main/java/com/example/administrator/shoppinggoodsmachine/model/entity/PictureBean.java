package com.example.administrator.shoppinggoodsmachine.model.entity;

import java.util.List;

/**
 * 作者：create by ZhiYuan Xue on 2019/9/7 17:38
 * 邮箱：xzy7319@sina.com
 */

public class PictureBean {

    /**
     * data : [{"id":"1168026294275051520","adType":"machine","fleType":"video","fileUrl":"https://xiaochengxu-img.oss-cn-beijing.aliyuncs.com/headcoin/1165899990036398082.jpg","linkUrl":"","adTitle":"轮播1","adContent":"不知道","adIndex":null,"adStatus":1,"createtime":null,"updatetime":null},{"id":"1168402557480648704","adType":"wechat","fleType":"url","fileUrl":"https://xiaochengxu-img.oss-cn-beijing.aliyuncs.com/headcoin/1168420462519828482.jpg","linkUrl":"https://hao.360.com/?llqxqd","adTitle":"指甲钳","adContent":"指甲钳","adIndex":2,"adStatus":1,"createtime":null,"updatetime":null},{"id":"1166552493969301504","adType":"machine","fleType":"url","fileUrl":"","linkUrl":"","adTitle":"我爱细胞","adContent":"我爱细胞","adIndex":null,"adStatus":1,"createtime":null,"updatetime":null}]
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
         * id : 1168026294275051520
         * adType : machine
         * fleType : video
         * fileUrl : https://xiaochengxu-img.oss-cn-beijing.aliyuncs.com/headcoin/1165899990036398082.jpg
         * linkUrl :
         * adTitle : 轮播1
         * adContent : 不知道
         * adIndex : null
         * adStatus : 1
         * createtime : null
         * updatetime : null
         */

        private String id;
        private String adType;
        private String fleType;
        private String fileUrl;
        private String linkUrl;
        private String adTitle;
        private String adContent;
        private Object adIndex;
        private int adStatus;
        private Object createtime;
        private Object updatetime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAdType() {
            return adType;
        }

        public void setAdType(String adType) {
            this.adType = adType;
        }

        public String getFleType() {
            return fleType;
        }

        public void setFleType(String fleType) {
            this.fleType = fleType;
        }

        public String getFileUrl() {
            return fileUrl;
        }

        public void setFileUrl(String fileUrl) {
            this.fileUrl = fileUrl;
        }

        public String getLinkUrl() {
            return linkUrl;
        }

        public void setLinkUrl(String linkUrl) {
            this.linkUrl = linkUrl;
        }

        public String getAdTitle() {
            return adTitle;
        }

        public void setAdTitle(String adTitle) {
            this.adTitle = adTitle;
        }

        public String getAdContent() {
            return adContent;
        }

        public void setAdContent(String adContent) {
            this.adContent = adContent;
        }

        public Object getAdIndex() {
            return adIndex;
        }

        public void setAdIndex(Object adIndex) {
            this.adIndex = adIndex;
        }

        public int getAdStatus() {
            return adStatus;
        }

        public void setAdStatus(int adStatus) {
            this.adStatus = adStatus;
        }

        public Object getCreatetime() {
            return createtime;
        }

        public void setCreatetime(Object createtime) {
            this.createtime = createtime;
        }

        public Object getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(Object updatetime) {
            this.updatetime = updatetime;
        }
    }
}
