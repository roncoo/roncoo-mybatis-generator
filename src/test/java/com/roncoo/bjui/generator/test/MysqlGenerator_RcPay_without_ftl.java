/**
 * Copyright (c) 2011-2016, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.roncoo.bjui.generator.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器-mysql
 * 
 * @author wujing
 */
public class MysqlGenerator_RcPay_without_ftl {

	private static final String[] TABLES = new String[] {"pay_order","pay_order_serial"};
	private static final String AUTHOR = "wujing";

	// 包的根路径设置
	private static final String PACKAGE_PATH = "com.roncoo.pay.boss";
	private static final String PACKAGE_PATH_COM = "com.roncoo.pay.common";
	private static final String MODULE_NAME = "admin";
	private static final String SUPERCONTROLLERCLASS = "com.roncoo.pay.common.custom.base.BaseController";

	// 文件保存的位置
	private static final String OUTPUT_DIR = "D:/workspace/rc-pay/rc-pay-boss/";
	private static final String OUTPUT_DIR_JAVA = "src/main/java/";
	private static final String OUTPUT_DIR_XML = "src/main/resources/mybatis/";
	private static final String OUTPUT_DIR_FTL = "src/main/resources/templates/" + MODULE_NAME + "/";

	// 数据库配置
	private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_USER_NAME = "root";
	private static final String DB_PASSWORD = "";
	private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/rc_os_pay?characterEncoding=utf8";

