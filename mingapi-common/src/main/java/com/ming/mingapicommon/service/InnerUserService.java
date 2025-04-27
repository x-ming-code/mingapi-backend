package com.ming.mingapicommon.service;


import com.ming.mingapicommon.model.entity.User;

/**
 * 用户服务
 *
 * @author ming
 * @from 
 */
public interface InnerUserService {

    /**
     * 根据accessKey和secretKey获取调用用户
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);


}
