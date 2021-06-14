package com.study.service;

import com.study.spring.*;

import static com.study.spring.ScopeEnum.singleton;

/**
 * @author 念着倒才子傻
 */
@Component("userService")
@Scope("singleton")
public class UserService implements Aware {

    @Autowired
    private OrderService orderService;

    @Override
    public void print(String beanName) {
        System.out.println("bean的名字为 ==> " + beanName);
    }

    public void printOrderService() {
        System.out.println(orderService);
    }
}
