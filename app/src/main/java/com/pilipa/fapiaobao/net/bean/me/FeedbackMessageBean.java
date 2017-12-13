package com.pilipa.fapiaobao.net.bean.me;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by edz on 2017/12/4.
 */

public class FeedbackMessageBean implements Parcelable {


    private int status;
    private String msg;
    private DataBean data;
    public FeedbackMessageBean(){}

    protected FeedbackMessageBean(Parcel in) {
        status = in.readInt();
        msg = in.readString();
    }

    public static final Creator<FeedbackMessageBean> CREATOR = new Creator<FeedbackMessageBean>() {
        @Override
        public FeedbackMessageBean createFromParcel(Parcel in) {
            return new FeedbackMessageBean(in);
        }

        @Override
        public FeedbackMessageBean[] newArray(int size) {
            return new FeedbackMessageBean[size];
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
    }

    public static class DataBean implements Parcelable{


        private int pageNo;
        private int pageSize;
        private int count;
        private int totalPage;
        private String html;
        private int firstResult;
        private int maxResults;
        private List<ListBean> list;
        public DataBean(){}

        protected DataBean(Parcel in) {
            pageNo = in.readInt();
            pageSize = in.readInt();
            count = in.readInt();
            totalPage = in.readInt();
            html = in.readString();
            firstResult = in.readInt();
            maxResults = in.readInt();
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

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(pageNo);
            dest.writeInt(pageSize);
            dest.writeInt(count);
            dest.writeInt(totalPage);
            dest.writeString(html);
            dest.writeInt(firstResult);
            dest.writeInt(maxResults);
        }

        public static class ListBean {
            public String getHighlightString() {
                return highlightString;
            }

            public void setHighlightString(String highlightString) {
                this.highlightString = highlightString;
            }

            /**
             * customerId : 155a7388d25e45ee8a414a9382f3b196
             * suggestionList : [{"suggestionId":"f7d4b41075a745e5b0bce1ddfbe896e0","type":"1","nickname":"","avatar":"https://www.youpiao8.cn/fapiaobao/upload/155a7388d25e45ee8a414a9382f3b196/head.jpg","message":"你真是个天才","createTime":"2017-12-06 12:51"}]
             */
            private String highlightString;
            private String customerId;
            private List<SuggestionListBean> suggestionList;

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public List<SuggestionListBean> getSuggestionList() {
                return suggestionList;
            }

            public void setSuggestionList(List<SuggestionListBean> suggestionList) {
                this.suggestionList = suggestionList;
            }

            public static class SuggestionListBean implements Parcelable{
                /**
                 * suggestionId : f7d4b41075a745e5b0bce1ddfbe896e0
                 * type : 1
                 * nickname :
                 * avatar : https://www.youpiao8.cn/fapiaobao/upload/155a7388d25e45ee8a414a9382f3b196/head.jpg
                 * message : 你真是个天才
                 * createTime : 2017-12-06 12:51
                 */

                private String suggestionId;
                private String type;
                private String nickname;
                private String avatar;
                private String message;
                private String createTime;
                public SuggestionListBean(){}
                protected SuggestionListBean(Parcel in) {
                    suggestionId = in.readString();
                    type = in.readString();
                    nickname = in.readString();
                    avatar = in.readString();
                    message = in.readString();
                    createTime = in.readString();
                }

                public static final Creator<SuggestionListBean> CREATOR = new Creator<SuggestionListBean>() {
                    @Override
                    public SuggestionListBean createFromParcel(Parcel in) {
                        return new SuggestionListBean(in);
                    }

                    @Override
                    public SuggestionListBean[] newArray(int size) {
                        return new SuggestionListBean[size];
                    }
                };

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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeString(suggestionId);
                    dest.writeString(type);
                    dest.writeString(nickname);
                    dest.writeString(avatar);
                    dest.writeString(message);
                    dest.writeString(createTime);
                }
            }
        }
    }
}
