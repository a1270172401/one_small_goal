package config;

import org.springframework.context.annotation.Configuration;

/**
 * 生成特殊实体类配置文件
 *
 * @author 念着倒才子傻
 */
@Configuration
public class SpecialVoConfig {
    /**
     * excel文件路径
     */
    public static final String PATH = SpecialVoConfig.class.getClassLoader().getResource("tempExcel/special.xlsx").getPath();
}
