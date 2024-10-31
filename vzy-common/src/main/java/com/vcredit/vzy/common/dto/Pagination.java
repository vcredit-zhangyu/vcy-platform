package com.vcredit.vzy.common.dto;

import java.util.List;
import lombok.Data;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Data
public class Pagination<T> {

    /**
     * 当前页
     */
    private long page;
    /**
     * 每页的数量
     */
    private long pageSize;
    /**
     * 总记录数
     */
    private long total;
    /**
     * 结果集
     */
    private List<T> records;
}
