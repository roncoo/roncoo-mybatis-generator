/*
 * Copyright 2015-2016 RonCoo(http://www.roncoo.com) Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.generator.plugins;

import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 标记注解插件
 * 
 * @author wujing
 */
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
