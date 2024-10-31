package com.vcredit.vzy.common.vo;

import java.util.List;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/19
 */
@Data
public class EnumsResponseVo {

    private String enumCode;

    private List<EnumsVo> enumList;

}
