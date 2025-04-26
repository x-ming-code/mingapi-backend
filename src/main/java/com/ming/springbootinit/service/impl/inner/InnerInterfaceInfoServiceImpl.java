package com.ming.springbootinit.service.impl.inner;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.mingapicommon.model.entity.InterfaceInfo;
import com.ming.mingapicommon.model.entity.User;
import com.ming.mingapicommon.service.InnerInterfaceInfoService;
import com.ming.springbootinit.common.ErrorCode;
import com.ming.springbootinit.exception.BusinessException;
import com.ming.springbootinit.mapper.InterfaceInfoMapper;
import com.ming.springbootinit.service.InterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ming
 * @description
 * @date 2025/4/25 16:52
 */
@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
        if (StrUtil.isAllBlank(url,method)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url",url)
                .eq("method",method);
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
