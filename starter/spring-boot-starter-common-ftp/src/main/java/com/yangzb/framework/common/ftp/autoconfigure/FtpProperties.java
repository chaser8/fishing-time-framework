package com.yangzb.framework.common.ftp.autoconfigure;

import com.yangzb.framework.common.ftp.FtpConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-19 14:28
 **/
@ConfigurationProperties(prefix = "ftp")
public class FtpProperties extends FtpConfig {
}