package com.vcredit.vzy.common.dto;

import lombok.Data;

@Data
public class AppDownloadDTO {
    private String id;
    private String type;
    private String url;
    private String version;
}
