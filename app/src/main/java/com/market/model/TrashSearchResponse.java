package com.market.model;

import java.util.List;

/**
 * author: ft
 * created on: 2022/8/25 15:48
 * description:
 */
public class TrashSearchResponse {

    private int code;
    private String msg;
    private List<NewslistDTO> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewslistDTO> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewslistDTO> newslist) {
        this.newslist = newslist;
    }

    public static class NewslistDTO {
        private String name;
        private int type;
        private int aipre;
        private String explain;
        private String contain;
        private String tip;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getAipre() {
            return aipre;
        }

        public void setAipre(int aipre) {
            this.aipre = aipre;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public String getContain() {
            return contain;
        }

        public void setContain(String contain) {
            this.contain = contain;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
