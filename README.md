## 代码生成器

​	在 src\main\java\com\java\test\mybatisplus\CodeGenerator.java  中的

```
private static final String FY_DB_URL = "ip:端口/数据库";
private static final String FY_DB_USERNAME = "用户名";
private static final String FY_DB_PASSWORD = "密码";
```

里面修改自己的数据库环境 然后表明 和去除的前缀指定一下

```
/**
 * 表名前缀 (生成后去掉前缀)
 */
private static final String TABLE_PREFIX = "jm_";
/**
 * 生成的表
 */
private static final String[] TABLE_ARRAY = {"jm_vip_level"};
```



运行CodeGenerator 就可以直接生成了