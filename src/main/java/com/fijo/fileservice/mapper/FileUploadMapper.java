package com.fijo.fileservice.mapper;


import com.fijo.fileservice.model.FileUpload;
import com.fijo.fileservice.model.SysFileUpload;

import java.util.List;

public interface FileUploadMapper {
    List<FileUpload> getAll(FileUpload o);
    int createTo(FileUpload o);
    int  updateTo(FileUpload o);
}
