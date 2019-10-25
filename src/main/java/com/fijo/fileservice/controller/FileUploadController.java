package com.fijo.fileservice.controller;


import com.fijo.fileservice.dto.ResultDto;
import com.fijo.fileservice.fileUplodUtil.UploadHelper;
import com.fijo.fileservice.model.FileUpload;
import com.fijo.fileservice.model.SysFileUpload;
import com.fijo.fileservice.service.FileUploadService;
import com.fijo.fileservice.service.impl.SysFileUploadServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


/**
 * 控制器
 * 党群党员
 *
 * @author
 * @Date 2019-07-16 15:34:58
 */
@RestController
@Slf4j
 //@RequestMapping(value = "/FileUpload")
public class FileUploadController {
    @Autowired
   private FileUploadService service;



    @GetMapping("/getAllTo")
    public String getAllTo() {
        return  "hello";
    }
    /**
    * @Description:获取附件
    * @Author:         liujy
    * @CreateDate:     2019/8/29 14:47
    */
    @ResponseBody
    @RequestMapping(value = "/getAll")
    public List getAll(FileUpload o) {

        o.setEnabled(1);
        o.setRemoved(0);
        List<FileUpload> fileUploadList = service.getAll(o);
        try {
            System.err.println(fileUploadList);
            return fileUploadList;
        } catch (Exception e) {
            log.info("[模块名] /partyOrganization, {}", e.getMessage());
            return null;
        }
    }
    /**
     * @Description: 写文件
     * @Author:         liujy
     * @CreateDate:     2019/8/30 16:54
     */
   @PostMapping(value = "/fileUplod")//FileUpload fileUpload,HttpServletRequest request
    public String  uplodFile(FileUpload fileUpload,HttpServletRequest request
            //@RequestBody FileUpload fileUpload
                        //   @RequestBody HttpServletRequest request
           // @RequestPart MultipartFile multipartFile
                        ) {
        String ret="";
        try {
           // System.err.println(multipartFile);
          //  System.err.println(multipartFile);
            if (fileUpload.getTenant()==null||fileUpload.getTenant().equals("")||fileUpload.getTenant().length()<1||
                    fileUpload.getOrgCode()==null||  fileUpload.getOrgCode().equals("")||fileUpload.getOrgCode().length()<1||
                    fileUpload.getModular()==null||fileUpload.getModular().equals("")||fileUpload.getModular().length()<1){
                ret="上传文件目录不能为空！";
                return ResultDto.ERROR(ret);
            }
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            List< MultipartFile> files = multipartRequest.getFiles("file");
            FileUpload filePath = service.resultUpload(files.get(0),fileUpload);
            System.out.println("返回值："+filePath);
            return ResultDto.SUCCESS("SUCCESS", filePath);
            //    return null;
        } catch (Exception e) {
            log.info("【fileUplodTo】/文件上传, {}", e.getMessage());
            return ResultDto.ERROR("文件上传失败");
        }
    }

    /**
     * @Description: 上传服务
     * @Author:         liujy
     * @CreateDate:     2019/8/30 16:54
     */
    @PostMapping(value = "/UploadService")//FileUpload fileUpload,HttpServletRequest request
    public String  UploadService(FileUpload fileUpload,HttpServletRequest request
                             //@RequestBody FileUpload fileUpload
                             //   @RequestBody HttpServletRequest request
                             // @RequestPart MultipartFile multipartFile
    ) {
        String ret="";
        try {
            // System.err.println(multipartFile);
            //  System.err.println(multipartFile);
//            if (fileUpload.getTenant()==null||fileUpload.getTenant().equals("")||fileUpload.getTenant().length()<1||
//                    fileUpload.getOrgCode()==null||  fileUpload.getOrgCode().equals("")||fileUpload.getOrgCode().length()<1||
//                    fileUpload.getModular()==null||fileUpload.getModular().equals("")||fileUpload.getModular().length()<1){
//                ret="上传文件目录不能为空！";
//                return ResultDto.ERROR(ret);
//            }
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            List< MultipartFile> files = multipartRequest.getFiles("file");
//            FileUpload filePath = service.resultUpload(files.get(0),fileUpload);
//            System.out.println("返回值："+filePath);
//            return ResultDto.SUCCESS("SUCCESS", filePath);
                return null;
        } catch (Exception e) {
            log.info("【fileUplodTo】/文件上传, {}", e.getMessage());
            return ResultDto.ERROR("文件上传失败");
        }
    }
    /**
    * @Description:绑定数据对应的实体id
    * @Author:         liujy
    * @CreateDate:     2019/8/30 16:13
    */
    @RequestMapping("/update")
    private String update(FileUpload o)throws Exception{
        try {

            int upload=service.updateTo(o);
            return ResultDto.SUCCESS("SUCCESS", upload);
        } catch (Exception e) {
        log.info("【fileUplodTo】/文件上传, {}", e.getMessage());
        return "文件上传失败！";
    }
    }

    //下载文件
    @RequestMapping("/downloadFile")
    private String downloadFile(HttpServletResponse response,FileUpload o) {
        o.setEnabled(1);
        o.setRemoved(0);
        List<FileUpload> get=service.getAll(o);
        if (get.size()<1){
            return "未找到对应的文件！";
        }
        String downloadFilePath =get.get(0).getFilePath();//被下载的文件在服务器中的路径
        String fileName =get.get(0).getFileName(); //文件名

         File file = new File(downloadFilePath);
        if (file.exists()) {
          //  response.setContentType("application/force-download");// 设置强制下载不打开            
            response.setContentType("application/octet-stream");// 二进制流
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                response.setHeader("Content-Length",String.valueOf(fis.available()));
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return "下载失败";
    }








}


