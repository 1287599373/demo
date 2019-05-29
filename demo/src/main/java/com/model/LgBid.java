package com.model;

import lombok.Data;

@Data
public class LgBid {
    private Integer bidid;

    private String goodsname;

    private String quantity;

    private String consignment;

    private String receivingplace;

    private String bidtime;

    private Integer bidstart;

    private Integer finishstart;

    private String time;

    private Integer userid;
}