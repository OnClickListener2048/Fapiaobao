package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2018/2/9.
 */

public class Invoice_Verify {

    /**
     * data : {"开票日期":"20180201","销售方名称":"天津华润万家生活超市有限公司","购买方名称":"天津腾洲物业管理有限公司","销方纳税人识别号":"91120116754813198A","购方纳税人识别号":"91120105MA05MTB86P","价税合计":4300,"税额合计":0,"校验码":"73828187342731888966","发票号码":"13063856","交税内容":[{"规定型号":"","税额":0,"单位":"张","数量":19,"税率":"0%","单价":200,"名称":"*预付卡销售*预付卡销售","金额":3800},{"规定型号":"","税额":0,"单位":"张","数量":1,"税率":"0%","单价":500,"名称":"*预付卡销售*预付卡销售","金额":500}],"销售方开户行及账号":"中国工商银行天津市陈塘庄支行0302060409300235718","销售方地址及电话":"天津市东丽开发区二经路1号 84826492","金额合计":4300,"发票类型":"增值税普通发票","机器编码":"661719275433","查验次数":16,"发票代码":"1200172320"}
     * status : 0
     */
    private String msg;
    private DataBean data;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * 开票日期 : 20180201
         * 销售方名称 : 天津华润万家生活超市有限公司
         * 购买方名称 : 天津腾洲物业管理有限公司
         * 销方纳税人识别号 : 91120116754813198A
         * 购方纳税人识别号 : 91120105MA05MTB86P
         * 价税合计 : 4300
         * 税额合计 : 0
         * 校验码 : 73828187342731888966
         * 发票号码 : 13063856
         * 交税内容 : [{"规定型号":"","税额":0,"单位":"张","数量":19,"税率":"0%","单价":200,"名称":"*预付卡销售*预付卡销售","金额":3800},{"规定型号":"","税额":0,"单位":"张","数量":1,"税率":"0%","单价":500,"名称":"*预付卡销售*预付卡销售","金额":500}]
         * 销售方开户行及账号 : 中国工商银行天津市陈塘庄支行0302060409300235718
         * 销售方地址及电话 : 天津市东丽开发区二经路1号 84826492
         * 金额合计 : 4300
         * 发票类型 : 增值税普通发票
         * 机器编码 : 661719275433
         * 查验次数 : 16
         * 发票代码 : 1200172320
         */
        private String errorId;
        private String errorMsg;
        private String 开票日期;
        private String 销售方名称;
        private String 购买方名称;
        private String 销方纳税人识别号;
        private String 购方纳税人识别号;
        private double 价税合计;
        private double 税额合计;
        private String 校验码;
        private String 发票号码;
        private String 销售方开户行及账号;
        private String 销售方地址及电话;
        private double 金额合计;
        private String 发票类型;
        private String 机器编码;
        private int 查验次数;
        private String 发票代码;
        private List<交税内容Bean> 交税内容;

        public String getErrorId() {
            return errorId;
        }

        public void setErrorId(String errorId) {
            this.errorId = errorId;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String get开票日期() {
            return 开票日期;
        }

        public void set开票日期(String 开票日期) {
            this.开票日期 = 开票日期;
        }

        public String get销售方名称() {
            return 销售方名称;
        }

        public void set销售方名称(String 销售方名称) {
            this.销售方名称 = 销售方名称;
        }

        public String get购买方名称() {
            return 购买方名称;
        }

        public void set购买方名称(String 购买方名称) {
            this.购买方名称 = 购买方名称;
        }

        public String get销方纳税人识别号() {
            return 销方纳税人识别号;
        }

        public void set销方纳税人识别号(String 销方纳税人识别号) {
            this.销方纳税人识别号 = 销方纳税人识别号;
        }

        public String get购方纳税人识别号() {
            return 购方纳税人识别号;
        }

        public void set购方纳税人识别号(String 购方纳税人识别号) {
            this.购方纳税人识别号 = 购方纳税人识别号;
        }

        public double get价税合计() {
            return 价税合计;
        }

        public void set价税合计(int 价税合计) {
            this.价税合计 = 价税合计;
        }

        public double get税额合计() {
            return 税额合计;
        }

        public void set税额合计(int 税额合计) {
            this.税额合计 = 税额合计;
        }

        public String get校验码() {
            return 校验码;
        }

        public void set校验码(String 校验码) {
            this.校验码 = 校验码;
        }

        public String get发票号码() {
            return 发票号码;
        }

        public void set发票号码(String 发票号码) {
            this.发票号码 = 发票号码;
        }

        public String get销售方开户行及账号() {
            return 销售方开户行及账号;
        }

        public void set销售方开户行及账号(String 销售方开户行及账号) {
            this.销售方开户行及账号 = 销售方开户行及账号;
        }

        public String get销售方地址及电话() {
            return 销售方地址及电话;
        }

        public void set销售方地址及电话(String 销售方地址及电话) {
            this.销售方地址及电话 = 销售方地址及电话;
        }

        public double get金额合计() {
            return 金额合计;
        }

        public void set金额合计(int 金额合计) {
            this.金额合计 = 金额合计;
        }

        public String get发票类型() {
            return 发票类型;
        }

        public void set发票类型(String 发票类型) {
            this.发票类型 = 发票类型;
        }

        public String get机器编码() {
            return 机器编码;
        }

        public void set机器编码(String 机器编码) {
            this.机器编码 = 机器编码;
        }

        public int get查验次数() {
            return 查验次数;
        }

        public void set查验次数(int 查验次数) {
            this.查验次数 = 查验次数;
        }

        public String get发票代码() {
            return 发票代码;
        }

        public void set发票代码(String 发票代码) {
            this.发票代码 = 发票代码;
        }

        public List<交税内容Bean> get交税内容() {
            return 交税内容;
        }

        public void set交税内容(List<交税内容Bean> 交税内容) {
            this.交税内容 = 交税内容;
        }

        public static class 交税内容Bean {
            /**
             * 规定型号 :
             * 税额 : 0
             * 单位 : 张
             * 数量 : 19
             * 税率 : 0%
             * 单价 : 200
             * 名称 : *预付卡销售*预付卡销售
             * 金额 : 3800
             */

            private String 规定型号;
            private double 税额;
            private String 单位;
            private int 数量;
            private String 税率;
            private double 单价;
            private String 名称;
            private double 金额;

            public String get规定型号() {
                return 规定型号;
            }

            public void set规定型号(String 规定型号) {
                this.规定型号 = 规定型号;
            }

            public double get税额() {
                return 税额;
            }

            public void set税额(int 税额) {
                this.税额 = 税额;
            }

            public String get单位() {
                return 单位;
            }

            public void set单位(String 单位) {
                this.单位 = 单位;
            }

            public int get数量() {
                return 数量;
            }

            public void set数量(int 数量) {
                this.数量 = 数量;
            }

            public String get税率() {
                return 税率;
            }

            public void set税率(String 税率) {
                this.税率 = 税率;
            }

            public double get单价() {
                return 单价;
            }

            public void set单价(int 单价) {
                this.单价 = 单价;
            }

            public String get名称() {
                return 名称;
            }

            public void set名称(String 名称) {
                this.名称 = 名称;
            }

            public double get金额() {
                return 金额;
            }

            public void set金额(int 金额) {
                this.金额 = 金额;
            }
        }
    }
}
