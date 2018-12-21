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

