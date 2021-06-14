package com.study.spring;

/**
 * @author 念着倒才子傻
 */
@Component
public class BeanPostProcessorImpl implements BeanPostProcessor {
    @Override
    public void doSomeThing() {
        System.out.println("执行了后置处理器");
    }
}
