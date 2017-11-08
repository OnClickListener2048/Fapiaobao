package com.pilipa.fapiaobao.net.bean.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class MacherBeanToken implements Parcelable{

    /**
     * status : 200
     * msg : OK
     * data : [{"amount":50,"bonus":5,"attentions":"无","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandId":"e1e72aa12b2d4f06911cb6b648b124a5","invoiceVarieties":[{"id":"f351c55b-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"e1e72aa12b2d4f06911cb6b648b124a5","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}}]},{"amount":50,"bonus":0.5,"attentions":"无","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandId":"e1e72aa12b2d4f06911cb6b648b1240a","invoiceVarieties":[{"id":"fecc4a7d-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"e1e72aa12b2d4f06911cb6b648b1240a","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}}]},{"amount":50,"bonus":0.5,"attentions":"无","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandId":"e1e72aa12b2d4f06911cb6b648b12405","invoiceVarieties":[{"id":"03d1f93c-ba54-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"e1e72aa12b2d4f06911cb6b648b12405","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}}]}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected MacherBeanToken(Parcel in) {
        status = in.readInt();
        msg = in.readString();
        data = in.createTypedArrayList(DataBean.CREATOR);
    }

    public static final Creator<MacherBeanToken> CREATOR = new Creator<MacherBeanToken>() {
        @Override
        public MacherBeanToken createFromParcel(Parcel in) {
            return new MacherBeanToken(in);
        }

        @Override
        public MacherBeanToken[] newArray(int size) {
            return new MacherBeanToken[size];
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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
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
        dest.writeTypedList(data);
    }

    public static class DataBean implements Parcelable {
        /**
         * amount : 50.0
         * bonus : 5.0
         * attentions : 无
         * company : {"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"}
         * demandId : e1e72aa12b2d4f06911cb6b648b124a5
         * invoiceVarieties : [{"id":"f351c55b-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"e1e72aa12b2d4f06911cb6b648b124a5","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}}]
         */

        private double amount;
        private double bonus;
        private String attentions;
        private CompanyBean company;
        private String demandId;
        private List<InvoiceVarietiesBean> invoiceVarieties;

        protected DataBean(Parcel in) {
            amount = in.readDouble();
            bonus = in.readDouble();
            attentions = in.readString();
            demandId = in.readString();
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

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        public String getAttentions() {
            return attentions;
        }

        public void setAttentions(String attentions) {
            this.attentions = attentions;
        }

        public CompanyBean getCompany() {
            return company;
        }

        public void setCompany(CompanyBean company) {
            this.company = company;
        }

        public String getDemandId() {
            return demandId;
        }

        public void setDemandId(String demandId) {
            this.demandId = demandId;
        }

        public List<InvoiceVarietiesBean> getInvoiceVarieties() {
            return invoiceVarieties;
        }

        public void setInvoiceVarieties(List<InvoiceVarietiesBean> invoiceVarieties) {
            this.invoiceVarieties = invoiceVarieties;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeDouble(amount);
            dest.writeDouble(bonus);
            dest.writeString(attentions);
            dest.writeString(demandId);
        }

        public static class CompanyBean implements Parcelable{
            /**
             * id : e73859da2a4448069bd9f85887c890d2
             * isNewRecord : false
             * remarks : 无
             * createDate : 2017-10-24 16:38:33
             * updateDate : 2017-10-24 16:38:33
             * customer : {"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false}
             * name : 天津爱康鼎科技有限公司
             * taxno : 320400137674452
             * address : 天津市红桥区海河华鼎大厦
             * phone : 022-12554551
             * depositBank : 中国民生银行天津分行
             * account : 55555555555555555
             * qrcode : www.sdsadsasd.dsada
             * isDefault : 1
             */

            private String id;
            private boolean isNewRecord;
            private String remarks;
            private String createDate;
            private String updateDate;
            private CustomerBean customer;
            private String name;
            private String taxno;
            private String address;
            private String phone;
            private String depositBank;
            private String account;
            private String qrcode;
            private String isDefault;

            public CompanyBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                remarks = in.readString();
                createDate = in.readString();
                updateDate = in.readString();
                name = in.readString();
                taxno = in.readString();
                address = in.readString();
                phone = in.readString();
                depositBank = in.readString();
                account = in.readString();
                qrcode = in.readString();
                isDefault = in.readString();
            }
            public CompanyBean(){

            }
            public static final Creator<CompanyBean> CREATOR = new Creator<CompanyBean>() {
                @Override
                public CompanyBean createFromParcel(Parcel in) {
                    return new CompanyBean(in);
                }

                @Override
                public CompanyBean[] newArray(int size) {
                    return new CompanyBean[size];
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

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
            }

            public CustomerBean getCustomer() {
                return customer;
            }

            public void setCustomer(CustomerBean customer) {
                this.customer = customer;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTaxno() {
                return taxno;
            }

            public void setTaxno(String taxno) {
                this.taxno = taxno;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getDepositBank() {
                return depositBank;
            }

            public void setDepositBank(String depositBank) {
                this.depositBank = depositBank;
            }

            public String getAccount() {
                return account;
            }

            public void setAccount(String account) {
                this.account = account;
            }

            public String getQrcode() {
                return qrcode;
            }

            public void setQrcode(String qrcode) {
                this.qrcode = qrcode;
            }

            public String getIsDefault() {
                return isDefault;
            }

            public void setIsDefault(String isDefault) {
                this.isDefault = isDefault;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(remarks);
                dest.writeString(createDate);
                dest.writeString(updateDate);
                dest.writeString(name);
                dest.writeString(taxno);
                dest.writeString(address);
                dest.writeString(phone);
                dest.writeString(depositBank);
                dest.writeString(account);
                dest.writeString(qrcode);
                dest.writeString(isDefault);
            }

            public static class CustomerBean {
                /**
                 * id : 6ee15c894b1a435d9c24025b324e17f7
                 * isNewRecord : false
                 */

                private String id;
                private boolean isNewRecord;

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
            }
        }

        public static class InvoiceVarietiesBean implements Parcelable{
            /**
             * id : f351c55b-ba53-11e7-ba5f-509a4c59a2df
             * isNewRecord : false
             * demand : {"id":"e1e72aa12b2d4f06911cb6b648b124a5","isNewRecord":false}
             * invoiceType : {"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}
             */

            private String id;
            private boolean isNewRecord;
            private DemandBean demand;
            private InvoiceTypeBean invoiceType;

            protected InvoiceVarietiesBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
            }

            public static final Creator<InvoiceVarietiesBean> CREATOR = new Creator<InvoiceVarietiesBean>() {
                @Override
                public InvoiceVarietiesBean createFromParcel(Parcel in) {
                    return new InvoiceVarietiesBean(in);
                }

                @Override
                public InvoiceVarietiesBean[] newArray(int size) {
                    return new InvoiceVarietiesBean[size];
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

            public DemandBean getDemand() {
                return demand;
            }

            public void setDemand(DemandBean demand) {
                this.demand = demand;
            }

            public InvoiceTypeBean getInvoiceType() {
                return invoiceType;
            }

            public void setInvoiceType(InvoiceTypeBean invoiceType) {
                this.invoiceType = invoiceType;
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

            public static class DemandBean {
                /**
                 * id : e1e72aa12b2d4f06911cb6b648b124a5
                 * isNewRecord : false
                 */

                private String id;
                private boolean isNewRecord;

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
            }

            public static class InvoiceTypeBean implements Parcelable{
                /**
                 * id : 46930352ad15484dbe667867a9abca99
                 * isNewRecord : false
                 * name : 住宿票
                 */

                private String id;
                private boolean isNewRecord;
                private String name;

                protected InvoiceTypeBean(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    name = in.readString();
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(name);
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                public static final Creator<InvoiceTypeBean> CREATOR = new Creator<InvoiceTypeBean>() {
                    @Override
                    public InvoiceTypeBean createFromParcel(Parcel in) {
                        return new InvoiceTypeBean(in);
                    }

                    @Override
                    public InvoiceTypeBean[] newArray(int size) {
                        return new InvoiceTypeBean[size];
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

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}
