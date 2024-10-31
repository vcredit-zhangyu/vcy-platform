package com.vcredit.vzy.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceAppDownloadReq {
    private List<String> model;
    private String version;
}
