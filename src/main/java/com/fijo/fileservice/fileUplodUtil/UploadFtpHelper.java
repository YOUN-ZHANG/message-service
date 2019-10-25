package com.fijo.fileservice.fileUplodUtil;

import com.fijo.fileservice.model.FileParameter;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.net.SocketException;
import java.sql.SQLTransactionRollbackException;

/**
 * @ Author     ：zhangbo.
 * @ Date       ：Created in 18:42 2019/9/18
 * @ Description：
 * @ Modified By：
 * @Version:
 */

@Slf4j
public class UploadFtpHelper {

    //设置私有不能实例化
    private UploadFtpHelper() {

    }

    /**
     * 测试例子
     * **/
    public static void main(String[] args) throws Exception {
        String hostname = "172.16.100.199";
        int port = 21;
        String username = "fijoftp";
        String password = "123456";
        String workingPath = "C:\\Users\\ASUS\\Pictures\\Saved Pictures\\20180224200246.jpg";
        String str="";
        InputStream fileInputStream = new FileInputStream(new File(str));
        String saveName = "QQ截图20190926091928.png";
        System.out.println(UploadFtpHelper.upload( hostname, port, username, password, workingPath, fileInputStream, saveName));
    }

    public static void main1(String[] args) {
        String ftpHost="172.16.100.199";
       String ftpUserName="fijoftp";
       String ftpPassword="123456";
       int ftpPort=21;
       String ftpPath="XZ0002/test4/SYSUSER0002/png/";
       String downloadFile="C:\\Users\\ASUS\\Pictures\\Saved Pictures";
       String downloadFileName="timg.jpg";
        downloadFile(ftpHost, ftpUserName, ftpPassword, ftpPort,ftpPath,downloadFile, downloadFileName);
    }

