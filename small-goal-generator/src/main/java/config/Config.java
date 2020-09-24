package config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 念着倒才子傻
 */
public class Config {
    /**
     * 数据库连接
     */
    public static final String JDBC_URL = "jdbc:mysql://49.234.192.103:3306/test?useUnicode=true&amp;characterEncoding=UTF8";
    /**
     * 数据库账户
     */
    public static final String JDBC_USERNAME = "root";
    /**
     * 数据库密码
     */
    public static final String JDBC_PASSWORD = "root";
    /**
     * 数据库驱动
     */
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    /**
     * oracle需要指定jdbc.schema,其它数据库忽略此项配置
     */
    public static final String CATALOG = null;
    /**
     * oracle需要指定jdbc.schema,其它数据库忽略此项配置
     */
    public static final String SCHEMA = "%";
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
    public static final String OUT_PATH = Config.class.getResource(".").getPath().split("target")[0]+"src/main/java/temporary";
    /**
     * 需要移除的表名前缀,使用逗号进行分隔多个前缀,示例值: t_,v_，默认为空
     */
    public static final String TABLE_REMOVE_PREFIXES = null;



}
