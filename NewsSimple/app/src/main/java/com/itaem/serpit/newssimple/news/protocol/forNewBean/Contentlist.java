package com.itaem.serpit.newssimple.news.protocol.forNewBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

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





