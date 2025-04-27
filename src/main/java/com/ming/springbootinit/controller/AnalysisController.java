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
import org.springframework.util.CollectionUtils;
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
       /* List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
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
        return ResultUtils.success(collect);*/
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        // 将接口信息按照接口ID分组，便于关联查询
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        // 创建查询接口信息的条件包装器
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        // 设置查询条件，使用接口信息ID在接口信息映射中的键集合进行条件匹配
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        // 调用接口信息服务的list方法，传入条件包装器，获取符合条件的接口信息列表
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        // 判断查询结果是否为空
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 构建接口信息VO列表，使用流式处理将接口信息映射为接口信息VO对象，并加入列表中
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            // 创建一个新的接口信息VO对象
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            // 将接口信息复制到接口信息VO对象中
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            // 从接口信息ID对应的映射中获取调用次数
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            // 将调用次数设置到接口信息VO对象中
            interfaceInfoVO.setTotalNum(totalNum);
            // 返回构建好的接口信息VO对象
            return interfaceInfoVO;
        }).collect(Collectors.toList());

        // 返回处理结果
        return ResultUtils.success(interfaceInfoVOList);
    }
}
