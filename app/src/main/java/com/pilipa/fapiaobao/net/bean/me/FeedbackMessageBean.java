package com.pilipa.fapiaobao.net.bean.me;

import java.util.List;

/**
 * Created by edz on 2017/12/4.
 */

public class FeedbackMessageBean {

    /**
     * status : 200
     * msg : OK
     * data : {"pageNo":1,"pageSize":1,"count":0,"totalPage":3,"list":[{"suggestionList":[{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"你好","createTime":"2017-12-01 16:20"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"赶紧回复","createTime":"2017-12-04 10:06"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"再次提问","createTime":"2017-12-04 10:09"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"0","message":"你是谁？","createTime":"2017-12-04 10:11"}]}],"firstResult":0,"maxResults":1,"html":"<ul>\n<li class=\"disabled\"><a href=\"javascript:\">« 上一页<\/a><\/li>\n<li class=\"active\"><a href=\"javascript:\">1<\/a><\/li>\n<li class=\"disabled\"><a href=\"javascript:\">下一页 »<\/a><\/li>\n<li class=\"disabled controls\"><a href=\"javascript:\">当前 <input type=\"text\" value=\"1\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,1,'');\" onclick=\"this.select();\"/> / <input type=\"text\" value=\"1\" onkeypress=\"var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');\" onclick=\"this.select();\"/> 条，共 0 条<\/a><\/li>\n<\/ul>\n<div style=\"clear:both;\"><\/div>"}
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
        /**
         * pageNo : 1
         * pageSize : 1
         * count : 0
         * totalPage : 3
         * list : [{"suggestionList":[{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"你好","createTime":"2017-12-01 16:20"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"赶紧回复","createTime":"2017-12-04 10:06"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"1","nickname":"P_13114917491","avatar":"https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg","message":"再次提问","createTime":"2017-12-04 10:09"},{"suggestionId":"540f240eafb54be4b607e0ee54bc4dd1","type":"0","message":"你是谁？","createTime":"2017-12-04 10:11"}]}]
         * firstResult : 0
         * maxResults : 1
         * html : <ul>
         <li class="disabled"><a href="javascript:">« 上一页</a></li>
         <li class="active"><a href="javascript:">1</a></li>
         <li class="disabled"><a href="javascript:">下一页 »</a></li>
         <li class="disabled controls"><a href="javascript:">当前 <input type="text" value="1" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(this.value,1,'');" onclick="this.select();"/> / <input type="text" value="1" onkeypress="var e=window.event||event;var c=e.keyCode||e.which;if(c==13)page(1,this.value,'');" onclick="this.select();"/> 条，共 0 条</a></li>
         </ul>
         <div style="clear:both;"></div>
         */

        private int pageNo;
        private int pageSize;
        private int count;
        private int totalPage;
        private int firstResult;
        private int maxResults;
        private String html;
        private List<ListBean> list;

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getFirstResult() {
            return firstResult;
        }

        public void setFirstResult(int firstResult) {
            this.firstResult = firstResult;
        }

        public int getMaxResults() {
            return maxResults;
        }

        public void setMaxResults(int maxResults) {
            this.maxResults = maxResults;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private List<SuggestionListBean> suggestionList;

            public List<SuggestionListBean> getSuggestionList() {
                return suggestionList;
            }

            public void setSuggestionList(List<SuggestionListBean> suggestionList) {
                this.suggestionList = suggestionList;
            }

            public static class SuggestionListBean {
                /**
                 * suggestionId : 540f240eafb54be4b607e0ee54bc4dd1
                 * type : 1
                 * nickname : P_13114917491
                 * avatar : https://www.youpiao8.cn/fapiaobao/upload/355d1bbab2f34aa3bbe95ea2be75b059/3a26940d36af43dfaed6dda610cd445f.jpg
                 * message : 你好
                 * createTime : 2017-12-01 16:20
                 */

                private String suggestionId;
                private String type;
                private String nickname;
                private String avatar;
                private String message;
                private String createTime;

                public String getSuggestionId() {
                    return suggestionId;
                }

                public void setSuggestionId(String suggestionId) {
                    this.suggestionId = suggestionId;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public String getAvatar() {
                    return avatar;
                }

                public void setAvatar(String avatar) {
                    this.avatar = avatar;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }
            }
        }
    }
}
