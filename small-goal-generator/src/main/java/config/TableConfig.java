package config;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成MVC配置文件
 *
 * @author 念着倒才子傻
 */
public class TableConfig {
    /**
     * 数据库主键名称
     */
    public static final String ID = "id";
    /**
     * 数据库主键 modelId
     */
    public static final String MODEL_ID = "id";
    /**
     * 表名
     */
    public static final String TABLE_NAME = "";
    /**
     * 表备注
     */
    public static final String TABLE_REMARK = "";
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
}
