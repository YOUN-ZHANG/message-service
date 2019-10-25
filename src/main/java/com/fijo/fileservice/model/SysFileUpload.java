package com.fijo.fileservice.model;

import lombok.Data;

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
public class SysFileUpload implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;

    /**
     * 组织编码
     */
    private Integer orgCode;

    /**
     * 实体类型编码
     */
    private String entityTypeCode;

    /**
     * 业务实体ID
     */
    private Long objectId;

    /**
     * 名称
     */
    private String fileName;
    /**
     * 文件存放服务器后生成的名字
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
     * 尺寸
     */
    private Long fileSize;

    /**
     * 附件描述
     */
    private String desc;

    /**
     * 附件用途
     */
    private String fileUsecase;

    /**
     * 扩展字段1 附件类型分类，将此附件与其它同业务实体的附件做区分处理
     */
    private String attr1;

    /**
     * 扩展字段2
     */
    private String attr2;

    /**
     * 扩展字段3
     */
    private String attr3;

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

}
