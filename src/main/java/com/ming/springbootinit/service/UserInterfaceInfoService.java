package com.ming.springbootinit.service;

import com.ming.springbootinit.model.entity.InterfaceInfo;
import com.ming.springbootinit.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 嘿嘿嘿
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2025-04-20 15:18:59
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 统计调用次数
     * @param interfaceInfoId
     * @return
     */

    boolean invokeCount(long interfaceInfoId,long userId);


}
