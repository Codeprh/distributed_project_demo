## AOP是什么？

aop是一种编程范式，不是编程语言，解决特定问题，不能解决全部问题，是OOP的补充，不是替换。

## 常见使用AOP的场景？

权限控制、缓存控制、事务控制、审计日志、性能监控、分布式追踪、异常处理等。

非功能性需求分离出来，减少代码的入侵性。

## 使用AOP的优势？

1. 集中处理某一关注点（横切逻辑）
2. 可以很方便地添加/删除关注点
3. 代码侵入性少，增强代码可读性和维护性。

## 设计AOP的初衷？

- DRY：don't repeat yourself，代码重复问题。
- SOC：separation of concerns，关注点分离。
  - -水平分离：展示层->服务层->持久层
  - -垂直分离：模块划分（订单、库存等）
  - -切面分离：分离功能性需求与非功能性需求

## 基于注解实现权限控制AOP

- 第一步：编写一个注解类

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AdminOnly {
}
```

注：讲讲注解

- 注解的生命周期

```java
public enum RetentionPolicy {
    /**
     * 注解只保留在源文件，当Java文件编译成class文件的时候，注解被遗弃.
     * 这意味着：Annotation仅存在于编译器处理期间，编译器处理完之后，该Annotation就没用了
     */
    SOURCE,
 
    /**
     * 注解被保留到class文件，但jvm加载class文件时候被遗弃，这是默认的生命周期.
     */
    CLASS,
 
    /**
     * 注解不仅被保存到class文件中，jvm加载class文件之后，仍然存在，
     * 保存到class对象中，可以通过反射来获取
     */
    RUNTIME
}

```

- 注解的使用范围

```java
// ElementType取值
public enum ElementType {
    /** 类、接口（包括注解类型）或枚举 */
    TYPE,
    /** field属性，也包括enum常量使用的注解 */
    FIELD,
    /** 方法 */
    METHOD,
    /** 参数 */
    PARAMETER,
    /** 构造函数 */
    CONSTRUCTOR,
    /** 局部变量 */
    LOCAL_VARIABLE,
    /** 注解上使用的元注解 */
    ANNOTATION_TYPE,
    /** 包 */
    PACKAGE
}

```

- 第二步：定义一个切面

```java
/**
 * 描述:
 * 权限校验切面
 *
 * @author codingprh
 * @create 2018-12-20 4:25 PM
 */
@Component
@Aspect
public class SecurityAspect {
    @Autowired
    private AuthService authService;

    // 使用要拦截标注有AdminOnly的注解进行操作
    @Pointcut("@annotation(adminOnly)")
    public void adminOnlyMethod(AdminOnly adminOnly) {

    }

    @Before("adminOnlyMethod(adminOnly)")
    public void check(AdminOnly adminOnly) {
        authService.checkAccess();
    }
    
    // 使用注解的全路径就不需要传入参数
    @Pointcut("@annotation(com.codingprh.demo.spring_aop_demo.simpleAccessControl.annotation.AdminOnly)")
    public void adminOnlyMethod() {

    }

    @Before("adminOnlyMethod()")
    public void check() {
        authService.checkAccess();
    }


}
```

- 第三步：编写测试类

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @Autowired
    private CurrentUserHolder currentUserHolder;

    @Test(expected = Exception.class)
    public void insert() {
        currentUserHolder.setHolder("tom");
        productService.insert();
    }

    @Test
    public void insertAdmin() {

        currentUserHolder.setHolder("admin");

        productService.insert();
    }

    @Test
    public void inquire() {
        productService.inquire();
    }
}
```

运行结果：

