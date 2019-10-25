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
public class FileParameter implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 文件保存路径
     */
    private String workingPath;

    /**
     * 文件
     */
    private String file;

    /**
     * 原文件名
     */
    private String fileName;
   /**
     生成随机的文件名
     */
    private String fileNewName;
    /**
     * 文件后缀
     */
    private String fileSuffix;





}
