package com.fijo.fileservice.dto;


import com.fijo.fileservice.enums.SysEnum.StatueEnum;
import com.fijo.fileservice.util.JsonUtil;
import lombok.Data;

@Data
public class ResultDto<T> {
    private String code;
    private String msg;
    private T data;

    /**
     * 默认空参数成功
     * @return
     */
    public static String SUCCESS(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_SUCCESS.getCode());
        resultDto.setMsg(StatueEnum.RESULT_CODE_SUCCESS.getMsg());
        resultDto.setData(null);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 带自定义提示消息成功，空数据
     * @return
     */
    public static String SUCCESS(String msg){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_SUCCESS.getCode());
        resultDto.setMsg(msg);
        resultDto.setData(null);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 自定义返回数据，默认提示消息,返回成功
     * @return
     */
    public static String SUCCESS(Object obj){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_SUCCESS.getCode());
        resultDto.setMsg(StatueEnum.RESULT_CODE_SUCCESS.getCode());
        resultDto.setData(obj);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 自定义返回数据，自定义提示消息,返回成功
     * @return
     */
    public static String SUCCESS(String msg ,Object obj){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_SUCCESS.getCode());
        resultDto.setMsg(msg);
        resultDto.setData(obj);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 默认空参数成功
     * @return
     */
    public static String ERROR(){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_ERROR.getCode());
        resultDto.setMsg(StatueEnum.RESULT_CODE_ERROR.getMsg());
        resultDto.setData(null);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 带自定义提示消息成功，空数据
     * @return
     */
    public static String ERROR(String msg){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_ERROR.getCode());
        resultDto.setMsg(msg);
        resultDto.setData(null);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 自定义返回数据，默认提示消息,返回成功
     * @return
     */
    public static String ERROR(Object obj){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_ERROR.getCode());
        resultDto.setMsg(StatueEnum.RESULT_CODE_ERROR.getCode());
        resultDto.setData(obj);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

    /**
     * 自定义返回数据，自定义提示消息,返回成功
     * @return
     */
    public static String ERROR(String msg ,Object obj){
        ResultDto resultDto = new ResultDto();
        resultDto.setCode(StatueEnum.RESULT_CODE_ERROR.getCode());
        resultDto.setMsg(msg);
        resultDto.setData(obj);
        return JsonUtil.toJsonNoSerialize(resultDto);
    }

}
