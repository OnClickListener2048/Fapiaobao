package com.pilipa.fapiaobao.net.bean.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class MatchBean {


    /**
     * status : 200
     * msg : OK
     * data : [{"amount":200,"bonus":40,"attentions":"尽快送到","company":{"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"},"demandId":"2716a03d6221477a886438e6aebef4ed","invoiceVarieties":[{"id":"a02eb095-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}},{"id":"c37e973d-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"83ff72c08d6b4aac8f83df71b8283df9","isNewRecord":false,"name":"过路过桥费票"}},{"id":"d17706df-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"name":"餐饮票"}}]}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public static class DataBean implements Parcelable{
        /**
         * amount : 200
         * bonus : 40
         * attentions : 尽快送到
         * company : {"id":"e73859da2a4448069bd9f85887c890d2","isNewRecord":false,"remarks":"无","createDate":"2017-10-24 16:38:33","updateDate":"2017-10-24 16:38:33","customer":{"id":"6ee15c894b1a435d9c24025b324e17f7","isNewRecord":false},"name":"天津爱康鼎科技有限公司","taxno":"320400137674452","address":"天津市红桥区海河华鼎大厦","phone":"022-12554551","depositBank":"中国民生银行天津分行","account":"55555555555555555","qrcode":"www.sdsadsasd.dsada","isDefault":"1"}
         * demandId : 2716a03d6221477a886438e6aebef4ed
         * invoiceVarieties : [{"id":"a02eb095-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}},{"id":"c37e973d-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"83ff72c08d6b4aac8f83df71b8283df9","isNewRecord":false,"name":"过路过桥费票"}},{"id":"d17706df-ba53-11e7-ba5f-509a4c59a2df","isNewRecord":false,"demand":{"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false},"invoiceType":{"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"name":"餐饮票"}}]
         */

        private double amount;

        @Override
        public String toString() {
            return "DataBean{" +
                    "amount=" + amount +
                    ", bonus=" + bonus +
                    ", attentions='" + attentions + '\'' +
                    ", company=" + company +
                    ", demandId='" + demandId + '\'' +
                    ", invoiceVarieties=" + invoiceVarieties +
                    '}';
        }

        private double bonus;
        private String attentions;
        private CompanyBean company;
        private String demandId;
        private List<InvoiceVarietiesBean> invoiceVarieties;

        protected DataBean(Parcel in) {
            amount = in.readInt();
            bonus = in.readInt();
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

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(int bonus) {
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

        public static class CompanyBean {
            @Override
            public String toString() {
                return "CompanyBean{" +
                        "id='" + id + '\'' +
                        ", isNewRecord=" + isNewRecord +
                        ", remarks='" + remarks + '\'' +
                        ", createDate='" + createDate + '\'' +
                        ", updateDate='" + updateDate + '\'' +
                        ", customer=" + customer +
                        ", name='" + name + '\'' +
                        ", taxno='" + taxno + '\'' +
                        ", address='" + address + '\'' +
                        ", phone='" + phone + '\'' +
                        ", depositBank='" + depositBank + '\'' +
                        ", account='" + account + '\'' +
                        ", qrcode='" + qrcode + '\'' +
                        ", isDefault='" + isDefault + '\'' +
                        '}';
            }

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

            public static class CustomerBean {
                @Override
                public String toString() {
                    return "CustomerBean{" +
                            "id='" + id + '\'' +
                            ", isNewRecord=" + isNewRecord +
                            '}';
                }

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

        public static class InvoiceVarietiesBean {
            /**
             * id : a02eb095-ba53-11e7-ba5f-509a4c59a2df
             * isNewRecord : false
             * demand : {"id":"2716a03d6221477a886438e6aebef4ed","isNewRecord":false}
             * invoiceType : {"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿票"}
             */

            private String id;
            private boolean isNewRecord;
            private DemandBean demand;

            @Override
            public String toString() {
                return "InvoiceVarietiesBean{" +
                        "id='" + id + '\'' +
                        ", isNewRecord=" + isNewRecord +
                        ", demand=" + demand +
                        ", invoiceType=" + invoiceType +
                        '}';
            }

            private InvoiceTypeBean invoiceType;

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

            public static class DemandBean {
                @Override
                public String toString() {
                    return "DemandBean{" +
                            "id='" + id + '\'' +
                            ", isNewRecord=" + isNewRecord +
                            '}';
                }

                /**
                 * id : 2716a03d6221477a886438e6aebef4ed
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

            public static class InvoiceTypeBean {
                @Override
                public String toString() {
                    return "InvoiceTypeBean{" +
                            "id='" + id + '\'' +
                            ", isNewRecord=" + isNewRecord +
                            ", name='" + name + '\'' +
                            '}';
                }

                /**
                 * id : 46930352ad15484dbe667867a9abca99
                 * isNewRecord : false
                 * name : 住宿票
                 */

                private String id;
                private boolean isNewRecord;
                private String name;

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
