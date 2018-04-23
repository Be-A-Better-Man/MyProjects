package com.itaem.serpit.newssimple.news.protocol;


import com.itaem.serpit.newssimple.news.protocol.forNewBean.Imageurls;
import java.util.List;



public class NewsDetailBean {


        private int ret_code;
        private Pagebean pagebean;
        public void setRet_code(int ret_code) {
            this.ret_code = ret_code;
        }
        public int getRet_code() {
            return ret_code;
        }

        public void setPagebean(Pagebean pagebean) {
            this.pagebean = pagebean;
        }
        public Pagebean getPagebean() {
            return pagebean;
        }

    public class Pagebean {

        private int allPages;
        private List<Contentlist> contentlist;
        private int currentPage;
        private int allNum;
        private int maxResult;
        public void setAllPages(int allPages) {
            this.allPages = allPages;
        }
        public int getAllPages() {
            return allPages;
        }

        public void setContentlist(List<Contentlist> contentlist) {
            this.contentlist = contentlist;
        }
        public List<Contentlist> getContentlist() {
            return contentlist;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }
        public int getCurrentPage() {
            return currentPage;
        }

        public void setAllNum(int allNum) {
            this.allNum = allNum;
        }
        public int getAllNum() {
            return allNum;
        }

        public void setMaxResult(int maxResult) {
            this.maxResult = maxResult;
        }
        public int getMaxResult() {
            return maxResult;
        }

    }


    public class Contentlist {

        private String id;
        private boolean havePic;
        private String pubDate;
        private String title;
        private String channelName;
        private List<Imageurls> imageurls;
        private String desc;
        private String source;
        private String channelId;
        private String nid;
        private String link;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setHavePic(boolean havePic) {
            this.havePic = havePic;
        }
        public boolean getHavePic() {
            return havePic;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }
        public String getPubDate() {
            return pubDate;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }
        public String getChannelName() {
            return channelName;
        }

        public void setImageurls(List<Imageurls> imageurls) {
            this.imageurls = imageurls;
        }
        public List<Imageurls> getImageurls() {
            return imageurls;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
        public String getDesc() {
            return desc;
        }

        public void setSource(String source) {
            this.source = source;
        }
        public String getSource() {
            return source;
        }

        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }
        public String getChannelId() {
            return channelId;
        }

        public void setNid(String nid) {
            this.nid = nid;
        }
        public String getNid() {
            return nid;
        }

        public void setLink(String link) {
            this.link = link;
        }
        public String getLink() {
            return link;
        }

    }




}
