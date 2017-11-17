package com.pilipa.fapiaobao.net.bean.invoice;

import android.os.Parcel;
import android.os.Parcelable;

import com.pilipa.fapiaobao.entity.Company;

import java.util.List;

/**
 * Created by edz on 2017/10/30.
 */

public class MacherBeanToken implements Parcelable{

    /**
     * status : 200
     * msg : OK
     * data : [{"amount":50,"bonus":5,"attentions":"","company":{"id":"242443fe0f044f218b022479562f436d","isNewRecord":false,"createDate":"2017-11-16 17:35:47","updateDate":"2017-11-16 17:35:47","customer":{"id":"4ac795cf4d934e48b5039d27a5161fd6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"王大鑫天津科技有限公司","taxno":"01234568790123","address":"王大鑫公路往前走","phone":"123546879","depositBank":"王大鑫天津工商银行","account":"121323125689761231","qrcode":"https://www.youpiao8.cn/fapiaobao/share/billingInfo?id=242443fe0f044f218b022479562f436d"},"demandId":"f4a3606d08984d7f80253fe554830f63","deadline":1511971200000,"invoiceType":"会议费","mailMinimum":10,"invoiceTypes":[{"id":"3d6dd28e93ae44529bc51b6328a635ab","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"name":"餐饮费","categorySort":0,"frequentSort":0}},{"id":"7dc8e4d87ee44c91820a0f2a1976ae78","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议费","categorySort":0,"frequentSort":0}},{"id":"91693da01129448f96bc45b65ceb32f5","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46a421bf1c0e4fa0a5f8260334096b92","isNewRecord":false,"name":"快递费","categorySort":0,"frequentSort":0}},{"id":"9d4d83bea4f34a3894e655369f81f32a","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"8d8cdbb335b84e0a89c92e75e1f0c1c3","isNewRecord":false,"name":"礼品费","categorySort":0,"frequentSort":0}},{"id":"cc4ab112089742d19acfc6ae9bd7c6d2","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿费","categorySort":0,"frequentSort":0}}]},{"amount":50,"bonus":5,"attentions":"","company":{"id":"242443fe0f044f218b022479562f436d","isNewRecord":false,"createDate":"2017-11-16 17:35:47","updateDate":"2017-11-16 17:35:47","customer":{"id":"4ac795cf4d934e48b5039d27a5161fd6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"王大鑫天津科技有限公司","taxno":"01234568790123","address":"王大鑫公路往前走","phone":"123546879","depositBank":"王大鑫天津工商银行","account":"121323125689761231","qrcode":"https://www.youpiao8.cn/fapiaobao/share/billingInfo?id=242443fe0f044f218b022479562f436d"},"demandId":"b58749ddfe404e57824cb7b726edc2aa","deadline":1511971200000,"invoiceType":"会议费","mailMinimum":0,"invoiceTypes":[{"id":"1e89ba94d7ca42eb83340c43ce1055cf","isNewRecord":false,"createDate":"2017-11-16 17:48:10","updateDate":"2017-11-16 17:48:10","demand":{"id":"b58749ddfe404e57824cb7b726edc2aa","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"8d8cdbb335b84e0a89c92e75e1f0c1c3","isNewRecord":false,"name":"礼品费","categorySort":0,"frequentSort":0}},{"id":"8d3eb39a91ce40568e0314fbf3b7b243","isNewRecord":false,"createDate":"2017-11-16 17:48:10","updateDate":"2017-11-16 17:48:10","demand":{"id":"b58749ddfe404e57824cb7b726edc2aa","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议费","categorySort":0,"frequentSort":0}},{"id":"9c445007ad0149d5a0648e2fa1a34e68","isNewRecord":false,"createDate":"2017-11-16 17:48:10","updateDate":"2017-11-16 17:48:10","demand":{"id":"b58749ddfe404e57824cb7b726edc2aa","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿费","categorySort":0,"frequentSort":0}}]},{"amount":50,"bonus":0,"attentions":"trust去去去","company":{"id":"da170bcdc7f74d01897b6d67508afe37","isNewRecord":false,"createDate":"2017-11-16 16:00:11","updateDate":"2017-11-16 16:00:11","customer":{"id":"8c19519eb43a418b961af0b02b0fa281","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"王大鑫天津科技有限公司","taxno":"01234568790123","address":"王大鑫公路往前走","phone":"123546879","depositBank":"王大鑫天津工商银行","account":"121323125689761231","qrcode":"https://www.youpiao8.cn/fapiaobao/share/billingInfo?id=da170bcdc7f74d01897b6d67508afe37"},"demandId":"386ce079f70a490eb0e84122f458993d","deadline":1511971200000,"invoiceType":"会议费","mailMinimum":10,"invoiceTypes":[{"id":"0361d9c06f254fd189248b4483d7fd0d","isNewRecord":false,"createDate":"2017-11-16 19:34:02","updateDate":"2017-11-16 19:34:02","demand":{"id":"386ce079f70a490eb0e84122f458993d","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议费","categorySort":0,"frequentSort":0}},{"id":"f8311d9b445c4b31aff7b1ec3df3238f","isNewRecord":false,"createDate":"2017-11-16 19:34:02","updateDate":"2017-11-16 19:34:02","demand":{"id":"386ce079f70a490eb0e84122f458993d","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿费","categorySort":0,"frequentSort":0}}]}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

    protected MacherBeanToken(Parcel in) {
        status = in.readInt();
        msg = in.readString();
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
    }

    public static class DataBean implements Parcelable{
        /**
         * amount : 50
         * bonus : 5
         * attentions :
         * company : {"id":"242443fe0f044f218b022479562f436d","isNewRecord":false,"createDate":"2017-11-16 17:35:47","updateDate":"2017-11-16 17:35:47","customer":{"id":"4ac795cf4d934e48b5039d27a5161fd6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0},"name":"王大鑫天津科技有限公司","taxno":"01234568790123","address":"王大鑫公路往前走","phone":"123546879","depositBank":"王大鑫天津工商银行","account":"121323125689761231","qrcode":"https://www.youpiao8.cn/fapiaobao/share/billingInfo?id=242443fe0f044f218b022479562f436d"}
         * demandId : f4a3606d08984d7f80253fe554830f63
         * deadline : 1511971200000
         * invoiceType : 会议费
         * mailMinimum : 10
         * invoiceTypes : [{"id":"3d6dd28e93ae44529bc51b6328a635ab","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"name":"餐饮费","categorySort":0,"frequentSort":0}},{"id":"7dc8e4d87ee44c91820a0f2a1976ae78","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"43f1faf2cfee4f508d02c36975dfa06d","isNewRecord":false,"name":"会议费","categorySort":0,"frequentSort":0}},{"id":"91693da01129448f96bc45b65ceb32f5","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46a421bf1c0e4fa0a5f8260334096b92","isNewRecord":false,"name":"快递费","categorySort":0,"frequentSort":0}},{"id":"9d4d83bea4f34a3894e655369f81f32a","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"8d8cdbb335b84e0a89c92e75e1f0c1c3","isNewRecord":false,"name":"礼品费","categorySort":0,"frequentSort":0}},{"id":"cc4ab112089742d19acfc6ae9bd7c6d2","isNewRecord":false,"createDate":"2017-11-16 17:46:40","updateDate":"2017-11-16 17:46:40","demand":{"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0},"invoiceType":{"id":"46930352ad15484dbe667867a9abca99","isNewRecord":false,"name":"住宿费","categorySort":0,"frequentSort":0}}]
         */

