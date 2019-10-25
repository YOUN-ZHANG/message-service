package com.fijo.fileservice.mapper;


import com.fijo.fileservice.model.SysFileUpload;

import java.util.List;

public interface SysFileUploadMapper {
    List<SysFileUpload> getAll(SysFileUpload o);
}
