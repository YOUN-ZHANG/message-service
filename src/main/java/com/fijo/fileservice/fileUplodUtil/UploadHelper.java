package com.fijo.fileservice.fileUplodUtil;

import ch.qos.logback.core.util.FileUtil;
import com.fijo.fileservice.model.FileParameter;
import com.fijo.fileservice.model.FileUpload;
import com.fijo.fileservice.model.FtpBen;
import com.fijo.fileservice.model.SysFileUpload;
import com.fijo.fileservice.service.FileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.SocketException;
import java.util.*;
@Slf4j
public class UploadHelper {
    @Autowired
    private static FileUploadService service;



//    public void getFtpImage (String workId,HttpServletResponse response,@RequestParam String filepath) throws InterruptedException {
//               //创建FTP连接
//            FTPClient ftp= null;
//            OutputStream outStream = null;
//            InputStream in = null;
//            try {
//            ftp= initFTP(ftp);
//             //下载文件
//             in = ftp.retrieveFileStream(filepath);
//             String picType = filepath.split("\\.")[1];
//             BufferedImage bufImg = null;
//            bufImg = ImageIO.read(in);
//             outStream = response.getOutputStream();
//             ImageIO.write(bufImg, picType, outStream);
//
//            } catch (IOException e) {
//            e.printStackTrace();
//            }finally {
//            if(in!=null) {
//            try {
//            in.close();
//            destroy(ftp);
//            } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            }
//            }
//            }
//            }

            //FTP初始化
//             public FTPClient initFTP(FTPClient ftp) throws IOException {
//
//                    InputStream in = UploadToFtpController.class.getResourceAsStream("/application.properties");
//                    Properties properties=new Properties();
//                    properties.load(in);
//                    String addr = properties.getProperty("ftp_url");
//                    int port=Integer.valueOf(properties.getProperty("ftp_port")).intValue();
//                    String username=properties.getProperty("ftp_username");
//                    String password=properties.getProperty("ftp_password");
//                    ftp = new FTPClient();
//                     ftp.connect(addr,port);
//                    ftp.login(username,password);
//                     ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
//                     ftp.setControlEncoding("GBK");
//                     ftp.setBufferSize(1024*1024*10); //设置缓冲区上传速度为10M，默认为1K
//                    ftp.setFileType(FTP.BINARY_FILE_TYPE);//设置上传方式位字节      
//                     ftp.enterLocalPassiveMode();//Switch to passive mode
//                     return ftp;
//                        }
//                    //关闭FTP
//                             public void destroy(FTPClient ftp) throws IOException {
//                     if(ftp != null){
//                     ftp.disconnect();
//                     ftp = null;
//                     }
//                     }


    /**
     * @author
     *
     * @create
     *
     *
     * @description 上传帮助类
     *
     */

        /**
         * @descrption 根据HttpServletRequest对象获取MultipartFile集合
         * @author
         * @create
         * @param request
         * @param maxLength
         *            文件最大限制
         * @param allowExtName
         *            不允许上传的文件扩展名
         * @return MultipartFile集合
         */
        public static MultipartFile getFileSet(HttpServletRequest request, long maxLength, String[] allowExtName) {
            MultipartHttpServletRequest multipartRequest = null;
            try {
                multipartRequest = (MultipartHttpServletRequest) request;
            } catch (Exception e) {
                  return null;
            }
           List< MultipartFile> files = multipartRequest.getFiles("files");//取files
                if (!validateFile(files.get(0), maxLength, allowExtName)) {
                  // files.remove(files.get(0));
                    if (files.size() == 0) {
                        return (MultipartFile) files;
                    }
                }
                System.err.println(files);
            return (MultipartFile) files;
        }

        /**
         * @descrption 保存文件
         * @author
         * @create
         * @param file
         *            MultipartFile对象
         * @param path
         * @throws Exception
         */
        public static  FileUpload uploadFile(MultipartFile file, FileUpload o, String path) throws Exception {

            String filename = file.getOriginalFilename();//拿到文件名
            //获取后缀
            String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            //生成唯一的名称 随机值+后缀
            String lastFileName = UUID.randomUUID().toString() + extName;
            //判断path的后缀
            if (!path.endsWith(File.separator)) {
                path = path + File.separator;
            }
            //截取文件去掉点 . 之后的后缀
            String getSignInfo = extName.substring(extName.lastIndexOf(".")+1);
         //   File temp = new File(path+"/"+o.getTenant()+"/"+o.getOrgCode()+"/"+o.getModular()); //新建一个文件 路径为path+租户编码+组织编码+模块名
            File temp = new File(path+"/xinzhuang/cw/ry/"+getSignInfo+"file/"); //新建一个文件 路径为path+租户编码+组织编码+模块名
            if (!temp.exists()) {//如果目录不存在
                temp.mkdirs();  //如果父文件夹不存在并且最后一级子文件夹不存在，它就自动新建所有路经里写的文件夹；
                                // 如果父文件夹存在，它就直接在已经存在的父文件夹下新建子文件夹
            }
            // 图片存储的全路径
            String fileFullPath = temp+"/"+lastFileName ;
           //复制file文件到 新建的fileFullPath目录
            FileCopyUtils.copy(file.getBytes(), new File(fileFullPath));

            FileUpload upload=new FileUpload();
            upload.setEnabled(1);
            upload.setRemoved(0);
            upload.setTenant(o.getTenant());//租户编码
            upload.setOrgCode(o.getOrgCode());//组织编码
            upload.setModular(o.getModular());//模块名
            upload.setFileName(filename);//原始文件名
            upload.setFilePath(fileFullPath);//文件保存路径
            upload.setFileNewName(lastFileName);//生成的文件名
          //  FileUpload fileUpload=service.resultUpload(upload);
            return null;
        }

    public static   FileParameter  getParameter(MultipartFile file,FileUpload o) throws Exception {
         FileParameter permission=new FileParameter();
         String filename = file.getOriginalFilename();//拿到文件名
        //获取后缀
        String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        //生成唯一的name
        String lastFileName = UUID.randomUUID().toString() + extName;
        //截取文件去掉点 . 之后的后缀
        String getSignInfo = extName.substring(extName.lastIndexOf(".")+1);
        permission.setFile(String.valueOf(file));
        permission.setFileName(filename);
        permission.setFileSuffix(getSignInfo);
        permission.setFileNewName(lastFileName);//生成的文件名
         //temporary

        permission.setWorkingPath("/"+o.getTenant()+"/"+o.getOrgCode()+"/"+o.getModular()+"/"+getSignInfo);
        return permission;
    }
        /**
         * @descrption 验证文件格式，这里主要验证后缀名
         * @author
         * @create
         * @param file
         *            MultipartFile对象
         * @param maxLength
         *            文件最大限制
         * @param allowExtName
         *            不允许上传的文件扩展名
         * @return 文件格式是否合法
         */
        private static boolean validateFile(MultipartFile file, long maxLength, String[] allowExtName) {
            if (file.getSize() < 0 || file.getSize() > maxLength) {
                return false;
            }
            String filename = file.getOriginalFilename();

            // 处理不选择文件点击上传时，也会有MultipartFile对象，在此进行过滤
            if (filename == "") {
                return false;
            }
            //判断文件后缀
            String extName = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            if (allowExtName == null || allowExtName.length == 0 || Arrays.binarySearch(allowExtName, extName) != -1) {
                return true;
            } else {
                return false;
            }
        }


    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File("F:\\Tool\\nginx\\nginx-file\\data\\file\\xinzhuang\\cw\\ry\\" + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }



}
