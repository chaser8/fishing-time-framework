package com.fishingtime.framework.common.ftp;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.Ftp;
import cn.hutool.extra.ftp.FtpMode;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


/**
 * @program: framework
 * @description:
 * @author:
 * @create: 2019-06-20 09:52
 **/
public class FtpClient extends AbstractFtpClient {
    public FtpClient(FtpConfig ftpConfig) {
        if (ftpConfig.getPort() == 0) {
            ftpConfig.setPort(21);
        }
        if (StrUtil.isBlank(ftpConfig.getUser())) {
            ftpConfig.setUser("anonymous");
            ftpConfig.setPassword("");
        }
        ftp = new Ftp(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUser(), ftpConfig.getPassword(), ftpConfig.getCharset(), ftpConfig.getFtpMode());
        ftp.setBackToPwd(true);
    }

    public Ftp setMode(FtpMode mode) {
        return ftp.setMode(mode);
    }

    @Override
    public Ftp reconnectIfTimeout() {
        return ftp.reconnectIfTimeout();
    }

    @Override
    public boolean cd(String directory) {
        return ftp.cd(directory);
    }

    @Override
    public String pwd() {
        return ftp.pwd();
    }

    @Override
    public List<String> ls(String path) {
        return ftp.ls(path);
    }

    public FTPFile[] lsFiles(String path) {
        return ftp.lsFiles(path);
    }

    @Override
    public boolean mkdir(String dir) {
        return ftp.mkdir(dir);
    }

    public boolean existFile(String path) {
        return ftp.existFile(path);
    }

    @Override
    public boolean delFile(String path) {
        return ftp.delFile(path);
    }

    @Override
    public boolean delDir(String dirPath) {
        return ftp.delDir(dirPath);
    }

    @Override
    public boolean upload(String path, File file) {
        reconnectIfTimeout();
        return ftp.upload(path, file);
    }

    public boolean upload(String path, String fileName, File file) {
        reconnectIfTimeout();
        return ftp.upload(path, fileName, file);
    }

    public boolean upload(String path, String fileName, InputStream fileStream) {
        reconnectIfTimeout();
        return ftp.upload(path, fileName, fileStream);
    }

    @Override
    public void download(String path, File outFile) {
        reconnectIfTimeout();
        boolean exist = ftp.exist(path);
        ftp.download(path, outFile);
    }

    public void download(String path, String fileName, File outFile) {
        reconnectIfTimeout();
        ftp.download(path, fileName, outFile);
    }

    public void download(String path, String fileName, OutputStream out) {
        reconnectIfTimeout();
        ftp.download(path, fileName, out);
    }

    public FTPClient getClient() {
        return ftp.getClient();
    }

    @Override
    public void close() throws IOException {
        ftp.close();
    }

    @Override
    public boolean toParent() {
        return ftp.toParent();
    }

    @Override
    public boolean exist(String path) {
        return ftp.exist(path);
    }

    @Override
    public void mkDirs(String dir) {
        ftp.mkDirs(dir);
    }

    private Ftp ftp = null;
}