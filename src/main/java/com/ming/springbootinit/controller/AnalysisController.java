package com.ming.springbootinit.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.mingapicommon.model.entity.InterfaceInfo;
import com.ming.mingapicommon.model.entity.UserInterfaceInfo;
import com.ming.springbootinit.annotation.AuthCheck;
import com.ming.springbootinit.common.BaseResponse;
import com.ming.springbootinit.common.ErrorCode;
import com.ming.springbootinit.common.ResultUtils;
import com.ming.springbootinit.constant.UserConstant;
import com.ming.springbootinit.exception.BusinessException;
import com.ming.springbootinit.mapper.UserInterfaceInfoMapper;
import com.ming.springbootinit.model.enums.UserRoleEnum;
import com.ming.springbootinit.model.vo.InterfaceInfoVO;
import com.ming.springbootinit.service.InterfaceInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ming
 * @description
 * @date 2025/4/26 19:41
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        //将查询出来的信息根据接口id进行分组 Map的键就是接口id 值就是分组后的 UserInterfaceInfo 对象的列表
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap =
                userInterfaceInfoList.stream()
                        .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));

        //构造查询条件根据接口id查询接口信息
        QueryWrapper<InterfaceInfo> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        List<InterfaceInfo> infoList = interfaceInfoService.list(infoQueryWrapper);
        if (CollectionUtil.isEmpty(infoList)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

        // 将接口信息封装到 InterfaceInfoVO 中
        List<InterfaceInfoVO> collect = infoList.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(collect);
    }
}
