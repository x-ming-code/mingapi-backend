package com.ming.springbootinit.model.vo;

import cn.hutool.json.JSONUtil;
import com.ming.springbootinit.model.entity.InterfaceInfo;
import com.ming.springbootinit.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 接口信息封装类
 *
 * @author ming
 * @from 
 */
@Data
public class InterfaceInfoVO extends InterfaceInfo implements Serializable {

    private static final long serialVersionUID = 5458466742471334646L;
    /**
     * id
     */
    private Integer totalNum;

}
