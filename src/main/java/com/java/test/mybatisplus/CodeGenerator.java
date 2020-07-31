package com.java.test.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;


import java.util.HashMap;
import java.util.Map;

public class CodeGenerator {
    /**
     * 项目所在的磁盘目录
     */
    private static final String PROJECT_ROOT_DIR = "D:\\0_DEV_ENV\\0_project_fengyu\\temp\\";
    // private static final String PROJECT_ROOT_DIR = "C:\\Users\\EDZ\\Desktop\\test\\";
    /**
     * 模块名
     */
    private static final String MODULE_NAME = "finance";
    /**
     * 作者
     */
    private static final String AUTHOR_NAME = "dzw";
    /**
     * 表名前缀 (生成后去掉前缀)
     */
    private static final String TABLE_PREFIX = "jm_";
    /**
     * 生成的表
     */
    private static final String[] TABLE_ARRAY = {"jm_vip_level"};
    /**
     * 1 连蜂羽的库 2 连车线的库
     */
    private static final int DB_ENV = 1;

    /**
     * 运行生成代码
     *
     * @param args
     */
    public static void main(String[] args) {
         genCode();
    }


    /**
     * 生成模板代码
     */
    public static void genCode() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        if (1 == DB_ENV) {
            //链接数据库
            dataSourceConfig.setUrl(concatDbUrl(FY_DB_URL));
            dataSourceConfig.setUsername(FY_DB_USERNAME);
            dataSourceConfig.setPassword(FY_DB_PASSWORD);
        }
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(TABLE_ARRAY);
        strategyConfig.setTablePrefix(TABLE_PREFIX);
        // lombok风格
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setCapitalMode(true);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor(AUTHOR_NAME);
        globalConfig.setOpen(true);
        globalConfig.setOutputDir(PROJECT_ROOT_DIR + "src\\main\\java");
        // 新文件覆盖旧文件
        globalConfig.setFileOverride(true);
        globalConfig.setEnableCache(false);
        globalConfig.setActiveRecord(false);
        globalConfig.setBaseColumnList(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setMapperName("%sDao");
        globalConfig.setEntityName("%sModel");
        globalConfig.setControllerName("%sController");

        // 自定义的模板文件的包结构
        PackageConfig pc = new PackageConfig();
        pc.setParent(ROOT_PATH);
        pc.setController(CONTROLLER_PATH);
        pc.setEntity(MODEL_PATH);
        pc.setMapper(DAO_PATH);
        pc.setService(SERVICE_PATH);
        pc.setServiceImpl(SERVICE_IMPL_PATH);
        // 这里没有支持xml生成到resource/mapper的原因在于mp不支持自定义mapper xml的父目录
        // 默认为GlobalConfig#outputDir+PackageConfig#parent路径
        // 以后慢慢优化
        pc.setXml(DAO_PATH);

        // 自定义的模板文件
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml("/templates/v5/Mapper.xml");
        templateConfig.setMapper("/templates/v5/Mapper.java");
        templateConfig.setService("/templates/v5/Service.java");
        templateConfig.setServiceImpl("/templates/v5/Serviceimpl.java");
        templateConfig.setEntity("/templates/v5/Model.java");

        // load自定义模板代码 (可拓展)
        // List<FileOutConfig> focList = new ArrayList<>();
        // FileOutConfig requestFoc = loadMPageRequestModel();
        // MPageRequest
        // focList.add(requestFoc);
        InjectionConfig cfg = loadInjectionConfig();
        // cfg.setFileOutConfigList(focList);

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setTemplate(templateConfig);
        autoGenerator.setCfg(cfg);
        autoGenerator.setPackageInfo(pc);
        // 生成代码
        autoGenerator.execute();
    }

    // 数据库环境
    private static final String FY_DB_URL = "ip:端口/数据库";
    private static final String FY_DB_USERNAME = "用户名";
    private static final String FY_DB_PASSWORD = "密码";

    /**
     * ROOT PATH
     */
    private static final String ROOT_PATH = "com.jumeng.sys";
    /**
     * Model 所在的包路径
     */
    private static final String MODEL_PATH = "model." + MODULE_NAME;
    /**
     * Controller 所在的包路径
     */
    private static final String CONTROLLER_PATH = "controller.apollo";
    /**
     * Service 所在的包路径
     */
    private static final String SERVICE_PATH = "service." + MODULE_NAME;
    /**
     * Service Impl 所在的包路径
     */
    private static final String SERVICE_IMPL_PATH = "service." + MODULE_NAME + ".impl";
    /**
     * Dao 所在的包路径
     */
    private static final String DAO_PATH = "service." + MODULE_NAME + ".dao";

    private static String concatDbUrl(String url) {
        return "jdbc:mysql://" + url
            + "?zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
    }

    private static InjectionConfig loadInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor());
                this.setMap(map);
            }
        };
    }

    // private static FileOutConfig loadMPageRequestModel() {
    // return new FileOutConfig("/templates/v4/MPageRequest.java.vm") {
    // @Override
    // public String outputFile(TableInfo tableInfo) {
    // return OUT_PUT_DIR + "\\request\\MPageRequest.java";
    // }
    // };
    // }

}
