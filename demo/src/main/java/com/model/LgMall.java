package com.model;

public class LgMall {
    private Integer mallid;

    private String name;

    private String imageurl;

    private Integer requiredintegral;

    private String time;

    private Integer mallorder;

    public Integer getMallid() {
        return mallid;
    }

    public void setMallid(Integer mallid) {
        this.mallid = mallid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl == null ? null : imageurl.trim();
    }

    public Integer getRequiredintegral() {
        return requiredintegral;
    }

    public void setRequiredintegral(Integer requiredintegral) {
        this.requiredintegral = requiredintegral;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time == null ? null : time.trim();
    }

    public Integer getMallorder() {
        return mallorder;
    }

    public void setMallorder(Integer mallorder) {
        this.mallorder = mallorder;
    }
}