![image-20181221090535039](https://ws3.sinaimg.cn/large/006tNbRwgy1fye3ciu6rkj309k09gglv.jpg)

## Pointcut express:切面表达式

- designators（指示器）：描述通过什么样的方式去匹配哪些类、哪些方法

```java
匹配方法 execution()
匹配注解 @target() @args() @within() @annotation()
匹配包/类型 within()
匹配对象 this() bean() target()
匹配参数 args()
```

- Wildcards（通配符）：使用通配符进行描述

```java
* 匹配任意数量的字符
+ 匹配指定类及其子类
.. 一般用于匹配任意参数的子包或参数
```

- Operators（运算符）：使用运算符进行多条件的判断

```java
&& 与操作符
|| 或操作符
! 非操作符
```

- execution使用：

  ```java
  execution(
      modifier-pattern? // 修饰符匹配
      ret-type-pattern // 返回值匹配
      declaring-type-pattern? // 描述值包名
      name-pattern(param-pattern) // 方法名匹配（参数匹配）
      throws-pattern?// 抛出异常匹配
  )
  ```

### Pointcut表达式：匹配包类

```java
/**
     * 拦截具体的某个具体的类
*/
    @Pointcut("within(com.codingprh.demo.spring_aop_demo.simpleAccessControl.service.AuthService)")
    public void matchClass() {

    }
```

```java

    /**
     * 拦截包，和包下面所有的类
     */
    @Pointcut("within(com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*)")
    public void matchPackageAndAll() {

    }
```

### Pointcut表达式：配置对象

```java

    /**
     * 拦截以service结尾的bean里面的所有方法
     */
    @Pointcut("bean(*Service)")
    public void matchBean() {

    }
```

注：todo区分target和this的区别

### Pointcut表达式：匹配参数

```java

    /**
     * 拦截所有insert开头的方法,并且只有一个参数的方法
     */
    @Pointcut("execution(* *..insert*(Long))")
    public void matchArgs() {

    }
```

```java
    /**
     * 匹配一个参数类型为String类型
     */
    @Pointcut("args(String,..)")
    public void matchArgs() {

    }
```

注：谨慎使用args参数，应该要具体自己定义的类型，才使用。

### Pointcut表达式：匹配方法

```java

    /**
     * 匹配方法&&使用execution表达式
     *
     * 匹配以public 开头，在com.codingprh.demo.spring_aop_demo.simpleAccessControl.service
     * 包以及子包，用*Service结尾的类的任意方法，任意参数
     */
    @Pointcut("execution(public * com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*Service.*(..))")
    public void matchMethod() {

    }
```



## Advice(建言)注解

5种Advice注解

```java
@Before，前置通知
@After（finally），后置通知，方法执行完之后
@AfterReturning，返回通知，成功执行之后
@AfterThrowing，异常通知，抛出异常之后
@Around，环绕通知
```

#### 基于Around实现方法耗时统计

```java
package com.codingprh.demo.spring_aop_demo.pointcutExpressDemo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 描述:
 * 统计方法时间耗时切面
 *
 * @author codingprh
 * @create 2018-12-21 11:11 AM
 */
@Aspect
@Component
public class StatisticalTimeAspect {
    /**
     * 统计在com.codingprh.demo.spring_aop_demo.simpleAccessControl.service
     * <p>
     * 包以及子包下，任何以Service结尾的类
     * <p>
     * 类中的任何以public开头的方法
     * <p>
     * 方法任意参数
     */
    @Pointcut("execution(public * com.codingprh.demo.spring_aop_demo.simpleAccessControl.service..*Service.*(..))")
    public void consumingTime() {

    }

    @Around("consumingTime()")
    public Object realOperator(ProceedingJoinPoint pjp) throws Throwable {
        Long startTime = System.currentTimeMillis();
        System.out.println("调用方法：" + pjp.getSignature().getName() + "，startTime=" + startTime);
        System.out.println("相当于@Before");
        try {
            Object result = pjp.proceed(pjp.getArgs());
            System.out.println("相当于@AfterReturning");
            return result;
        } catch (Throwable throwable) {
            System.out.println("相当于@AfterThrowing");
            throw throwable;
        } finally {
            System.out.println("相当于@After");
            System.out.println("endTime,total=" + (System.currentTimeMillis() - startTime) + "ms");
        }
    }

}
```

## AOP实现原理



### 设计模式：代理模式、责任链模式

#### 静态代理和动态代理