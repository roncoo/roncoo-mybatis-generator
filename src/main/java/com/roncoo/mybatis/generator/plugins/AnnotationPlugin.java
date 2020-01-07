package com.roncoo.mybatis.generator.plugins;

import java.util.List;
import java.util.Properties;

import com.roncoo.mybatis.generator.api.IntrospectedTable;
import com.roncoo.mybatis.generator.api.PluginAdapter;
import com.roncoo.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import com.roncoo.mybatis.generator.api.dom.java.Interface;
import com.roncoo.mybatis.generator.api.dom.java.TopLevelClass;

public class AnnotationPlugin extends PluginAdapter {
	private String annotationClassName;
	private String annotationName;

	@Override
	public void setProperties(Properties properties) {
		super.setProperties(properties);
		annotationClassName = String.valueOf(properties.getProperty("annotationClass"));
		annotationName = String.valueOf(properties.getProperty("annotationName"));
	}

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		FullyQualifiedJavaType importedType = new FullyQualifiedJavaType(annotationClassName);
		interfaze.addImportedType(importedType);
		interfaze.addAnnotation(annotationName);
		return true;
	}

}
