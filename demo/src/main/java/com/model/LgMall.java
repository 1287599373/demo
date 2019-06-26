package com.model;

public class LgMall {
    private Integer mallid;

    private String name;

    private String imageurl;

    private Integer requiredintegral;

    private String time;

    private Integer mallorder;

    private String specifications;

    private Double price;

    private Integer stockbalance;
    
    //查询条件
    private String startTime;
    
    private String endTime;
    
    
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

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

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications == null ? null : specifications.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStockbalance() {
        return stockbalance;
    }

    public void setStockbalance(Integer stockbalance) {
        this.stockbalance = stockbalance;
    }
}