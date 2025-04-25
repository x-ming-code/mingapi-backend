package com.ming.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ming.mingapicommon.service.UserInterfaceInfoService;

import com.ming.mingapicommon.model.entity.UserInterfaceInfo;

import com.ming.springbootinit.common.ErrorCode;
import com.ming.springbootinit.exception.BusinessException;
import com.ming.springbootinit.mapper.UserInterfaceInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 嘿嘿嘿
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2025-04-20 15:18:59
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        // 判断接口信息对象是否为空,为空则抛出参数错误的异常
        if (userInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }


        // 如果是添加操作,所有参数必须非空,否则抛出参数错误的异常
        if (add) {
            if (userInterfaceInfo.getUserId()<=0||userInterfaceInfo.getInterfaceInfoId()<=0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口或用户不存在");
            }
        }
        if (userInterfaceInfo.getLeftNum()<0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余次数不能小于 0");
        }
    }

    /**
     * 统计调用次数
     * @param interfaceInfoId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId<=0||userId<=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        UpdateWrapper<UserInterfaceInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper
                .eq("interfaceInfoId",interfaceInfoId)
                .eq("userId",userId)
                .setSql("leftNum = leftNum -1,totalNum = totalNum+1");
        return this.update(updateWrapper);
    }
}




