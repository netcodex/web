package com.lizard.simpleweb.bean;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 描述：layui-table动态列属性模型
 *
 * @author x
 * @since 2020-06-14 12:31
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Column {
    /**
     * 设定字段名。字段名的设定非常重要，且是表格数据列的唯一标识 eg: username
     */
    @NonNull
    private String field;
    /**
     * 设定标题名称 eg: 用户名
     */
    @NonNull
    private String title;
    /**
     * 设定列宽，若不填写，则自动分配；若填写，则支持值为：数字、百分比 200/30%
     * <p>
     * 请结合实际情况，对不同列做不同设定。
     */
    private int width;
    /**
     * 局部定义当前常规单元格的最小宽度（默认：60），
     * <p>
     * 一般用于列宽自动分配的情况。其优先级高于基础参数中的 cellMinWidth 100
     */
    private int minWidth;
    /**
     * 设定列类型。可选值有： <ui>
     * <li>normal（常规列，无需设定）
     * <li>checkbox（复选框列）
     * <li>radio（单选框列，layui 2.4.0 新增）
     * <li>numbers（序号列）
     * <li>space（空列）
     * <li/></ui>
     */
    private String type;
    /**
     * 是否全选状态（默认：false）。
     * <p>
     * 必须复选框列开启后才有效，如果设置 true，则表示复选框默认全部选中。 TRUE
     */
    private Boolean LAY_CHECKED;
    /**
     * 固定列。可选值有：
     * <p>
     * left（固定在左）
     * <p>
     * right（固定在右）。
     * <p>
     * 一旦设定，对应的列将会被固定在左或右，不随滚动条而滚动。 left（同 true）
     * <p>
     * 注意：如果是固定在左，该列必须放在表头最前面；如果是固定在右，该列必须放在表头最后面。 right
     */
    private String fixed;
    /**
     * 是否初始隐藏列，默认：false。layui 2.4.0 新增 TRUE
     */
    private Boolean hide;
    /**
     * Boolean/Object 是否开启该列的自动合计功能，默认：false。 TRUE
     * <p>
     * 当开启时，则默认由前端自动合计当前行数据。从 layui 2.5.6 开始.
     * <p>
     * 若接口直接返回了合计行数据，则优先读取接口合计行数据，格式如下：
     * 
     * <pre>
     *      codelayui.code
     * 		1. {
     *      2.   "code": 0,
     *      3.   "msg": "",
     *      4.   "count": 1000,
     *      5.   "data": [{}, {}]
     *      6.   "totalRow": {
     *      7.     "score": "666"
     *      8.     ,"experience": "999"
     *      9.   }
     *      10. }
     * 		11
     * </pre>
     * 
     * <p>
     * 如上，在 totalRow 中返回所需统计的列字段名和值即可。
     * <p>
     * 另外，totalRow 字段同样可以通过 parseData 回调来解析成为 table 组件所规定的数据格式。
     */
    private Boolean totalRow;
    /**
     * 用于显示自定义的合计文本。layui 2.4.0 新增 "合计："
     */
    private String totalRowText;
    /**
     * 是否允许排序（默认：false）。
     * <p>
     * 如果设置 true，则在对应的表头显示排序icon，从而对列开启排序功能。 TRUE
     * <p>
     * 注意：不推荐对值同时存在“数字和普通字符”的列开启排序，
     * <p>
     * 因为会进入字典序比对。比如：'贤心' > '2' > '100'，这可能并不是你想要的结果，
     * <p>
     * 但字典序排列算法（ASCII码比对）就是如此。
     */
    private Boolean sort;
    /**
     * Boolean 是否禁用拖拽列宽（默认：false）。
     * <p>
     * 默认情况下会根据列类型（type）来决定是否禁用，如复选框列，会自动禁用。
     * <p>
     * 而其它普通列，默认允许拖拽列宽，当然你也可以设置 true 来禁用该功能。 FALSE
     */
    private Boolean unresize;
    /**
     * 单元格编辑类型（默认不开启）目前只支持：text（输入框） text
     */
    private String edit;
    /**
     * 自定义单元格点击事件名，以便在 tool 事件中完成对该单元格的业务处理 任意字符
     */
    private String event;
    /**
     * 自定义单元格样式。即传入 CSS 样式 background-color: #5FB878; color: #fff
     */
    private String style;
    /**
     * 单元格排列方式。可选值有：left（默认）、center（居中）、right（居右） center
     */
    private String align;
    /**
     * Number 单元格所占列数（默认：1）。一般用于多级表头 3
     */
    private int colspan;
    /**
     * Number 单元格所占行数（默认：1）。一般用于多级表头 2
     */
    private int rowspan;
    /**
     * 自定义列模板，模板遵循 laytpl 语法。
     * <p>
     * 这是一个非常实用的功能，你可借助它实现逻辑处理，
     * <p>
     * 以及将原始数据转化成其它格式，如时间戳转化为日期字符等 详见自定义模板
     */
    private String templet;
    /**
     * 绑定工具条模板。可在每行对应的列中出现一些自定义的操作性按钮 详见行工具事件
     */
    private String toolbar;

}
