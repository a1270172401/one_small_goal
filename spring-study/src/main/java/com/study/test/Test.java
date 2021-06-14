package com.study.test;

import com.study.config.AppConfig;
import com.study.service.UserService;
import com.study.spring.StudyApplicationContext;

/**
 * @author 念着倒才子傻
 */
public class Test {
    public static void main(String[] args) {
         /*
         启动spring 扫描，创建bean（非懒加载的单例bean）
         单例bean ==> 整个spring容器中只有这一个bean 懒加载：第一次使用的时候创建 非懒加载：spring启动会直接创建
         原型bean ==> 每次去使用的时候去创建一个新的bean
         */
        StudyApplicationContext applicationContext = new StudyApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
        System.out.println(userService);
        userService.printOrderService();
        userService.printOrderService();
        userService.printOrderService();
    }
}
