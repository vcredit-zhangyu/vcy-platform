package com.vcredit.vzy.dao.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.vcredit.vzy.common.dto.Pagination;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/23
 */
public class PageUtil {

    public static <T> Pagination<T> convertPagination(IPage<T> page) {
        if (page == null) {
            return new Pagination<>();
        }
        Pagination<T> pagination = new Pagination<>();
        pagination.setRecords(page.getRecords());
        pagination.setTotal(page.getTotal());
        pagination.setPageSize(page.getSize());
        pagination.setPage(page.getCurrent());
        return pagination;
    }
}
