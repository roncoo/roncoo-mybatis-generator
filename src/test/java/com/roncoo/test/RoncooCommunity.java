package com.roncoo.test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @功能描述 生成实体、接口和配置文件.
 * @author Mr.von
 * @time 2014年11月6日 下午4:04:13
 */
public class RoncooCommunity {

	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		System.out.println("Start generator ...");
		List<String> warnings = new ArrayList<String>();
		boolean overwrite = true; // 是否覆盖原来的文件
		File configFile = new File("D:/workspace-roncoo/roncoo-mybatis-generator/src/test/resources/roncoo-adminlte-springmvc.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		System.out.println("Finish generator ...");
	}
}
