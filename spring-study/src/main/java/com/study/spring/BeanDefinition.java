package com.study.spring;

/**
 * @author 念着倒才子傻
 */
public class BeanDefinition {
    /**
     * 存储Class
     */
    private Class beanClass;
    /**
     * 是否为原型
     */
    private ScopeEnum scope;

    /**
     * 懒加载
     */
    private Boolean lazy;

    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public ScopeEnum getScope() {
        return scope;
    }

    public void setScope(ScopeEnum scope) {
        this.scope = scope;
    }
}
