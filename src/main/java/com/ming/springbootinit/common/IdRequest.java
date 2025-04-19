package com.ming.springbootinit.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 *
 * @author ming
 * @from 
 */
@Data
public class IdRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}