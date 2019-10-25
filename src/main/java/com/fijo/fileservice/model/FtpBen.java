package com.fijo.fileservice.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "ftp")
public class FtpBen {

    private String ftpHost;

    private Integer ftpPort;

    private String ftpUserName;

    private String ftpPassword;

}
