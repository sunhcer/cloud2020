//package com.atguigu.springcloud.controller;
//
//import com.baomidou.mybatisplus.annotation.DbType;
//import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
//import com.baomidou.mybatisplus.core.toolkit.StringPool;
//import com.baomidou.mybatisplus.generator.AutoGenerator;
//import com.baomidou.mybatisplus.generator.InjectionConfig;
//import com.baomidou.mybatisplus.generator.config.*;
//import com.baomidou.mybatisplus.generator.config.po.TableInfo;
//import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
//import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
////import org.apache.commons.lang3.StringUtils;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Scanner;
///*
//*        <dependency>
//            <groupId>com.baomidou</groupId>
//            <artifactId>mybatis-plus-generator</artifactId>
//            <version>3.3.2</version>
//        </dependency>
//* */
//public class AutoGeneratorMain {
//
//    /**
//     * <p>
//     * 读取控制台内容
//     * </p>
//     */
//    public static String scanner(String tip) {
//        Scanner scanner = new Scanner(System.in);
//        StringBuilder help = new StringBuilder();
//        help.append("请输入" + tip + "：");
//        System.out.println(help.toString());
//        if (scanner.hasNext()) {
//            String ipt = scanner.next();
////            if (StringUtils.isNotEmpty(ipt)) {
////                return ipt;
////            }
//        }
//        throw new MybatisPlusException("请输入正确的" + tip + "！");
//    }
//
//    static final String projectBaseDir=System.getProperty("user.dir");
//    static final String javaOutDir=projectBaseDir.concat("/hm-tax-collect/src/main/java/com/hm/tax/collect/taxreport/");
//    static final String baseOutDir=projectBaseDir.concat("/src/main/java/taxreport");
//    static final String mapperXmlDir=projectBaseDir.concat("/hm-tax-collect/src/main/resources/mapper/entrestructtaxreport/");
//    static final String packageBaseName="com.hm.tax.collect.taxreport";
//
//    static final String jdbcUrl="jdbc:mysql://192.168.1.40:3306/UAT_hmtax?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&autoReconnect=true&serverTimezone=Asia/Shanghai";
//    static final String jdbcName="remote-user";
//    static final String jdbcPwd="123";
//
//    static final String templatePath = "/templates/mapper.xml.ftl";
//    public static void main(String[] args){
//        generator();
//    }
//
//
//    public static void generator() {
//        GlobalConfig gbc = new GlobalConfig();
//        gbc.setOutputDir(baseOutDir);
//        gbc.setFileOverride(false);//默认 false ,是否覆盖已生成文件
//        gbc.setOpen(true);//默认true ,是否打开输出目录
//        gbc.setAuthor(scanner("请输入创建者名称"));
//        gbc.setBaseResultMap(true);
//        gbc.setBaseColumnList(true);
//        gbc.setEntityName("%sEntity");
//        gbc.setMapperName("%sDao");
//        gbc.setXmlName("%sMapper");
//        gbc.setServiceName("I%sService");
//        gbc.setServiceImplName("%sServiceImpl");
//        gbc.setSwagger2(true);
//        gbc.setAuthor("weck.Han");
//
//        DataSourceConfig dataSource = new DataSourceConfig();
//        dataSource.setDbType(DbType.MYSQL);
//        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
//        dataSource.setUrl(jdbcUrl);
//        dataSource.setUsername(jdbcName);
//        dataSource.setPassword(jdbcPwd);
//
//        StrategyConfig syc = new StrategyConfig();
//        syc.setCapitalMode(true);
//        syc.setNaming(NamingStrategy.underline_to_camel);
//        syc.setColumnNaming(NamingStrategy.underline_to_camel);
////        syc.setTablePrefix("test_");
//        syc.setEntityLombokModel(false);//Lombokbook注解  @Data()
//        syc.setEntityTableFieldAnnotationEnable(true);//@TableField("数据库注解")
//        syc.setEntityBooleanColumnRemoveIsPrefix(true);
//        syc.setRestControllerStyle(true);
//        // 表名称
//        String tableNames = scanner("请输入表名称(同时生成多张表，用英文逗号间隔)");
//        String[] split = tableNames.split(",");
//        for (String tableName : split) {
//        syc.setInclude(tableName);
//        //自定义属性注入
//        InjectionConfig injectionConfig = new InjectionConfig() {
//            //自定义属性注入:abc
//            //在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
//            @Override
//            public void initMap() {
//
//            }
//        };
//
//
//
//        List<FileOutConfig> list = new ArrayList<>();
//        list.add(new FileOutConfig(templatePath) {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                return mapperXmlDir+ tableInfo.getMapperName() + StringPool.DOT_XML;
//            }
//        });
//
//        injectionConfig.setFileOutConfigList(list);
//
//        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setXml(null);
//
//        PackageConfig pck = new PackageConfig();
//        pck.setParent(packageBaseName);
//        pck.setMapper("dao");
//        pck.setService("service");
//        pck.setServiceImpl("service.impl");
//        pck.setEntity("entity");
//        pck.setController("controller");
//
//
//        Map<String, String> packInfo = new HashMap<>();
//        packInfo.put(ConstVal.ENTITY_PATH, javaOutDir.concat("entity"));
//        packInfo.put(ConstVal.MAPPER_PATH,  javaOutDir.concat("dao"));
//        packInfo.put(ConstVal.SERVICE_PATH, javaOutDir.concat("service"));
//        packInfo.put(ConstVal.SERVICE_IMPL_PATH, javaOutDir.concat("service/impl"));
//        packInfo.put(ConstVal.CONTROLLER_PATH, javaOutDir.concat("controller"));
//
//        pck.setPathInfo(packInfo);
//
//
//        new AutoGenerator().setGlobalConfig(gbc)
//                .setCfg(injectionConfig)
//                .setDataSource(dataSource)
//                .setStrategy(syc)
//                .setPackageInfo(pck)
//                .setTemplate(templateConfig)
//                .setTemplateEngine(new FreemarkerTemplateEngine())
//                .execute();
//    }
//
//    }
//}