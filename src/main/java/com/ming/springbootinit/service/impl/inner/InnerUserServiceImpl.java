package com.ming.springbootinit.service.impl.inner;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.mingapicommon.model.entity.User;
import com.ming.mingapicommon.service.InnerUserService;
import com.ming.springbootinit.common.ErrorCode;
import com.ming.springbootinit.exception.BusinessException;
import com.ming.springbootinit.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author ming
 * @description
 * @date 2025/4/25 16:52
 */
@DubboService
public class InnerUserServiceImpl implements InnerUserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public User getInvokeUser(String accessKey) {
        if (StrUtil.isAllBlank(accessKey)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("accessKey",accessKey);
        com.ming.mingapicommon.model.entity.User user = userMapper.selectOne(queryWrapper);
        return user;
    }
}
