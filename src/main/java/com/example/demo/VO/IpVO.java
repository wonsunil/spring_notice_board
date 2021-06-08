package com.example.demo.VO;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class IpVO {
    public String ip;
    public String id;

    public IpVO(String ip, String id) {
        this.ip = ip;
        this.id = id;
    }

    public IpVO() {
        this.ip = "";
        this.id = "";
    }
}
