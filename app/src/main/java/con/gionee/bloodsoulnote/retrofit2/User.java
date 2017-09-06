package con.gionee.bloodsoulnote.retrofit2;

import java.util.List;

/**
 * Created by root on 17-8-8.
 */
public class User {


    /**
     * data : {"list":[{"location":3,"tips":"抢占第一个评论"},{"location":2,"tips":"去留下你的脚印"}]}
     * msg : success
     * success : true
     */
    private DataEntity data;
    private String msg;
    private boolean success;

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public class DataEntity {
        /**
         * list : [{"location":3,"tips":"抢占第一个评论"},{"location":2,"tips":"去留下你的脚印"}]
         */
        private List<ListEntity> list;

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public class ListEntity {
            /**
             * location : 3
             * tips : 抢占第一个评论
             */
            private int location;
            private String tips;

            public void setLocation(int location) {
                this.location = location;
            }

            public void setTips(String tips) {
                this.tips = tips;
            }

            public int getLocation() {
                return location;
            }

            public String getTips() {
                return tips;
            }
        }
    }
}
