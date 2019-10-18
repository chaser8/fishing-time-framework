package com.yangzb.framework.common.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-04-01 09:30
 **/
@Getter
@AllArgsConstructor
public enum StatusCd {

    CD_1000("1000", "有效","正常有效数据"),
    CD_1100("1100", "无效","逻辑删除"),
    CD_1200("1200", "未生效","草稿状态"),
    CD_1300("1300", "已归档","销售品订购以后，未竣工发起撤单，归档后状态为“已归档”"),
    CD_1001("1001", "将生效","订购/新装/新增后已归档但还未生效，如次月生效"),
    CD_1002("1002", "待恢复","恢复待失效的数据（恢复为已生效）未归档，如拆机撤单"),
    CD_1101("1101", "将失效","退订/拆机/取消后已归档但还未失效，如次月失效"),
    CD_1102("1102", "将失效","退订/拆机/取消后未归档，如拆机未生效"),
    CD_1301("1301", "待撤消","撤消待生效的数据（撤消为已失效）未归档，新装撤单");

    private String code;
    private String name;
    private String desc;
}