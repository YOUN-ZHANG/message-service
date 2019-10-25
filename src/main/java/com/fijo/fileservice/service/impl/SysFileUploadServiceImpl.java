
package com.fijo.fileservice.service.impl;


import com.fijo.fileservice.mapper.SysFileUploadMapper;
import com.fijo.fileservice.model.SysFileUpload;
import com.fijo.fileservice.service.SysFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author
 * @since 2019-07-15
 */
@Service
public class SysFileUploadServiceImpl implements SysFileUploadService {

@Autowired
private SysFileUploadMapper mapper;


    @Override
    public List<SysFileUpload> getAll(SysFileUpload o) {
        return mapper.getAll(o);
    }

}