        private double amount;
        private double bonus;
        private String attentions;
        private CompanyBean company;
        private String demandId;
        private long deadline;
        private String invoiceType;
        private double mailMinimum;
        private List<InvoiceTypesBean> invoiceTypes;

        protected DataBean(Parcel in) {
            amount = in.readDouble();
            bonus = in.readDouble();
            attentions = in.readString();
            demandId = in.readString();
            deadline = in.readLong();
            invoiceType = in.readString();
            mailMinimum = in.readDouble();
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

        public long getDeadline() {
            return deadline;
        }

        public void setDeadline(long deadline) {
            this.deadline = deadline;
        }

        public String getInvoiceType() {
            return invoiceType;
        }

        public void setInvoiceType(String invoiceType) {
            this.invoiceType = invoiceType;
        }

        public double getMailMinimum() {
            return mailMinimum;
        }

        public void setMailMinimum(double mailMinimum) {
            this.mailMinimum = mailMinimum;
        }

        public List<InvoiceTypesBean> getInvoiceTypes() {
            return invoiceTypes;
        }

        public void setInvoiceTypes(List<InvoiceTypesBean> invoiceTypes) {
            this.invoiceTypes = invoiceTypes;
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
            dest.writeLong(deadline);
            dest.writeString(invoiceType);
            dest.writeDouble(mailMinimum);
        }

        public static class CompanyBean implements Parcelable{
            /**
             * id : 242443fe0f044f218b022479562f436d
             * isNewRecord : false
             * createDate : 2017-11-16 17:35:47
             * updateDate : 2017-11-16 17:35:47
             * customer : {"id":"4ac795cf4d934e48b5039d27a5161fd6","isNewRecord":false,"amount":0,"bonus":0,"frozen":0,"creditScore":0,"creditLevel":0,"beginAmount":0,"endAmount":0,"beginBonus":0,"endBonus":0,"beginFrozen":0,"endFrozen":0}
             * name : 王大鑫天津科技有限公司
             * taxno : 01234568790123
             * address : 王大鑫公路往前走
             * phone : 123546879
             * depositBank : 王大鑫天津工商银行
             * account : 121323125689761231
             * qrcode : https://www.youpiao8.cn/fapiaobao/share/billingInfo?id=242443fe0f044f218b022479562f436d
             */

            private String id;
            private boolean isNewRecord;
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

            public CompanyBean() {

            }

            protected CompanyBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                updateDate = in.readString();
                name = in.readString();
                taxno = in.readString();
                address = in.readString();
                phone = in.readString();
                depositBank = in.readString();
                account = in.readString();
                qrcode = in.readString();
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(id);
                dest.writeByte((byte) (isNewRecord ? 1 : 0));
                dest.writeString(createDate);
                dest.writeString(updateDate);
                dest.writeString(name);
                dest.writeString(taxno);
                dest.writeString(address);
                dest.writeString(phone);
                dest.writeString(depositBank);
                dest.writeString(account);
                dest.writeString(qrcode);
            }

            public static class CustomerBean implements Parcelable{
                /**
                 * id : 4ac795cf4d934e48b5039d27a5161fd6
                 * isNewRecord : false
                 * amount : 0
                 * bonus : 0
                 * frozen : 0
                 * creditScore : 0
                 * creditLevel : 0
                 * beginAmount : 0
                 * endAmount : 0
                 * beginBonus : 0
                 * endBonus : 0
                 * beginFrozen : 0
                 * endFrozen : 0
                 */

                private String id;
                private boolean isNewRecord;
                private int amount;
                private int bonus;
                private int frozen;
                private int creditScore;
                private int creditLevel;
                private int beginAmount;
                private int endAmount;
                private int beginBonus;
                private int endBonus;
                private int beginFrozen;
                private int endFrozen;

                protected CustomerBean(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    amount = in.readInt();
                    bonus = in.readInt();
                    frozen = in.readInt();
                    creditScore = in.readInt();
                    creditLevel = in.readInt();
                    beginAmount = in.readInt();
                    endAmount = in.readInt();
                    beginBonus = in.readInt();
                    endBonus = in.readInt();
                    beginFrozen = in.readInt();
                    endFrozen = in.readInt();
                }

                public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
                    @Override
                    public CustomerBean createFromParcel(Parcel in) {
                        return new CustomerBean(in);
                    }

                    @Override
                    public CustomerBean[] newArray(int size) {
                        return new CustomerBean[size];
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

                public int getAmount() {
                    return amount;
                }

                public void setAmount(int amount) {
                    this.amount = amount;
                }

                public int getBonus() {
                    return bonus;
                }

                public void setBonus(int bonus) {
                    this.bonus = bonus;
                }

                public int getFrozen() {
                    return frozen;
                }

                public void setFrozen(int frozen) {
                    this.frozen = frozen;
                }

                public int getCreditScore() {
                    return creditScore;
                }

                public void setCreditScore(int creditScore) {
                    this.creditScore = creditScore;
                }

                public int getCreditLevel() {
                    return creditLevel;
                }

                public void setCreditLevel(int creditLevel) {
                    this.creditLevel = creditLevel;
                }

                public int getBeginAmount() {
                    return beginAmount;
                }

                public void setBeginAmount(int beginAmount) {
                    this.beginAmount = beginAmount;
                }

                public int getEndAmount() {
                    return endAmount;
                }

                public void setEndAmount(int endAmount) {
                    this.endAmount = endAmount;
                }

                public int getBeginBonus() {
                    return beginBonus;
                }

                public void setBeginBonus(int beginBonus) {
                    this.beginBonus = beginBonus;
                }

                public int getEndBonus() {
                    return endBonus;
                }

                public void setEndBonus(int endBonus) {
                    this.endBonus = endBonus;
                }

                public int getBeginFrozen() {
                    return beginFrozen;
                }

                public void setBeginFrozen(int beginFrozen) {
                    this.beginFrozen = beginFrozen;
                }

                public int getEndFrozen() {
                    return endFrozen;
                }

                public void setEndFrozen(int endFrozen) {
                    this.endFrozen = endFrozen;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeInt(amount);
                    dest.writeInt(bonus);
                    dest.writeInt(frozen);
                    dest.writeInt(creditScore);
                    dest.writeInt(creditLevel);
                    dest.writeInt(beginAmount);
                    dest.writeInt(endAmount);
                    dest.writeInt(beginBonus);
                    dest.writeInt(endBonus);
                    dest.writeInt(beginFrozen);
                    dest.writeInt(endFrozen);
                }
            }
        }

        public static class InvoiceTypesBean implements Parcelable{
            /**
             * id : 3d6dd28e93ae44529bc51b6328a635ab
             * isNewRecord : false
             * createDate : 2017-11-16 17:46:40
             * updateDate : 2017-11-16 17:46:40
             * demand : {"id":"f4a3606d08984d7f80253fe554830f63","isNewRecord":false,"totalAmount":0,"leftAmount":0,"totalBonus":0,"leftBonus":0,"mailMinimum":0,"beginTotalAmount":0,"endTotalAmount":0,"beginLeftAmount":0,"endLeftAmount":0,"beginTotalBonus":0,"endTotalBonus":0,"beginLeftBonus":0,"endLeftBonus":0,"beginMailMinimum":0,"endMailMinimum":0}
             * invoiceType : {"id":"bac478bb52e6440fa745e15d53e42282","isNewRecord":false,"name":"餐饮费","categorySort":0,"frequentSort":0}
             */

            private String id;
            private boolean isNewRecord;
            private String createDate;
            private String updateDate;
            private DemandBean demand;
            private InvoiceTypeBean invoiceType;

            protected InvoiceTypesBean(Parcel in) {
                id = in.readString();
                isNewRecord = in.readByte() != 0;
                createDate = in.readString();
                updateDate = in.readString();
            }

            public static final Creator<InvoiceTypesBean> CREATOR = new Creator<InvoiceTypesBean>() {
                @Override
                public InvoiceTypesBean createFromParcel(Parcel in) {
                    return new InvoiceTypesBean(in);
                }

                @Override
                public InvoiceTypesBean[] newArray(int size) {
                    return new InvoiceTypesBean[size];
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

            public String getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(String updateDate) {
                this.updateDate = updateDate;
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
                dest.writeString(createDate);
                dest.writeString(updateDate);
            }

            public static class DemandBean implements Parcelable{
                /**
                 * id : f4a3606d08984d7f80253fe554830f63
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
                private int totalAmount;
                private int leftAmount;
                private int totalBonus;
                private int leftBonus;
                private int mailMinimum;
                private int beginTotalAmount;
                private int endTotalAmount;
                private int beginLeftAmount;
                private int endLeftAmount;
                private int beginTotalBonus;
                private int endTotalBonus;
                private int beginLeftBonus;
                private int endLeftBonus;
                private int beginMailMinimum;
                private int endMailMinimum;

                protected DemandBean(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    totalAmount = in.readInt();
                    leftAmount = in.readInt();
                    totalBonus = in.readInt();
                    leftBonus = in.readInt();
                    mailMinimum = in.readInt();
                    beginTotalAmount = in.readInt();
                    endTotalAmount = in.readInt();
                    beginLeftAmount = in.readInt();
                    endLeftAmount = in.readInt();
                    beginTotalBonus = in.readInt();
                    endTotalBonus = in.readInt();
                    beginLeftBonus = in.readInt();
                    endLeftBonus = in.readInt();
                    beginMailMinimum = in.readInt();
                    endMailMinimum = in.readInt();
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

                public int getTotalAmount() {
                    return totalAmount;
                }

                public void setTotalAmount(int totalAmount) {
                    this.totalAmount = totalAmount;
                }

                public int getLeftAmount() {
                    return leftAmount;
                }

                public void setLeftAmount(int leftAmount) {
                    this.leftAmount = leftAmount;
                }

                public int getTotalBonus() {
                    return totalBonus;
                }

                public void setTotalBonus(int totalBonus) {
                    this.totalBonus = totalBonus;
                }

                public int getLeftBonus() {
                    return leftBonus;
                }

                public void setLeftBonus(int leftBonus) {
                    this.leftBonus = leftBonus;
                }

                public int getMailMinimum() {
                    return mailMinimum;
                }

                public void setMailMinimum(int mailMinimum) {
                    this.mailMinimum = mailMinimum;
                }

                public int getBeginTotalAmount() {
                    return beginTotalAmount;
                }

                public void setBeginTotalAmount(int beginTotalAmount) {
                    this.beginTotalAmount = beginTotalAmount;
                }

                public int getEndTotalAmount() {
                    return endTotalAmount;
                }

                public void setEndTotalAmount(int endTotalAmount) {
                    this.endTotalAmount = endTotalAmount;
                }

                public int getBeginLeftAmount() {
                    return beginLeftAmount;
                }

                public void setBeginLeftAmount(int beginLeftAmount) {
                    this.beginLeftAmount = beginLeftAmount;
                }

                public int getEndLeftAmount() {
                    return endLeftAmount;
                }

                public void setEndLeftAmount(int endLeftAmount) {
                    this.endLeftAmount = endLeftAmount;
                }

                public int getBeginTotalBonus() {
                    return beginTotalBonus;
                }

                public void setBeginTotalBonus(int beginTotalBonus) {
                    this.beginTotalBonus = beginTotalBonus;
                }

                public int getEndTotalBonus() {
                    return endTotalBonus;
                }

                public void setEndTotalBonus(int endTotalBonus) {
                    this.endTotalBonus = endTotalBonus;
                }

                public int getBeginLeftBonus() {
                    return beginLeftBonus;
                }

                public void setBeginLeftBonus(int beginLeftBonus) {
                    this.beginLeftBonus = beginLeftBonus;
                }

                public int getEndLeftBonus() {
                    return endLeftBonus;
                }

                public void setEndLeftBonus(int endLeftBonus) {
                    this.endLeftBonus = endLeftBonus;
                }

                public int getBeginMailMinimum() {
                    return beginMailMinimum;
                }

                public void setBeginMailMinimum(int beginMailMinimum) {
                    this.beginMailMinimum = beginMailMinimum;
                }

                public int getEndMailMinimum() {
                    return endMailMinimum;
                }

                public void setEndMailMinimum(int endMailMinimum) {
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
                    dest.writeInt(totalAmount);
                    dest.writeInt(leftAmount);
                    dest.writeInt(totalBonus);
                    dest.writeInt(leftBonus);
                    dest.writeInt(mailMinimum);
                    dest.writeInt(beginTotalAmount);
                    dest.writeInt(endTotalAmount);
                    dest.writeInt(beginLeftAmount);
                    dest.writeInt(endLeftAmount);
                    dest.writeInt(beginTotalBonus);
                    dest.writeInt(endTotalBonus);
                    dest.writeInt(beginLeftBonus);
                    dest.writeInt(endLeftBonus);
                    dest.writeInt(beginMailMinimum);
                    dest.writeInt(endMailMinimum);
                }
            }

            public static class InvoiceTypeBean implements Parcelable{
                /**
                 * id : bac478bb52e6440fa745e15d53e42282
                 * isNewRecord : false
                 * name : 餐饮费
                 * categorySort : 0
                 * frequentSort : 0
                 */

                private String id;
                private boolean isNewRecord;
                private String name;
                private int categorySort;
                private int frequentSort;

                protected InvoiceTypeBean(Parcel in) {
                    id = in.readString();
                    isNewRecord = in.readByte() != 0;
                    name = in.readString();
                    categorySort = in.readInt();
                    frequentSort = in.readInt();
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

                public int getCategorySort() {
                    return categorySort;
                }

                public void setCategorySort(int categorySort) {
                    this.categorySort = categorySort;
                }

                public int getFrequentSort() {
                    return frequentSort;
                }

                public void setFrequentSort(int frequentSort) {
                    this.frequentSort = frequentSort;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(id);
                    dest.writeByte((byte) (isNewRecord ? 1 : 0));
                    dest.writeString(name);
                    dest.writeInt(categorySort);
                    dest.writeInt(frequentSort);
                }
            }
        }
    }
}
