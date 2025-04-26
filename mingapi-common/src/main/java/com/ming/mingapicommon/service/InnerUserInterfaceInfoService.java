package com.ming.mingapicommon.service;




/**
* @author 嘿嘿嘿
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-04-20 15:18:59
*/

public interface InnerUserInterfaceInfoService  {

    /**
     * 统计调用次数
     * @param interfaceInfoId
     * @return
     */
    boolean invokeCount(long interfaceInfoId,long userId);


}
