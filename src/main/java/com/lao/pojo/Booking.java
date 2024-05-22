package com.lao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private String bid;
    private String bName;
    private Date timeInfo;
    private String placeInfo;
    private String seekerId;
    private String helperId;
    private String detail;
    private double reward;
    private int state;
    private double star;
    private Date create_time;
}
