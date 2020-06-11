package com.lizard.simpleweb;

import java.io.IOException;

/**
 * 练习JavaDoc常用语法(参照{@code https://www.cnblogs.com/boring09/p/4274893.html})
 *
 * @author <a href="ncoder@yeah.net">x</a>
 * @version 标识版本
 * @since 2020-06-11 22:47
 */
public class JavaDocTest {

    /**
     * 1.将文本标记为代码{@code Map}.
     * <p>
     * 2.快速链接到相关代码：
     * <p>
     * 链接到指定类中的方法：{@link java.util.Map#getOrDefault(Object, Object)};
     * <p>
     * 链接到当前类中的方法：{@link #main(String[])}
     * <p>
     * 3.类或使用方法的示例
     * <pre class="domain">
     *  public static void register(){
     *      docker.registerService();
     *  }
     *  </pre>
     * 4.怎么换行？
     * <p>
     * 5.项目符号
     * <ui>
     * <li>String</li>
     * <li>Boolean</li>
     * <li>Integer</li>
     * </ui>
     * 6.参考类或方法
     * <p>
     * 以上可看着是描述部分，以下是常用标记部分：
     *
     * @param str 表示参数
     * @return String 返回值
     * @throws IOException 异常
     * @see java.util.Map
     */
    public String testMethodNote(String str) throws IOException {
        return str;
    }

    public static void main(String[] args) throws IOException {
        JavaDocTest test = new JavaDocTest();
        test.testMethodNote("JavaDoc");
    }
}
