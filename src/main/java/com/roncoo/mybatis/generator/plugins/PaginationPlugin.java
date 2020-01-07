package com.roncoo.mybatis.generator.plugins;
import java.util.List;

import com.roncoo.mybatis.generator.api.CommentGenerator;
import com.roncoo.mybatis.generator.api.IntrospectedTable;
import com.roncoo.mybatis.generator.api.PluginAdapter;
import com.roncoo.mybatis.generator.api.ShellRunner;
import com.roncoo.mybatis.generator.api.dom.java.Field;
import com.roncoo.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import com.roncoo.mybatis.generator.api.dom.java.JavaVisibility;
import com.roncoo.mybatis.generator.api.dom.java.Method;
import com.roncoo.mybatis.generator.api.dom.java.Parameter;
import com.roncoo.mybatis.generator.api.dom.java.TopLevelClass;
import com.roncoo.mybatis.generator.api.dom.xml.Attribute;
import com.roncoo.mybatis.generator.api.dom.xml.TextElement;
import com.roncoo.mybatis.generator.api.dom.xml.XmlElement;
/**
 * <pre>
 * add pagination using mysql limit.
 * This class is only used in ibator code generator.
 * </pre>
 */
public class PaginationPlugin extends PluginAdapter {
	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		addLimit(topLevelClass, introspectedTable, "limitStart");
		addLimit(topLevelClass, introspectedTable, "pageSize");
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}
	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(
			XmlElement element, IntrospectedTable introspectedTable) {
//		XmlElement isParameterPresenteElemen = (XmlElement) element
//				.getElements().get(element.getElements().size() - 1);
		XmlElement ifElement = new XmlElement("if");
		ifElement.addAttribute(new Attribute("test","limitStart >= 0"));
		ifElement.addElement(new TextElement(
				"limit ${limitStart} , ${pageSize}"));
		element.getElements().add(ifElement);
//		XmlElement isNotNullElement = new XmlElement("isGreaterEqual"); //$NON-NLS-1$
//		isNotNullElement.addAttribute(new Attribute("property", "limitStart")); //$NON-NLS-1$ //$NON-NLS-2$
//		isNotNullElement.addAttribute(new Attribute("compareValue", "0")); //$NON-NLS-1$ //$NON-NLS-2$
//		isNotNullElement.addElement(new TextElement(
//				"limit $limitStart$ , $limitEnd$"));
//		isParameterPresenteElemen.addElement(isNotNullElement);
		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element,
				introspectedTable);
	}
	private void addLimit(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(FullyQualifiedJavaType.getIntInstance());
		field.setName(name);
		field.setInitializationString("-1");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(FullyQualifiedJavaType
				.getIntInstance(), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}
	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}
	public static void generate() {
		String config = PaginationPlugin.class.getClassLoader().getResource(
				"mybatisConfig.xml").getFile();
		String[] arg = { "-configfile", config, "-overwrite" };
		ShellRunner.main(arg);
	}
	public static void main(String[] args) {
		generate();
	}
}
