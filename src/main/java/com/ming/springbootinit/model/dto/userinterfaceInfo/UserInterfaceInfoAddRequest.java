package com.ming.springbootinit.model.dto.userinterfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 请求参数对象
 *
 * @author ming
 * @from 
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {
    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

}