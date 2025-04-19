package com.ming.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ming.springbootinit.model.entity.InterfaceInfo;


/**
* @author 嘿嘿嘿
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2025-04-08 21:47:33
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 获取查询条件
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
//    QueryWrapper<InterfaceInfo> getQueryWrapper(InterfaceInfoQueryRequest interfaceInfoQueryRequest);

}
