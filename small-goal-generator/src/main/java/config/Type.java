package config;

import lombok.Data;

/**
 * @author 念着倒才子傻
 */
public enum Type {

    /**
     * 生成特殊VO
     */
    MEKE_SPECIAL_VO("生成特别的VO对象",1),
    /**
     * 生成数据库表的MVC层
     */
    MEKE_DATABASE_ENTITY("生成数据库实体类和MVC层",2);

    private String detail;
    private int type;

    Type(String detail, Integer type) {
        this.detail = detail;
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
