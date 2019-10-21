package com.fishingtime.framework.common.ftp;

import cn.hutool.extra.ftp.FtpMode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-06-19 14:28
 **/
@Getter
@Setter
@Accessors(chain = true)
public class FtpConfig {
    /**
     * 类型，FTP和SFTP 默认 FTP
     */
    protected Type type = Type.FTP;

    /**
     * 主机名或ip
     */
    protected String host;

    /**
     * 端口 默认21
     */
    protected int port=0;

    /**
     * 用户名
     */
    protected String user;

    /**
     * 密码
     */
    protected String password;

    /**
     * 编码格式 ,详见 java.nio.charset.StandardCharsets
     */
    protected Charset charset = StandardCharsets.UTF_8;

    /**
     * type=ftp 下有效 主被动模式，默认被动
     */
    protected FtpMode ftpMode=FtpMode.Passive;

    enum Type{
        FTP,SFTP;
    }
}
