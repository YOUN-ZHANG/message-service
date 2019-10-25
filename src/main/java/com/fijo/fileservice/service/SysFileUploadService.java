package com.fijo.fileservice.service;




import com.fijo.fileservice.model.SysFileUpload;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author
 * @since 2019-07-15
 */
public interface SysFileUploadService {
    List<SysFileUpload> getAll(SysFileUpload o);
}