    /**
     * 下载文件
     *
     * @param ftpHost ftp服务器地址
     * @param ftpUserName anonymous匿名用户登录，不需要密码。administrator指定用户登录
     * @param ftpPassword 指定用户密码
     * @param ftpPort ftp服务员器端口号
     * @param ftpPath  ftp文件存放物理路径
     * @param fileName 文件名
     */
    public static void downloadFile(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort, String ftpPath, String localPath, String fileName) {

        FTPClient ftpClient = null;


        try {

            ftpClient =  getFTPClient(ftpHost, ftpUserName, ftpPassword, ftpPort);
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE );
            ftpClient.setControlEncoding("UTF-8"); // 中文支持

            ftpClient.enterLocalPassiveMode();
            ftpClient.changeWorkingDirectory(ftpPath);

            File localFile = new File(localPath + File.separatorChar + fileName);
            OutputStream os = new FileOutputStream(localFile);

            ftpClient.retrieveFile(fileName, os);
            os.close();
            ftpClient.logout();

        } catch (FileNotFoundException e) {
            log.error("没有找到" + ftpPath + "文件");
            e.printStackTrace();
        } catch (SocketException e) {
            log.error("连接FTP失败.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件读取错误。");
            e.printStackTrace();
        }
    }
    /**
     * 获取FTPClient对象
     *
     * @param ftpHost
     *            FTP主机服务器
     * @param ftpPassword
     *            FTP 登录密码
     * @param ftpUserName
     *            FTP登录用户名
     * @param ftpPort
     *            FTP端口 默认为21
     * @return
     */
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName, String ftpPassword, int ftpPort) {

        FTPClient ftpClient = null;

        try {
            //创建一个ftp客户端
            ftpClient = new FTPClient();
            // 连接FTP服务器
            ftpClient.connect(ftpHost, ftpPort);
            // 登陆FTP服务器
            ftpClient.login(ftpUserName, ftpPassword);

            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info("未连接到FTP，用户名或密码错误。");
                ftpClient.disconnect();
            } else {
                log.info("FTP连接成功。");
            }
        } catch (SocketException e) {
            e.printStackTrace();
            log.info("FTP的IP地址可能错误，请正确配置。");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP的端口错误,请正确配置。");
        }
        return ftpClient;
    }


    /**
     * 上传
     *
     * @param hostname ip或域名地址
     * @param port  端口
     * @param username 用户名
     * @param password 密码
     * @param workingPath 服务器的工作目录
     * @param inputStream 要上传文件的输入流
     * @param saveName    设置上传之后的文件名
     * @return
     */
    public static boolean upload(String hostname, int port, String username, String password, String workingPath, InputStream inputStream, String saveName) {
        boolean flag = false;
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding("UTF-8");

        //1 测试连接
        if (connect(ftpClient, hostname, port, username, password)) {
            try {

                CreateDirecroty(workingPath,ftpClient);

                // 3 检查是否上传成功
                if (storeFile(ftpClient, saveName, inputStream)) {
                    flag = true;
                    disconnect(ftpClient);
                }
//                //2 检查工作目录是否存在
//                if (ftpClient.changeWorkingDirectory(workingPath)) {
//                    // 3 检查是否上传成功
//                    if (storeFile(ftpClient, saveName, inputStream)) {
//                        flag = true;
//                        disconnect(ftpClient);
//                    }
//                }
//                else {
//                    boolean a = ftpClient.makeDirectory(workingPath);
//                    // 3 检查是否上传成功
//                    if (storeFile(ftpClient, saveName, inputStream)) {
//                        flag = true;
//                        disconnect(ftpClient);
//                    }
//                }
            } catch (IOException e) {
                log.error("工作目录不存在");
                e.printStackTrace();
                disconnect(ftpClient);
            }
        }
        return flag;
    }

    //改变目录路径
    public static boolean changeWorkingDirectory(String directory,FTPClient ftpClient) {
        boolean flag = true;
        try {
            flag = ftpClient.changeWorkingDirectory(directory);
            if (flag) {
                log.debug("进入文件夹" + directory + " 成功！");

            } else {
                log.debug("进入文件夹" + directory + " 失败！");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return flag;
    }


    //创建目录
    public static boolean makeDirectory(String dir,FTPClient ftpClient) {
        boolean flag = true;
        try {
            flag = ftpClient.makeDirectory(dir);
            if (flag) {
                log.debug("创建文件夹" + dir + " 成功！");

            } else {
                log.debug("创建文件夹" + dir + " 失败！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    //判断ftp服务器文件是否存在
    public static boolean existFile(String path,FTPClient ftpClient) throws IOException {
        boolean flag = false;
        FTPFile[] ftpFileArr = ftpClient.listFiles(path);
        if (ftpFileArr.length > 0) {
            flag = true;
        }
        return flag;
    }



    //创建多层目录文件，如果有ftp服务器已存在该文件，则不创建，如果无，则创建
    public static boolean CreateDirecroty(String remote,FTPClient ftpClient) throws IOException {
        boolean success = true;
        String directory = remote + "/";
        // String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
        // 如果远程目录不存在，则递归创建远程服务器目录
        if (!directory.equalsIgnoreCase("/") && !changeWorkingDirectory(new String(directory),ftpClient)) {
            int start = 0;
            int end = 0;
            if (directory.startsWith("/")) {
                start = 1;
            } else {
                start = 0;
            }
            end = directory.indexOf("/", start);
            String path = "";
            String paths = "";
            while (true) {

                String subDirectory = new String(remote.substring(start, end).getBytes("GBK"), "iso-8859-1");
                path = path + "/" + subDirectory;
                if (!existFile(path,ftpClient)) {
                    if (makeDirectory(subDirectory,ftpClient)) {
                        changeWorkingDirectory(subDirectory,ftpClient);
                    } else {
                        log.debug("创建目录[" + subDirectory + "]失败");
                        changeWorkingDirectory(subDirectory,ftpClient);
                    }
                } else {
                    changeWorkingDirectory(subDirectory,ftpClient);
                }

                paths = paths + "/" + subDirectory;
                start = end + 1;
                end = directory.indexOf("/", start);
                // 检查所有目录是否创建完毕
                if (end <= start) {
                    break;
                }
            }
        }
        return success;
    }



    /**
     * 断开连接
     *
     * @param ftpClient
     * @throws Exception
     */
    public static void disconnect(FTPClient ftpClient) {
        if (ftpClient.isConnected()) {
            try {
                ftpClient.disconnect();
                log.error("已关闭连接");
            } catch (IOException e) {
                log.error("没有关闭连接");
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试是否能连接
     *
     * @param ftpClient
     * @param hostname  ip或域名地址
     * @param port      端口
     * @param username  用户名
     * @param password  密码
     * @return 返回真则能连接
     */
    public static boolean connect(FTPClient ftpClient, String hostname, int port, String username, String password) {
        boolean flag = false;
        try {
            //ftp初始化的一些参数
            ftpClient.connect(hostname, port);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            ftpClient.setControlEncoding("UTF-8");
            if (ftpClient.login(username, password)) {
                log.info("连接ftp成功");
                flag = true;
            } else {
                log.error("连接ftp失败，可能用户名或密码错误");
                try {
                    disconnect(ftpClient);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            log.error("连接失败，可能ip或端口错误");
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 上传文件
     *
     * @param ftpClient
     * @param saveName        全路径。如/home/public/a.txt
     * @param fileInputStream 要上传的文件流
     * @return
     */
    public static boolean storeFile(FTPClient ftpClient, String saveName, InputStream fileInputStream) {
        boolean flag = false;
        try {
            //修改成二进制上传
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            if (ftpClient.storeFile(saveName, fileInputStream)) {
                flag = true;
                log.error("上传成功");
                disconnect(ftpClient);
            }
        } catch (IOException e) {
            log.error("上传失败");
            disconnect(ftpClient);
            e.printStackTrace();
        }
        return flag;
    }

}
