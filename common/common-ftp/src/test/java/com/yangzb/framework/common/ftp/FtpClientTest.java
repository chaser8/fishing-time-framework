package com.yangzb.framework.common.ftp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FtpClientTest {
    private AbstractFtpClient ftpClient;

    @Before
    public void setUp() throws Exception {
        FtpConfig ftp = new FtpConfig();
        ftp.setType(FtpConfig.Type.FTP)
                .setHost("192.168.10.152")
                .setPassword("1qaz2wsx")
                .setUser("ftpuser");
        ftpClient = new FtpClient(ftp);
    }

    @After
    public void tearDown() throws Exception {
        ftpClient.close();
    }

    @Test
    public void pwd() {
        String pwd = ftpClient.pwd();
        System.out.println(pwd);
    }
}