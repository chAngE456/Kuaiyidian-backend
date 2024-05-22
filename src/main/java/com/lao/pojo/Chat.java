package com.lao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private int cid;
    private String bid;
    private String detail;
    private String sid;
    private String rid;
    private Date create_time;
}
