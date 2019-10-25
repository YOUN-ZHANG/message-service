package com.fijo.fileservice.service;




import com.fijo.fileservice.model.FileUpload;
import com.fijo.fileservice.model.SysFileUpload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2019-07-15
 */
public interface FileUploadService {
    List<FileUpload> getAll(FileUpload o);
    int createTo(FileUpload o);
    int  updateTo(FileUpload o);
    FileUpload resultUpload(MultipartFile file, FileUpload o)throws Exception;
}
