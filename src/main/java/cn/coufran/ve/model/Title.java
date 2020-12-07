package cn.coufran.ve.model;

import lombok.Data;

/**
 * 会计科目
 * @author coufran
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public class Title {
    /** 科目ID */
    private Integer id;
    /** 科目名称 */
    private String name;
    /** 科目分类 */
    private Kind kind;

    /**
     * 会计科目分类
     * @author coufran
     * @version 1.0.0
     * @since 1.0.0
     */
    public enum Kind {
        /** 资产类 */
        ASSETS,
        /** 负债类 */
        LIABILITY,
        /** 损益类_收入 */
        PROFIT,
        /** 损益类_成本/费用 */
        LOSS
    }
}
