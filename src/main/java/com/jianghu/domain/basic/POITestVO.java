package com.jianghu.domain.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ExcelTarget("POITestVO")
public class POITestVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * type = 10 代表数字
     * mergeVertical 自动合并相关单元格
     */
    @Excel(name = "编号", mergeVertical = true, orderNum = "1", type = 10)
    private Integer no;

    @Excel(name = "名称", orderNum = "2")
    private String name;

    /**
     * isStatistics 自动统计总数
     */
    @Excel(name = "数量", orderNum = "3", type = 10, isStatistics = true)
    private Integer num;

    @Excel(name = "备注", orderNum = "4", width = 100)
    private String remark;
}
