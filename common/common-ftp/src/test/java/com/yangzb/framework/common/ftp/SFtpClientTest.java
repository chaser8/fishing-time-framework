package com.yangzb.framework.common.ftp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SFtpClientTest {

    private AbstractFtpClient ftpClient;

    @Before
    public void setUp() throws Exception {
        FtpConfig ftp = new FtpConfig();
        ftp.setType(FtpConfig.Type.SFTP)
                .setHost("192.168.10.51")
                .setPassword("RRrr22@@")
                .setUser("root");
        ftpClient = new SFtpClient(ftp);
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