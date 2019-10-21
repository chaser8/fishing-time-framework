package com.fishingtime.framework.common.ftp.autoconfigure;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.AbstractFtp;
import com.fishingtime.framework.common.ftp.AbstractFtpClient;
import com.fishingtime.framework.common.ftp.FtpClient;
import com.fishingtime.framework.common.ftp.SFtpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-06-19 15:13
 **/
@Configuration
@EnableConfigurationProperties(value = {FtpProperties.class})
@ConditionalOnMissingBean(AbstractFtp.class)
public class FtpAutoConfiguration {
    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(value = "ftp.type", matchIfMissing = false, havingValue = "ftp")
    public AbstractFtpClient ftp(@Autowired FtpProperties ftpProperties) {
        if(ftpProperties.getPort()==0){
            ftpProperties.setPort(21);
        }
        if(StrUtil.isBlank(ftpProperties.getUser())){
            ftpProperties.setUser("anonymous");
            ftpProperties.setPassword("");
        }
        AbstractFtpClient ftp = new FtpClient(ftpProperties);
        return ftp;
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty(value = "ftp.type", matchIfMissing = false, havingValue = "sftp")
    public AbstractFtpClient sftp(@Autowired FtpProperties ftpProperties) {
        if(ftpProperties.getPort()==0){
            ftpProperties.setPort(22);
        }
        AbstractFtpClient ftp = new SFtpClient(ftpProperties);
        return ftp;
    }
}
