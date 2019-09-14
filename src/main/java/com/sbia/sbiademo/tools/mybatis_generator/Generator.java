package com.sbia.sbiademo.tools.mybatis_generator;

import java.io.IOException;
import java.io.InputStream;
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
 * MyBatis 代码生成器
 */
public class Generator {

	public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
		List<String> warnings=new ArrayList<>();
		InputStream is=Generator.class.getResourceAsStream("/mybatis-generator-Config/generatorConfig.xml");
		ConfigurationParser cp=new ConfigurationParser(warnings);
		Configuration config=cp.parseConfiguration(is);
		is.close();
		DefaultShellCallback callback=new DefaultShellCallback(false);
		MyBatisGenerator myBatisGenerator=new MyBatisGenerator(config,callback,warnings);
		myBatisGenerator.generate(null);
		for(String warning:warnings) {
			System.out.println(warning);
		}
	}

}
