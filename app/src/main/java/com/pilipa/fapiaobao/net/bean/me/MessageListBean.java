package com.pilipa.fapiaobao.net.bean.me;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by edz on 2017/11/6.
 */

public class MessageListBean implements Parcelable {


    /**
     * status : 200
     * msg : OK
     * data : {"1":[{"id":"uyuyouyyuytg64ghfghgfh2333423","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db65a94df191b54654564654","isNewRecord":false,"type":"1","content":"新到发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"}],"2":[{"id":"43243242234gs4df191ba27f3fb1a4fee","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"}],"3":[{"id":"trewtrewtewtewt5ytrtryerteryteryeryye","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db653423432432432wqewq","isNewRecord":false,"type":"3","content":"不合格发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"}],"4":[{"id":"retertertretewrggewtttttttttttttttttwtewt","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324324324324324","isNewRecord":false,"type":"4","content":"服务通知 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"},{"id":"uyuyouyyuytg647675444f3242333423","isNewRecord":false,"createDate":"2017-11-08 11:00:33","message":{"id":"382e88db6534234324432432432324","isNewRecord":false,"type":"4","content":"服务通知 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}},"readFlag":"0"}]}
     */

    private int status;
    private String msg;
    private DataBean data;

    protected MessageListBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Creator<MessageListBean> CREATOR = new Creator<MessageListBean>() {
        @Override
        public MessageListBean createFromParcel(Parcel in) {
            return new MessageListBean(in);
        }

        @Override
        public MessageListBean[] newArray(int size) {
            return new MessageListBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(status);
        dest.writeString(msg);
        dest.writeParcelable(data, flags);
    }

    public static class DataBean implements Parcelable {
        @SerializedName("1")
        private ArrayList<_$1Bean> _$1;
        @SerializedName("2")
        private ArrayList<_$2Bean> _$2;
        @SerializedName("3")
        private ArrayList<_$3Bean> _$3;
        @SerializedName("4")
        private ArrayList<_$4Bean> _$4;

        public DataBean() {
        }
        public DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public ArrayList<_$1Bean> get_$1() {
            return _$1;
        }

        public void set_$1(ArrayList<_$1Bean> _$1) {
            this._$1 = _$1;
        }

        public ArrayList<_$2Bean> get_$2() {
            return _$2;
        }

        public void set_$2(ArrayList<_$2Bean> _$2) {
            this._$2 = _$2;
        }

        public ArrayList<_$3Bean> get_$3() {
            return _$3;
        }

        public void set_$3(ArrayList<_$3Bean> _$3) {
            this._$3 = _$3;
        }

        public ArrayList<_$4Bean> get_$4() {
            return _$4;
        }

        public void set_$4(ArrayList<_$4Bean> _$4) {
            this._$4 = _$4;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
        }

        public static class _$1Bean implements Parcelable {
            /**
             * id : uyuyouyyuytg64ghfghgfh2333423
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"382e88db65a94df191b54654564654","isNewRecord":false,"type":"1","content":"新到发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBean message;
            private String readFlag;

            protected _$1Bean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                readFlag = in.readString();
            }

            public static final Creator<_$1Bean> CREATOR = new Creator<_$1Bean>() {
                @Override
                public _$1Bean createFromParcel(Parcel in) {
                    return new _$1Bean(in);
                }

                @Override
                public _$1Bean[] newArray(int size) {
                    return new _$1Bean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(createDate);
                dest.writeString(readFlag);
            }

            public static class MessageBean implements Parcelable{
                /**
                 * id : 382e88db65a94df191b54654564654
                 * isNewRecord : false
                 * type : 1
                 * content : 新到发票 20张
                 * demand : {"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;
                private DemandBean demand;

                protected MessageBean(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    type = in.readString();
                    content = in.readString();
                }

                public static final Creator<MessageBean> CREATOR = new Creator<MessageBean>() {
                    @Override
                    public MessageBean createFromParcel(Parcel in) {
                        return new MessageBean(in);
                    }

                    @Override
                    public MessageBean[] newArray(int size) {
                        return new MessageBean[size];
                    }
                };

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

                public DemandBean getDemand() {
                    return demand;
                }

                public void setDemand(DemandBean demand) {
                    this.demand = demand;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(type);
                    dest.writeString(content);
                }

                public static class DemandBean implements  Parcelable{
                    /**
                     * id : 247b44d14a444a9394baa4b322f33fb7
                     * isNewRecord : false
                     * totalAmount : 0
                     * leftAmount : 0
                     * totalBonus : 0
                     * leftBonus : 0
                     * mailMinimum : 0
                     * beginTotalAmount : 0
                     * endTotalAmount : 0
                     * beginLeftAmount : 0
                     * endLeftAmount : 0
                     * beginTotalBonus : 0
                     * endTotalBonus : 0
                     * beginLeftBonus : 0
                     * endLeftBonus : 0
                     * beginMailMinimum : 0
                     * endMailMinimum : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private BigDecimal totalAmount;
                    private BigDecimal leftAmount;
                    private BigDecimal totalBonus;
                    private BigDecimal leftBonus;
                    private BigDecimal mailMinimum;
                    private BigDecimal beginTotalAmount;
                    private BigDecimal endTotalAmount;
                    private BigDecimal beginLeftAmount;
                    private BigDecimal endLeftAmount;
                    private BigDecimal beginTotalBonus;
                    private BigDecimal endTotalBonus;
                    private BigDecimal beginLeftBonus;
                    private BigDecimal endLeftBonus;
                    private BigDecimal beginMailMinimum;
                    private BigDecimal endMailMinimum;

                    protected DemandBean(Parcel in) {
                        id = in.readString();
                        isNewRecord = in.readByte() != 0;
                    }

                    public static final Creator<DemandBean> CREATOR = new Creator<DemandBean>() {
                        @Override
                        public DemandBean createFromParcel(Parcel in) {
                            return new DemandBean(in);
                        }

                        @Override
                        public DemandBean[] newArray(int size) {
                            return new DemandBean[size];
                        }
                    };

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

                    public BigDecimal getTotalAmount() {
                        return totalAmount;
                    }

                    public void setTotalAmount(BigDecimal totalAmount) {
                        this.totalAmount = totalAmount;
                    }

                    public BigDecimal getLeftAmount() {
                        return leftAmount;
                    }

                    public void setLeftAmount(BigDecimal leftAmount) {
                        this.leftAmount = leftAmount;
                    }

                    public BigDecimal getTotalBonus() {
                        return totalBonus;
                    }

                    public void setTotalBonus(BigDecimal totalBonus) {
                        this.totalBonus = totalBonus;
                    }

                    public BigDecimal getLeftBonus() {
                        return leftBonus;
                    }

                    public void setLeftBonus(BigDecimal leftBonus) {
                        this.leftBonus = leftBonus;
                    }

                    public BigDecimal getMailMinimum() {
                        return mailMinimum;
                    }

                    public void setMailMinimum(BigDecimal mailMinimum) {
                        this.mailMinimum = mailMinimum;
                    }

                    public BigDecimal getBeginTotalAmount() {
                        return beginTotalAmount;
                    }

                    public void setBeginTotalAmount(BigDecimal beginTotalAmount) {
                        this.beginTotalAmount = beginTotalAmount;
                    }

                    public BigDecimal getEndTotalAmount() {
                        return endTotalAmount;
                    }

                    public void setEndTotalAmount(BigDecimal endTotalAmount) {
                        this.endTotalAmount = endTotalAmount;
                    }

                    public BigDecimal getBeginLeftAmount() {
                        return beginLeftAmount;
                    }

                    public void setBeginLeftAmount(BigDecimal beginLeftAmount) {
                        this.beginLeftAmount = beginLeftAmount;
                    }

                    public BigDecimal getEndLeftAmount() {
                        return endLeftAmount;
                    }

                    public void setEndLeftAmount(BigDecimal endLeftAmount) {
                        this.endLeftAmount = endLeftAmount;
                    }

                    public BigDecimal getBeginTotalBonus() {
                        return beginTotalBonus;
                    }

                    public void setBeginTotalBonus(BigDecimal beginTotalBonus) {
                        this.beginTotalBonus = beginTotalBonus;
                    }

                    public BigDecimal getEndTotalBonus() {
                        return endTotalBonus;
                    }

                    public void setEndTotalBonus(BigDecimal endTotalBonus) {
                        this.endTotalBonus = endTotalBonus;
                    }

                    public BigDecimal getBeginLeftBonus() {
                        return beginLeftBonus;
                    }

                    public void setBeginLeftBonus(BigDecimal beginLeftBonus) {
                        this.beginLeftBonus = beginLeftBonus;
                    }

                    public BigDecimal getEndLeftBonus() {
                        return endLeftBonus;
                    }

                    public void setEndLeftBonus(BigDecimal endLeftBonus) {
                        this.endLeftBonus = endLeftBonus;
                    }

                    public BigDecimal getBeginMailMinimum() {
                        return beginMailMinimum;
                    }

                    public void setBeginMailMinimum(BigDecimal beginMailMinimum) {
                        this.beginMailMinimum = beginMailMinimum;
                    }

                    public BigDecimal getEndMailMinimum() {
                        return endMailMinimum;
                    }

                    public void setEndMailMinimum(BigDecimal endMailMinimum) {
                        this.endMailMinimum = endMailMinimum;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    }
                }
            }
        }

        public static class _$2Bean implements Parcelable{
            /**
             * id : 43243242234gs4df191ba27f3fb1a4fee
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"qewwqeqwewqewqewqewqewqewq","isNewRecord":false,"type":"2","content":"红包到账 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"begBigDecimalotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBeanX message;
            private String readFlag;

            protected _$2Bean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                readFlag = in.readString();
            }

            public static final Creator<_$2Bean> CREATOR = new Creator<_$2Bean>() {
                @Override
                public _$2Bean createFromParcel(Parcel in) {
                    return new _$2Bean(in);
                }

                @Override
                public _$2Bean[] newArray(int size) {
                    return new _$2Bean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(createDate);
                dest.writeString(readFlag);
            }

            public static class MessageBeanX implements Parcelable{
                /**
                 * id : qewwqeqwewqewqewqewqewqewq
                 * isNewRecord : false
                 * type : 2
                 * content : 红包到账 20张
                 * demand : {"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;
                private DemandBeanX demand;

                protected MessageBeanX(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    type = in.readString();
                    content = in.readString();
                }

                public static final Creator<MessageBeanX> CREATOR = new Creator<MessageBeanX>() {
                    @Override
                    public MessageBeanX createFromParcel(Parcel in) {
                        return new MessageBeanX(in);
                    }

                    @Override
                    public MessageBeanX[] newArray(int size) {
                        return new MessageBeanX[size];
                    }
                };

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

                public DemandBeanX getDemand() {
                    return demand;
                }

                public void setDemand(DemandBeanX demand) {
                    this.demand = demand;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(type);
                    dest.writeString(content);
                }

                public static class DemandBeanX implements Parcelable{
                    /**
                     * id : 247b44d14a444a9394baa4b322f33fb7
                     * isNewRecord : false
                     * totalAmount : 0
                     * leftAmount : 0
                     * totalBonus : 0
                     * leftBonus : 0
                     * mailMinimum : 0
                     * beginTotalAmount : 0
                     * endTotalAmount : 0
                     * beginLeftAmount : 0
                     * endLeftAmount : 0
                     * beginTotalBonus : 0
                     * endTotalBonus : 0
                     * beginLeftBonus : 0
                     * endLeftBonus : 0
                     * beginMailMinimum : 0
                     * endMailMinimum : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private BigDecimal totalAmount;
                    private BigDecimal leftAmount;
                    private BigDecimal totalBonus;
                    private BigDecimal leftBonus;
                    private BigDecimal mailMinimum;
                    private BigDecimal beginTotalAmount;
                    private BigDecimal endTotalAmount;
                    private BigDecimal beginLeftAmount;
                    private BigDecimal endLeftAmount;
                    private BigDecimal beginTotalBonus;
                    private BigDecimal endTotalBonus;
                    private BigDecimal beginLeftBonus;
                    private BigDecimal endLeftBonus;
                    private BigDecimal beginMailMinimum;
                    private BigDecimal endMailMinimum;

                    protected DemandBeanX(Parcel in) {
                        id = in.readString();
                        isNewRecord = in.readByte() != 0;
                    }

                    public static final Creator<DemandBeanX> CREATOR = new Creator<DemandBeanX>() {
                        @Override
                        public DemandBeanX createFromParcel(Parcel in) {
                            return new DemandBeanX(in);
                        }

                        @Override
                        public DemandBeanX[] newArray(int size) {
                            return new DemandBeanX[size];
                        }
                    };

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

                    public BigDecimal getTotalAmount() {
                        return totalAmount;
                    }

                    public void setTotalAmount(BigDecimal totalAmount) {
                        this.totalAmount = totalAmount;
                    }

                    public BigDecimal getLeftAmount() {
                        return leftAmount;
                    }

                    public void setLeftAmount(BigDecimal leftAmount) {
                        this.leftAmount = leftAmount;
                    }

                    public BigDecimal getTotalBonus() {
                        return totalBonus;
                    }

                    public void setTotalBonus(BigDecimal totalBonus) {
                        this.totalBonus = totalBonus;
                    }

                    public BigDecimal getLeftBonus() {
                        return leftBonus;
                    }

                    public void setLeftBonus(BigDecimal leftBonus) {
                        this.leftBonus = leftBonus;
                    }

                    public BigDecimal getMailMinimum() {
                        return mailMinimum;
                    }

                    public void setMailMinimum(BigDecimal mailMinimum) {
                        this.mailMinimum = mailMinimum;
                    }

                    public BigDecimal getBeginTotalAmount() {
                        return beginTotalAmount;
                    }

                    public void setBeginTotalAmount(BigDecimal beginTotalAmount) {
                        this.beginTotalAmount = beginTotalAmount;
                    }

                    public BigDecimal getEndTotalAmount() {
                        return endTotalAmount;
                    }

                    public void setEndTotalAmount(BigDecimal endTotalAmount) {
                        this.endTotalAmount = endTotalAmount;
                    }

                    public BigDecimal getBeginLeftAmount() {
                        return beginLeftAmount;
                    }

                    public void setBeginLeftAmount(BigDecimal beginLeftAmount) {
                        this.beginLeftAmount = beginLeftAmount;
                    }

                    public BigDecimal getEndLeftAmount() {
                        return endLeftAmount;
                    }

                    public void setEndLeftAmount(BigDecimal endLeftAmount) {
                        this.endLeftAmount = endLeftAmount;
                    }

                    public BigDecimal getBeginTotalBonus() {
                        return beginTotalBonus;
                    }

                    public void setBeginTotalBonus(BigDecimal beginTotalBonus) {
                        this.beginTotalBonus = beginTotalBonus;
                    }

                    public BigDecimal getEndTotalBonus() {
                        return endTotalBonus;
                    }

                    public void setEndTotalBonus(BigDecimal endTotalBonus) {
                        this.endTotalBonus = endTotalBonus;
                    }

                    public BigDecimal getBeginLeftBonus() {
                        return beginLeftBonus;
                    }

                    public void setBeginLeftBonus(BigDecimal beginLeftBonus) {
                        this.beginLeftBonus = beginLeftBonus;
                    }

                    public BigDecimal getEndLeftBonus() {
                        return endLeftBonus;
                    }

                    public void setEndLeftBonus(BigDecimal endLeftBonus) {
                        this.endLeftBonus = endLeftBonus;
                    }

                    public BigDecimal getBeginMailMinimum() {
                        return beginMailMinimum;
                    }

                    public void setBeginMailMinimum(BigDecimal beginMailMinimum) {
                        this.beginMailMinimum = beginMailMinimum;
                    }

                    public BigDecimal getEndMailMinimum() {
                        return endMailMinimum;
                    }

                    public void setEndMailMinimum(BigDecimal endMailMinimum) {
                        this.endMailMinimum = endMailMinimum;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    }
                }
            }
        }

        public static class _$3Bean implements Parcelable{
            /**
             * id : trewtrewtewtewt5ytrtryerteryteryeryye
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"382e88db653423432432432wqewq","isNewRecord":false,"type":"3","content":"不合格发票 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBeanXX message;
            private String readFlag;

            protected _$3Bean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                readFlag = in.readString();
            }

            public static final Creator<_$3Bean> CREATOR = new Creator<_$3Bean>() {
                @Override
                public _$3Bean createFromParcel(Parcel in) {
                    return new _$3Bean(in);
                }

                @Override
                public _$3Bean[] newArray(int size) {
                    return new _$3Bean[size];
                }
            };

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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(createDate);
                dest.writeString(readFlag);
            }

            public static class MessageBeanXX implements Parcelable{
                /**
                 * id : 382e88db653423432432432wqewq
                 * isNewRecord : false
                 * type : 3
                 * content : 不合格发票 20张
                 * demand : {"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;
                private DemandBeanXX demand;

                protected MessageBeanXX(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    type = in.readString();
                    content = in.readString();
                }

                public static final Creator<MessageBeanXX> CREATOR = new Creator<MessageBeanXX>() {
                    @Override
                    public MessageBeanXX createFromParcel(Parcel in) {
                        return new MessageBeanXX(in);
                    }

                    @Override
                    public MessageBeanXX[] newArray(int size) {
                        return new MessageBeanXX[size];
                    }
                };

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

                public DemandBeanXX getDemand() {
                    return demand;
                }

                public void setDemand(DemandBeanXX demand) {
                    this.demand = demand;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(type);
                    dest.writeString(content);
                }

                public static class DemandBeanXX implements Parcelable {
                    /**
                     * id : 247b44d14a444a9394baa4b322f33fb7
                     * isNewRecord : false
                     * totalAmount : 0
                     * leftAmount : 0
                     * totalBonus : 0
                     * leftBonus : 0
                     * mailMinimum : 0
                     * beginTotalAmount : 0
                     * endTotalAmount : 0
                     * beginLeftAmount : 0
                     * endLeftAmount : 0
                     * beginTotalBonus : 0
                     * endTotalBonus : 0
                     * beginLeftBonus : 0
                     * endLeftBonus : 0
                     * beginMailMinimum : 0
                     * endMailMinimum : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private BigDecimal totalAmount;
                    private BigDecimal leftAmount;
                    private BigDecimal totalBonus;
                    private BigDecimal leftBonus;
                    private BigDecimal mailMinimum;
                    private BigDecimal beginTotalAmount;
                    private BigDecimal endTotalAmount;
                    private BigDecimal beginLeftAmount;
                    private BigDecimal endLeftAmount;
                    private BigDecimal beginTotalBonus;
                    private BigDecimal endTotalBonus;
                    private BigDecimal beginLeftBonus;
                    private BigDecimal endLeftBonus;
                    private BigDecimal beginMailMinimum;
                    private BigDecimal endMailMinimum;

                    protected DemandBeanXX(Parcel in) {
                        id = in.readString();
                        isNewRecord = in.readByte() != 0;
                    }

                    public static final Creator<DemandBeanXX> CREATOR = new Creator<DemandBeanXX>() {
                        @Override
                        public DemandBeanXX createFromParcel(Parcel in) {
                            return new DemandBeanXX(in);
                        }

                        @Override
                        public DemandBeanXX[] newArray(int size) {
                            return new DemandBeanXX[size];
                        }
                    };

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

                    public BigDecimal getTotalAmount() {
                        return totalAmount;
                    }

                    public void setTotalAmount(BigDecimal totalAmount) {
                        this.totalAmount = totalAmount;
                    }

                    public BigDecimal getLeftAmount() {
                        return leftAmount;
                    }

                    public void setLeftAmount(BigDecimal leftAmount) {
                        this.leftAmount = leftAmount;
                    }

                    public BigDecimal getTotalBonus() {
                        return totalBonus;
                    }

                    public void setTotalBonus(BigDecimal totalBonus) {
                        this.totalBonus = totalBonus;
                    }

                    public BigDecimal getLeftBonus() {
                        return leftBonus;
                    }

                    public void setLeftBonus(BigDecimal leftBonus) {
                        this.leftBonus = leftBonus;
                    }

                    public BigDecimal getMailMinimum() {
                        return mailMinimum;
                    }

                    public void setMailMinimum(BigDecimal mailMinimum) {
                        this.mailMinimum = mailMinimum;
                    }

                    public BigDecimal getBeginTotalAmount() {
                        return beginTotalAmount;
                    }

                    public void setBeginTotalAmount(BigDecimal beginTotalAmount) {
                        this.beginTotalAmount = beginTotalAmount;
                    }

                    public BigDecimal getEndTotalAmount() {
                        return endTotalAmount;
                    }

                    public void setEndTotalAmount(BigDecimal endTotalAmount) {
                        this.endTotalAmount = endTotalAmount;
                    }

                    public BigDecimal getBeginLeftAmount() {
                        return beginLeftAmount;
                    }

                    public void setBeginLeftAmount(BigDecimal beginLeftAmount) {
                        this.beginLeftAmount = beginLeftAmount;
                    }

                    public BigDecimal getEndLeftAmount() {
                        return endLeftAmount;
                    }

                    public void setEndLeftAmount(BigDecimal endLeftAmount) {
                        this.endLeftAmount = endLeftAmount;
                    }

                    public BigDecimal getBeginTotalBonus() {
                        return beginTotalBonus;
                    }

                    public void setBeginTotalBonus(BigDecimal beginTotalBonus) {
                        this.beginTotalBonus = beginTotalBonus;
                    }

                    public BigDecimal getEndTotalBonus() {
                        return endTotalBonus;
                    }

                    public void setEndTotalBonus(BigDecimal endTotalBonus) {
                        this.endTotalBonus = endTotalBonus;
                    }

                    public BigDecimal getBeginLeftBonus() {
                        return beginLeftBonus;
                    }

                    public void setBeginLeftBonus(BigDecimal beginLeftBonus) {
                        this.beginLeftBonus = beginLeftBonus;
                    }

                    public BigDecimal getEndLeftBonus() {
                        return endLeftBonus;
                    }

                    public void setEndLeftBonus(BigDecimal endLeftBonus) {
                        this.endLeftBonus = endLeftBonus;
                    }

                    public BigDecimal getBeginMailMinimum() {
                        return beginMailMinimum;
                    }

                    public void setBeginMailMinimum(BigDecimal beginMailMinimum) {
                        this.beginMailMinimum = beginMailMinimum;
                    }

                    public BigDecimal getEndMailMinimum() {
                        return endMailMinimum;
                    }

                    public void setEndMailMinimum(BigDecimal endMailMinimum) {
                        this.endMailMinimum = endMailMinimum;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    }
                }
            }
        }

        public static class _$4Bean implements Parcelable{
            /**
             * id : retertertretewrggewtttttttttttttttttwtewt
             * isNewRecord : false
             * createDate : 2017-11-08 11:00:33
             * message : {"id":"382e88db6534234324324324324324","isNewRecord":false,"type":"4","content":"服务通知 20张","demand":{"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}}
             * readFlag : 0
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private MessageBeanXXX message;
            private String readFlag;

            protected _$4Bean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                readFlag = in.readString();
            }

            public static final Creator<_$4Bean> CREATOR = new Creator<_$4Bean>() {
                @Override
                public _$4Bean createFromParcel(Parcel in) {
                    return new _$4Bean(in);
                }

                @Override
                public _$4Bean[] newArray(int size) {
                    return new _$4Bean[size];
                }
            };

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

            public MessageBeanXXX getMessage() {
                return message;
            }

            public void setMessage(MessageBeanXXX message) {
                this.message = message;
            }

            public String getReadFlag() {
                return readFlag;
            }

            public void setReadFlag(String readFlag) {
                this.readFlag = readFlag;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(createDate);
                dest.writeString(readFlag);
            }

            public static class MessageBeanXXX implements Parcelable{
                /**
                 * id : 382e88db6534234324324324324324
                 * isNewRecord : false
                 * type : 4
                 * content : 服务通知 20张
                 * demand : {"id":"247b44d14a444a9394baa4b322f33fb7","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
                 */

                private String id;
                private boolean isNewRecord;
                private String type;
                private String content;
                private DemandBeanXXX demand;

                protected MessageBeanXXX(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    type = in.readString();
                    content = in.readString();
                    demand = in.readParcelable(DemandBeanXXX.class.getClassLoader());
                }

                public static final Creator<MessageBeanXXX> CREATOR = new Creator<MessageBeanXXX>() {
                    @Override
                    public MessageBeanXXX createFromParcel(Parcel in) {
                        return new MessageBeanXXX(in);
                    }

                    @Override
                    public MessageBeanXXX[] newArray(int size) {
                        return new MessageBeanXXX[size];
                    }
                };

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

                public DemandBeanXXX getDemand() {
                    return demand;
                }

                public void setDemand(DemandBeanXXX demand) {
                    this.demand = demand;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(type);
                    dest.writeString(content);
                    dest.writeParcelable(demand, flags);
                }

                public static class DemandBeanXXX implements Parcelable{
                    /**
                     * id : 247b44d14a444a9394baa4b322f33fb7
                     * isNewRecord : false
                     * totalAmount : 0
                     * leftAmount : 0
                     * totalBonus : 0
                     * leftBonus : 0
                     * mailMinimum : 0
                     * beginTotalAmount : 0
                     * endTotalAmount : 0
                     * beginLeftAmount : 0
                     * endLeftAmount : 0
                     * beginTotalBonus : 0
                     * endTotalBonus : 0
                     * beginLeftBonus : 0
                     * endLeftBonus : 0
                     * beginMailMinimum : 0
                     * endMailMinimum : 0
                     */

                    private String id;
                    private boolean isNewRecord;
                    private BigDecimal totalAmount;
                    private BigDecimal leftAmount;
                    private BigDecimal totalBonus;
                    private BigDecimal leftBonus;
                    private BigDecimal mailMinimum;
                    private BigDecimal beginTotalAmount;
                    private BigDecimal endTotalAmount;
                    private BigDecimal beginLeftAmount;
                    private BigDecimal endLeftAmount;
                    private BigDecimal beginTotalBonus;
                    private BigDecimal endTotalBonus;
                    private BigDecimal beginLeftBonus;
                    private BigDecimal endLeftBonus;
                    private BigDecimal beginMailMinimum;
                    private BigDecimal endMailMinimum;

                    protected DemandBeanXXX(Parcel in) {
                        id = in.readString();
                        isNewRecord = in.readByte() != 0;
                    }

                    public static final Creator<DemandBeanXXX> CREATOR = new Creator<DemandBeanXXX>() {
                        @Override
                        public DemandBeanXXX createFromParcel(Parcel in) {
                            return new DemandBeanXXX(in);
                        }

                        @Override
                        public DemandBeanXXX[] newArray(int size) {
                            return new DemandBeanXXX[size];
                        }
                    };

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

                    public BigDecimal getTotalAmount() {
                        return totalAmount;
                    }

                    public void setTotalAmount(BigDecimal totalAmount) {
                        this.totalAmount = totalAmount;
                    }

                    public BigDecimal getLeftAmount() {
                        return leftAmount;
                    }

                    public void setLeftAmount(BigDecimal leftAmount) {
                        this.leftAmount = leftAmount;
                    }

                    public BigDecimal getTotalBonus() {
                        return totalBonus;
                    }

                    public void setTotalBonus(BigDecimal totalBonus) {
                        this.totalBonus = totalBonus;
                    }

                    public BigDecimal getLeftBonus() {
                        return leftBonus;
                    }

                    public void setLeftBonus(BigDecimal leftBonus) {
                        this.leftBonus = leftBonus;
                    }

                    public BigDecimal getMailMinimum() {
                        return mailMinimum;
                    }

                    public void setMailMinimum(BigDecimal mailMinimum) {
                        this.mailMinimum = mailMinimum;
                    }

                    public BigDecimal getBeginTotalAmount() {
                        return beginTotalAmount;
                    }

                    public void setBeginTotalAmount(BigDecimal beginTotalAmount) {
                        this.beginTotalAmount = beginTotalAmount;
                    }

                    public BigDecimal getEndTotalAmount() {
                        return endTotalAmount;
                    }

                    public void setEndTotalAmount(BigDecimal endTotalAmount) {
                        this.endTotalAmount = endTotalAmount;
                    }

                    public BigDecimal getBeginLeftAmount() {
                        return beginLeftAmount;
                    }

                    public void setBeginLeftAmount(BigDecimal beginLeftAmount) {
                        this.beginLeftAmount = beginLeftAmount;
                    }

                    public BigDecimal getEndLeftAmount() {
                        return endLeftAmount;
                    }

                    public void setEndLeftAmount(BigDecimal endLeftAmount) {
                        this.endLeftAmount = endLeftAmount;
                    }

                    public BigDecimal getBeginTotalBonus() {
                        return beginTotalBonus;
                    }

                    public void setBeginTotalBonus(BigDecimal beginTotalBonus) {
                        this.beginTotalBonus = beginTotalBonus;
                    }

                    public BigDecimal getEndTotalBonus() {
                        return endTotalBonus;
                    }

                    public void setEndTotalBonus(BigDecimal endTotalBonus) {
                        this.endTotalBonus = endTotalBonus;
                    }

                    public BigDecimal getBeginLeftBonus() {
                        return beginLeftBonus;
                    }

                    public void setBeginLeftBonus(BigDecimal beginLeftBonus) {
                        this.beginLeftBonus = beginLeftBonus;
                    }

                    public BigDecimal getEndLeftBonus() {
                        return endLeftBonus;
                    }

                    public void setEndLeftBonus(BigDecimal endLeftBonus) {
                        this.endLeftBonus = endLeftBonus;
                    }

                    public BigDecimal getBeginMailMinimum() {
                        return beginMailMinimum;
                    }

                    public void setBeginMailMinimum(BigDecimal beginMailMinimum) {
                        this.beginMailMinimum = beginMailMinimum;
                    }

                    public BigDecimal getEndMailMinimum() {
                        return endMailMinimum;
                    }

                    public void setEndMailMinimum(BigDecimal endMailMinimum) {
                        this.endMailMinimum = endMailMinimum;
                    }

                    @Override
                    public int describeContents() {
                        return 0;
                    }

                    @Override
                    public void writeToParcel(Parcel dest, int flags) {
                        dest.writeString(id);
                        dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    }
                }
            }
        }
    }
}
