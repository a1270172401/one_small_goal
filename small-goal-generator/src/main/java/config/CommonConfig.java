package config;

import org.springframework.beans.factory.annotation.Value;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 公共配置文件
 * @author 念着倒才子傻
 */
public class CommonConfig {
    /**
     * 操作类型
     */
    public static final Type TYPE = Type.MEKE_SPECIAL_VO;
    /**
     * 作者
     */
    public static final String AUTHOR = "念着倒才子傻";
    /**
     * 邮箱
     */
    public static final String EMAIL = "1270172401@qq.com";
    /**
     * 日期
     */
    public static final String DATE = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
    /**
     * 年份
     */
    public static final String YEAR = new SimpleDateFormat("yyyy年").format(new Date());
    /**
     * 包名
     */
    public static final String BASE_PACKAGE = "com.temporary";
    /**
     * 导报最外层包名,需要和文件生成位置最后一级目录对应
     */
    public static final String PACKAGE_FIRST = "temporary";
    /**
     * 生成文件的位置，默认在当前项目的目录下
     */
    public static final String OUT_PATH = TableConfig.class.getResource(".").getPath().split("target")[0]+"src/main/java/temporary";
    /**
     * 需要移除的表名前缀,使用逗号进行分隔多个前缀,示例值: t_,v_，默认为空
     */
    public static final String TABLE_REMOVE_PREFIXES = null;
    /**
     * 生成特殊实体类 这无需更改
     */
    public static final int GET_SPECIAL_VO = 1;
    /**
     * 生成MVC 这无需更改
     */
    public static final int GET_MVC = 2;
}
