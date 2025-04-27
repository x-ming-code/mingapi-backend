package com.ming.mingapicommon.service;


import com.ming.mingapicommon.model.entity.InterfaceInfo;

/**
* @author 嘿嘿嘿
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-04-08 21:47:33
*/
public interface InnerInterfaceInfoService  {

    /**
     * 从数据库中查询模拟接口是否存在
     * @param path
     * @param method
     * @return
     */
    InterfaceInfo getInterfaceInfo(String path, String method);

}
