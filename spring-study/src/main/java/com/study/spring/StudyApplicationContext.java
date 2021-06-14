package com.study.spring;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 念着倒才子傻
 */
public class StudyApplicationContext {
    /**
     * 表示spring的配置类
     */
    private Class configClass;

    /**
     * 存储bean描述的map
     */
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    /**
     * 存储bean的单例池
     */
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>();

    /**
     * 存储bean的处理器
     */
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<BeanPostProcessor>();

    /**
     * spring启动类，大致流程如下
     * 1 扫描配置类注解下需要扫描的包名，获取我们需要的beanDefinition集合
     * 2 根据beanDefinition实例化非懒加载单例bean
     *
     * @param configClass 配置类
     */
    public StudyApplicationContext(Class configClass) {
        this.configClass = configClass;
        // 扫描配置类的包名下的所有beanDefinition
        scanBean();
        // 根据beanDefinition创建非懒加载的单例bean
        instanceSingletonBean();
    }

    /**
     * 根据beanDefinition创建单例bean
     */
    private void instanceSingletonBean() {
        // 遍历beanDefinition
        for (String beanName : beanDefinitionMap.keySet()) {
            System.out.println(beanName);
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            // 懒加载和原型bean跳过初始化
            if (beanDefinition.getLazy() && ScopeEnum.prototype.equals(beanDefinition.getScope())) {
                continue;
            }
            doCreateBean(beanName, beanDefinition);
        }
    }

    /**
     * 扫描配置类包名下的bean，并且放入BeanDefinition集合
     * BeanDefinition：描述一个bean的信息 这里的beanDefinition对比spring中做了一些简化
     */
    private void scanBean() {
        // 判断是否存在扫描注解
        boolean annotationPresent = configClass.isAnnotationPresent(ComponentScan.class);
        // 如果不存在，抛出异常
        if (!annotationPresent) {
            throw new RuntimeException("@ComponentScan is could not null");
        }
        // 获取注解上的信息
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        // 取出注解的value属性 这里就是包名
        String path = componentScanAnnotation.value();
        System.out.println(path);
        // 将包名替换为完整路径
        path = path.replace(".", "/");
        // 获取ApplicationContext的classLoader
        ClassLoader classLoader = StudyApplicationContext.class.getClassLoader();
        // 获取相对路径
        URL resource = classLoader.getResource(path);
        System.out.println(resource);
        // 获取路径下的文件
        File file = new File(resource.getFile());
        // 判断是否为文件夹，如果不是则说明指定扫描的包有问题
        if (!file.isDirectory()) {
            throw new RuntimeException("ComponentScan is error");
        }
        // 遍历文件
        for (File listFile : file.listFiles()) {
            // 真正在spring源码中使用ASM技术判断字节码有没有这些注解，我们这里就加载class对象，判断类上面是否存在对应的注解
            // 获取文件地址 E:\one_small_goal\one_small_goal\spring-study\target\classes\com\study\service\UserService.class
            String fileName = listFile.getAbsolutePath();
            // 我们这里需要通过classLoader来获取到真正的类，所以需要将文件加载成Class
            // 判断地址是否是以 .class结尾的
            if (fileName.endsWith(".class")) {
                // 由于classLoader.loadClass加载文件需要的地址为com.study.service.UserService，我们这里做截取
                String className = fileName.substring(fileName.indexOf("com"), fileName.indexOf(".class"));
                className = className.replace("\\", ".");
                try {
                    Class<?> clazz = classLoader.loadClass(className);
                    // 判断得到的class是否加了@Component注解，如果没有加则无需加载为bean
                    if (!clazz.isAnnotationPresent(Component.class)) {
                        continue;
                    }
                    // 创建beanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setBeanClass(clazz);
                    // 获取bean的名称，这里默认bean名称为自己指定的，spring中bean名称为自己指定的
                    Component component = clazz.getAnnotation(Component.class);
                    String beanName = component.value();
                    // 解析类上的原型注解
                    if (clazz.isAnnotationPresent(Scope.class)) {
                        Scope scope = clazz.getAnnotation(Scope.class);
                        String scopeValue = scope.value();
                        if (ScopeEnum.singleton.name().equals(scopeValue)) {
                            beanDefinition.setScope(ScopeEnum.singleton);
                        } else {
                            beanDefinition.setScope(ScopeEnum.prototype);
                        }
                    } else {
                        beanDefinition.setScope(ScopeEnum.singleton);
                    }
                    // 解析类上的懒加载注解
                    if (clazz.isAnnotationPresent(Lazy.class)) {
                        beanDefinition.setLazy(true);
                    } else {
                        beanDefinition.setLazy(false);
                    }
                    // 判断该bean是否实现了BeanPostProcessor，如果实现了则将该bean添加到BeanPostProcessor的集合
                    if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                        try {
                            BeanPostProcessor instance = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                            beanPostProcessorList.add(instance);
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                    // 将beanDefinition存入Map
                    beanDefinitionMap.put(beanName, beanDefinition);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    /**
     * 获取bean
     *
     * @param beanName bean名称
     * @return bean对象
     */
    public Object getBean(String beanName) {
        // 先判断bean的单例池中是否存在该bean，如果存在则直接返回，如果不存在则去获取beanDefinition，去创建一个bean
        if (singletonObjects.containsKey(beanName)) {
            return singletonObjects.get(beanName);
        } else {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            return doCreateBean(beanName, beanDefinition);
        }
    }

    /**
     * 1 创建bean
     * 2 填充创建的bean的属性
     * 3 执行后置处理器等操作
     *
     * @param beanName       bean的名称
     * @param beanDefinition bean描述
     * @return bean
     */
    private Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        // 获取beanDefinition中存储的class
        Class beanClass = beanDefinition.getBeanClass();
        Object o = null;
        try {
            // 将class实例化
            Constructor declaredConstructor = beanClass.getDeclaredConstructor();
            o = declaredConstructor.newInstance();

            // 填充属性（解析@Autowried 注解） 获取类中的元素（可以理解为声明的全局变量）
            Field[] fileds = beanClass.getDeclaredFields();
            // 遍历元素
            for (Field file : fileds) {
                // 判断是否添加了Autowired注解,如果添加了该注解，则需要填充属性
                if (file.isAnnotationPresent(Autowired.class)) {
                    // spring此处先是byType然后才是byName，此处直接模拟byName
                    String fieldName = file.getName();
                    /*
                        此处存在循环依赖的问题，具体怎么解决需要参考spring源码，这个demo并未解决该问题
                        循环依赖可参考：https://blog.csdn.net/u010853261/article/details/77940767
                     */
                    Object bean = getBean(fieldName);
                    // 设置反射修改权限
                    file.setAccessible(true);
                    // 将属性赋值
                    file.set(o, bean);
                }
            }
            
            /*
                Aware回调
                比如UserService实现了spring的某个特定的接口，那么在bean的创建过程中会调用该接口实现特定功能
             */
            if (o instanceof Aware) {
                ((Aware) o).print(beanName);
            }

            // 初始化 实现原理和Aware回调差不多，就不举例了

            // 执行后置处理器
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                beanPostProcessor.doSomeThing();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 如果不为原型则存入单例池中
        if (ScopeEnum.singleton.equals(beanDefinition.getScope())) {
            singletonObjects.put(beanName, o);
        }
        return o;
    }

}
