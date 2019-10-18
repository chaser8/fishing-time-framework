package com.yangzb.framework.common.ftp;

import cn.hutool.core.lang.Filter;
import cn.hutool.extra.ssh.Sftp;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @program: framework
 * @description:
 * @author: yzb
 * @create: 2019-06-20 09:52
 **/
public class SFtpClient extends AbstractFtpClient {

    public SFtpClient(FtpConfig ftpConfig){
        if(ftpConfig.getPort()==0){
            ftpConfig.setPort(22);
        }
        ftp = new Sftp(ftpConfig.getHost(), ftpConfig.getPort(), ftpConfig.getUser(), ftpConfig.getPassword(), ftpConfig.getCharset());
    }

    @Override
    public Sftp reconnectIfTimeout() {
        return ftp.reconnectIfTimeout();
    }

    public com.jcraft.jsch.ChannelSftp getClient() {
        return ftp.getClient();
    }

    @Override
    public String pwd() {
        return ftp.pwd();
    }

    public String home() {
        return ftp.home();
    }

    @Override
    public List<String> ls(String path) {
        return ftp.ls(path);
    }

    public List<String> lsDirs(String path) {
        return ftp.lsDirs(path);
    }

    public List<String> lsFiles(String path) {
        return ftp.lsFiles(path);
    }

    public List<String> ls(String path, Filter<com.jcraft.jsch.ChannelSftp.LsEntry> filter) {
        return ftp.ls(path, filter);
    }

    @Override
    public boolean mkdir(String dir) {
        return ftp.mkdir(dir);
    }

    @Override
    public boolean cd(String directory) {
        return ftp.cd(directory);
    }

    @Override
    public boolean delFile(String filePath) {
        return ftp.delFile(filePath);
    }

    @Override
    public boolean delDir(String dirPath) {
        return ftp.delDir(dirPath);
    }

    @Override
    public boolean upload(String path, File file) {
//        ftp.upload(srcFilePath, destFile);
        put(file.getAbsolutePath(),path);
        return true;
    }

    public Sftp put(String srcFilePath, String destPath) {
        reconnectIfTimeout();
        return ftp.put(srcFilePath, destPath);
    }

    public Sftp put(String srcFilePath, String destPath, Sftp.Mode mode) {
        reconnectIfTimeout();
        return ftp.put(srcFilePath, destPath, mode);
    }

    @Override
    public void download(String src, File destFile) {
        reconnectIfTimeout();
        ftp.download(src, destFile);
    }

    public Sftp get(String src, String dest) {
        reconnectIfTimeout();
        return ftp.get(src, dest);
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

    private Sftp ftp = null;
}