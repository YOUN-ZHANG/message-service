
package com.fijo.fileservice.service.impl;


import com.fijo.fileservice.enums.SysEnum.ResultEnum;
import com.fijo.fileservice.enums.SysEnum.StatueEnum;
import com.fijo.fileservice.fileUplodUtil.UploadFtpHelper;
import com.fijo.fileservice.fileUplodUtil.UploadHelper;
import com.fijo.fileservice.mapper.FileUploadMapper;
import com.fijo.fileservice.mapper.SysFileUploadMapper;
import com.fijo.fileservice.model.FileParameter;
import com.fijo.fileservice.model.FileUpload;
import com.fijo.fileservice.model.SysFileUpload;
import com.fijo.fileservice.service.FileUploadService;
import com.fijo.fileservice.service.SysFileUploadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2019-07-15
 */
@Slf4j
@Service
public class FileUploadServiceImpl implements FileUploadService {

@Autowired
private FileUploadMapper mapper;
//    @Value("${ftpHost}")
//    private String ftpHost;
//    @Value("${ftpPort}")
//    private int ftpPort;
//    @Value("${ftpUserName}")
//    private String ftpUserName;
//    @Value("${ftpPassword}")
//    private String ftpPassword;
//    @Value("${ftpPath}")
//    private String ftpPath;

    @Override
    public List<FileUpload> getAll(FileUpload o) {
        return mapper.getAll(o);
    }

    @Override
    public int createTo(FileUpload o) {
        return mapper.createTo(o);
    }

    @Override
    public int updateTo(FileUpload o) {
        return mapper.updateTo(o);
    }

    @Override
    public FileUpload resultUpload(MultipartFile file, FileUpload o) throws Exception{
        FileUpload upload=new FileUpload();
        try {
            String ftpPath="http://file.static.fijo.com.cn";
             String ftpHost = "172.16.100.199";
            int ftpPort = 21;
            String ftpUserName = "fijoftp";
            String ftpPassword = "123456";
            String workingPath="";
            String saveName ="";
            FileParameter parameter= UploadHelper.getParameter(file,o);
            workingPath=parameter.getWorkingPath();//新建一个文件 +租户编码+组织编码+模块名+文件类型
            saveName=parameter.getFileNewName();//文件名称
            InputStream fileInputStream =file.getInputStream();
                if (UploadFtpHelper.upload(ftpHost, ftpPort, ftpUserName, ftpPassword, workingPath, fileInputStream, saveName)){
                    upload.setEnabled(Integer.parseInt(StatueEnum.STATE_TRUE.getCode()));
                    upload.setRemoved(Integer.parseInt(StatueEnum.STATE_FALSE.getCode()));
                    upload.setTenant(o.getTenant());//租户编码
                    upload.setOrgCode(o.getOrgCode());//组织编码
                    upload.setModular(o.getModular());//模块名
                    upload.setFileName(parameter.getFileName());//原始文件名
                    upload.setFilePath(ftpPath+parameter.getWorkingPath()+"/"+parameter.getFileNewName());//文件保存路径
                    upload.setFileNewName(parameter.getFileNewName());//生成的文件名
                    upload.setFileClass(parameter.getFileSuffix());
                    createTo(upload);
            }
            return  upload;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
     return upload;
    }
}
