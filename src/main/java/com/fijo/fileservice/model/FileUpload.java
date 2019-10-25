package com.fijo.fileservice.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author ljy
 * @since 2019-07-15
 */
@Data
public class FileUpload implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 租户编码
     */
    private String tenant;

    /**q
     * 模块
     */
    private String modular;

    /**
     * 组织编码
     */
    private String orgCode;

    /**
     * 业务实体ID
     */
    private Long objectId;


    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件服务器上的名称
     */
    private String fileNewName;

    /**
     * 文件保存路径
     */
    private String filePath;

    /**
     * 文件类型
     */
    private String fileClass;

    /**
     * 文件尺寸
     */
    private String fileSize;

    /**
     * 描述
     */
    private String desc;

    /**
     * 附件用途
     */
    private String fileUsecase;
    /**
     * 是否有效
     */
    private Integer enabled;

    /**
     * 是否删除
     */
    private Integer removed;


    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人ID
     */
    private Long updateUserId;

    /**
     * 更新人
     */
    private String updateUserName;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * ip地址
     */
    private String operateIp;

    //private MultipartFile multipartFile;

}