	/**
	 * 代码生成
	 */
	public static void main(String[] args) {
		// 自定义需要填充的字段
		List<TableFill> tableFillList = new ArrayList<>();
		tableFillList.add(new TableFill("ASDD_SS", FieldFill.INSERT_UPDATE));

		// 全局配置
		GlobalConfig gc = new GlobalConfig();
		gc.setOutputDir(OUTPUT_DIR + OUTPUT_DIR_JAVA);// 输出目录
		gc.setFileOverride(true);// 是否覆盖文件
		gc.setActiveRecord(true);// 开启 activeRecord 模式
		gc.setEnableCache(false);// XML 二级缓存
		gc.setBaseResultMap(true);// XML ResultMap
		gc.setBaseColumnList(true);// XML columList
		gc.setAuthor(AUTHOR); // 作者

		// 自定义文件命名，注意 %s 会自动填充表实体属性！
		// gc.setMapperName("%sDao");
		// gc.setXmlName("%sDao");
		// gc.setServiceName("%sDao");
		// gc.setServiceImplName("%sDaoImpl");
		// gc.setControllerName("%sAction");

		// 数据源配置
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setDbType(DbType.MYSQL).setDriverName(DRIVER_NAME).setUsername(DB_USER_NAME).setPassword(DB_PASSWORD).setUrl(DB_URL);

		// 策略配置
		StrategyConfig sc = new StrategyConfig();
		// sc.setCapitalMode(true);// 全局大写命名
		// sc.setDbColumnUnderline(true);//全局下划线命名
		// sc.setTablePrefix(new String[] { "bmd_", "mp_" });// 此处可以修改为您的表前缀
		sc.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
		sc.setInclude(TABLES); // 需要生成的表

		// sc.setExclude(new String[]{"test"}); // 排除生成的表
		// sc.setSuperEntityClass("com.baomidou.demo.TestEntity"); // 自定义实体父类
		// 自定义实体，公共字段
		// sc.setSuperEntityColumns(new String[] { "test_id" }).setTableFillList(tableFillList);
		// 自定义 mapper 父类
		// sc.setSuperMapperClass("com.baomidou.demo.TestMapper");
		// 自定义 service 父类
		// sc.setSuperServiceClass("com.baomidou.demo.TestService");
		// 自定义 service 实现类父类
		// sc.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
		// 自定义 controller 父类
		sc.setSuperControllerClass(SUPERCONTROLLERCLASS);
		// 【实体】是否生成字段常量（默认 false）
		// public static final String ID = "test_id";
		// sc.setEntityColumnConstant(true);
		// 【实体】是否为构建者模型（默认 false）
		// public User setName(String name) {this.name = name; return this;}
		// sc.setEntityBuilderModel(true);
		
		// 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
		sc.setEntityLombokModel(true);
		
		// Boolean类型字段是否移除is前缀处理
		// sc.setEntityBooleanColumnRemoveIsPrefix(true);
		// sc.setRestControllerStyle(true);
		// sc.setControllerMappingHyphenStyle(true);

		// 包配置
		PackageConfig pc = new PackageConfig();
		pc.setParent(PACKAGE_PATH);// 自定义包路径
		pc.setModuleName(MODULE_NAME);

		// 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
		InjectionConfig ic = new InjectionConfig() {
			@Override
			public void initMap() {
				Map<String, Object> map = new HashMap<>();
				map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
				//if (StringUtils.isEmpty(MODULE_NAME)) {
					this.getConfig().getPackageInfo().put("Biz", PACKAGE_PATH + ".service");
					this.getConfig().getPackageInfo().put("Ctl", PACKAGE_PATH + ".controller." + MODULE_NAME);
					this.getConfig().getPackageInfo().put("Qo", PACKAGE_PATH + ".bean.qo");
					this.getConfig().getPackageInfo().put("Vo", PACKAGE_PATH + ".bean.vo");
					this.getConfig().getPackageInfo().put("Dao", PACKAGE_PATH_COM + ".dao");
					this.getConfig().getPackageInfo().put("Ent", PACKAGE_PATH_COM + ".entity");
					this.getConfig().getPackageInfo().put("Page", PACKAGE_PATH_COM + ".custom.bjui");
//				} else {
//					this.getConfig().getPackageInfo().put("Biz", PACKAGE_PATH + "." + MODULE_NAME + ".biz");
//					this.getConfig().getPackageInfo().put("Ctl", PACKAGE_PATH + "." + MODULE_NAME + ".controller");
//					this.getConfig().getPackageInfo().put("Qo", PACKAGE_PATH + "." + MODULE_NAME + ".qo");
//					this.getConfig().getPackageInfo().put("Vo", PACKAGE_PATH + "." + MODULE_NAME + ".vo");
//				}
				this.setMap(map);
			}
		};

		List<FileOutConfig> list = new ArrayList<>();
		list.add(new FileOutConfig("/template/mybatis/service.java.vm") {
			// 自定义输出文件目录
			@Override
			public String outputFile(TableInfo tableInfo) {
				//if (StringUtils.isEmpty(MODULE_NAME)) {
					return OUTPUT_DIR + OUTPUT_DIR_JAVA + PACKAGE_PATH.replace(".", "/") + "/service/" + tableInfo.getEntityName() + "Service.java";
				//} else {
				//	return OUTPUT_DIR + OUTPUT_DIR_JAVA + PACKAGE_PATH.replace(".", "/") + "/" + MODULE_NAME.replace(".", "/") + "/service/" + tableInfo.getEntityName() + "Service.java";
				//}
			}
		});
		list.add(new FileOutConfig("/template/mybatis/controller.java.vm") {
			// 自定义输出文件目录
			@Override
			public String outputFile(TableInfo tableInfo) {
				//if (StringUtils.isEmpty(MODULE_NAME)) {
					return OUTPUT_DIR + OUTPUT_DIR_JAVA + PACKAGE_PATH.replace(".", "/") + "/controller/" + MODULE_NAME.replace(".", "/") + "/" + tableInfo.getEntityName() + "Controller.java";
				//}
				//return OUTPUT_DIR + OUTPUT_DIR_JAVA + PACKAGE_PATH.replace(".", "/") + "/" + MODULE_NAME.replace(".", "/") + "/controller/" + tableInfo.getEntityName() + "Controller.java";
			}
		});
		
		ic.setFileOutConfigList(list);

		// 关闭默认 xml 生成，调整生成 至 根目录
		// 自定义模板配置，模板可以参考源码 /mybatis-plus/src/main/resources/template
		// 使用 copy至您项目 src/main/resources/template 目录下，模板名称也可自定义如下配置：
		TemplateConfig tc = new TemplateConfig();
		tc.setXml(null);
		tc.setController(null);
		tc.setEntity(null);
		tc.setMapper(null);
		tc.setService(null);
		tc.setServiceImpl(null);

		// 代码生成器
		AutoGenerator ag = new AutoGenerator();
		ag.setGlobalConfig(gc);
		ag.setDataSource(dsc);
		ag.setStrategy(sc);
		ag.setPackageInfo(pc);
		ag.setCfg(ic);
		ag.setTemplate(tc);
		ag.execute();// 执行生成

		// 打印注入设置，这里演示模板里面怎么获取注入内容【可无】
		System.err.println(ag.getCfg().getMap().get("abc"));
	}

}
