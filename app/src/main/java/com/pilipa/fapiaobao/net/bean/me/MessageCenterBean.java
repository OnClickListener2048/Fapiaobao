package com.pilipa.fapiaobao.net.bean.me;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by edz on 2017/11/6.
 */

public class MessageCenterBean {


    /**
     * status : 200
     * msg : OK
     * data : {"2":[{"id":"43243242234gs4df191ba27f3fb1a4fee","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张"},"readFlag":"0"}],"3":[{"id":"trewtrewtewtewt5ytrtryerteryteryeryye","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db653423432432432wqewq","isNewRecord":false,"type":"3","content":"不合格发票 20张"},"readFlag":"0"}],"4":[{"id":"retertertretewrggewtttttttttttttttttwtewt","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324324324324324","isNewRecord":false,"type":"4","content":"服务通知 20张"},"readFlag":"0"},{"id":"uyuyouyyuytg647675444f3242333423","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324432432432324","isNewRecord":false,"type":"4","content":"服务通知 20张"},"readFlag":"0"}]}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        @SerializedName("2")
        private List<_$2Bean> _$2;
        @SerializedName("3")
        private List<_$3Bean> _$3;
        @SerializedName("4")
        private List<_$4Bean> _$4;

        public List<_$2Bean> get_$2() {
            return _$2;
        }

        public void set_$2(List<_$2Bean> _$2) {
            this._$2 = _$2;
        }

        public List<_$3Bean> get_$3() {
            return _$3;
        }

        public void set_$3(List<_$3Bean> _$3) {
            this._$3 = _$3;
        }

        public List<_$4Bean> get_$4() {
            return _$4;
        }

        public void set_$4(List<_$4Bean> _$4) {
            this._$4 = _$4;
        }

        public static class _$2Bean {
            /**
             * id : 43243242234gs4df191ba27f3fb1a4fee
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张"}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBean message;
            private String readFlag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public MessageBean getMessage() {
                return message;
            }

            public void setMessage(MessageBean message) {
                this.message = message;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            public static class MessageBean {
                /**
                 * id : qewwqeqwewqewqewqewqewqewq
                 * isNewRecord : false
                 * type : 2
                 * content : 红包到账 20张
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isIsNewRecord() {
                    return isNewRecord;
                }

                public void setIsNewRecord(boolean isNewRecord) {
                    this.isNewRecord = isNewRecord;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }

        public static class _$3Bean {
            /**
             * id : trewtrewtewtewt5ytrtryerteryteryeryye
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"382e88db653423432432432wqewq","isNewRecord":false,"type":"3","content":"不合格发票 20张"}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBeanX message;
            private String readFlag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public MessageBeanX getMessage() {
                return message;
            }

            public void setMessage(MessageBeanX message) {
                this.message = message;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            public static class MessageBeanX {
                /**
                 * id : 382e88db653423432432432wqewq
                 * isNewRecord : false
                 * type : 3
                 * content : 不合格发票 20张
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isIsNewRecord() {
                    return isNewRecord;
                }

                public void setIsNewRecord(boolean isNewRecord) {
                    this.isNewRecord = isNewRecord;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }

        public static class _$4Bean {
            /**
             * id : retertertretewrggewtttttttttttttttttwtewt
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"382e88db6534234324324324324324","isNewRecord":false,"type":"4","content":"服务通知 20张"}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBeanXX message;
            private String readFlag;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public MessageBeanXX getMessage() {
                return message;
            }

            public void setMessage(MessageBeanXX message) {
                this.message = message;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            public static class MessageBeanXX {
                /**
                 * id : 382e88db6534234324324324324324
                 * isNewRecord : false
                 * type : 4
                 * content : 服务通知 20张
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isIsNewRecord() {
                    return isNewRecord;
                }

                public void setIsNewRecord(boolean isNewRecord) {
                    this.isNewRecord = isNewRecord;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }
            }
        }
    }
}
