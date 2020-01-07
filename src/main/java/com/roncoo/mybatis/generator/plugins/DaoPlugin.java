package com.roncoo.mybatis.generator.plugins;

import java.util.ArrayList;
import java.util.List;

import com.roncoo.mybatis.generator.api.GeneratedJavaFile;
import com.roncoo.mybatis.generator.api.IntrospectedColumn;
import com.roncoo.mybatis.generator.api.IntrospectedTable;
import com.roncoo.mybatis.generator.api.PluginAdapter;
import com.roncoo.mybatis.generator.api.dom.java.Field;
import com.roncoo.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import com.roncoo.mybatis.generator.api.dom.java.Interface;
import com.roncoo.mybatis.generator.api.dom.java.JavaElement;
import com.roncoo.mybatis.generator.api.dom.java.JavaVisibility;
import com.roncoo.mybatis.generator.api.dom.java.Method;
import com.roncoo.mybatis.generator.api.dom.java.Parameter;
import com.roncoo.mybatis.generator.api.dom.java.TopLevelClass;
import com.roncoo.mybatis.generator.internal.util.StringUtility;

/**
 * generate dao class
 */
public class DaoPlugin extends PluginAdapter {

	private String pagePath = "org.mybatis.generator.custom.util.Page";
	private String pageUtilPath = "org.mybatis.generator.custom.util.PageUtil";
	private String idPath = "org.mybatis.generator.custom.util.IdWorker";
	
	private FullyQualifiedJavaType serviceType;
	private FullyQualifiedJavaType daoType;
	private FullyQualifiedJavaType interfaceType;
	private FullyQualifiedJavaType pojoType;
	private FullyQualifiedJavaType pojoCriteriaType;
	private FullyQualifiedJavaType pojoExampleType;
	private FullyQualifiedJavaType listType;
	private FullyQualifiedJavaType pageType;
	private FullyQualifiedJavaType pageUtilType;
	private FullyQualifiedJavaType idType;
	private FullyQualifiedJavaType autowired;
	private FullyQualifiedJavaType service;
	private FullyQualifiedJavaType returnType;
	private String daoPack;
	private String daoImplPack;
	private String project;
	private String pojoUrl;
	/**
	 * 所有的方法
	 */
	private List<Method> methods;
	private boolean enableId = true;
	/**
	 * 是否添加注解
	 */
	private boolean enableAnnotation = true;

	private boolean enableInsert = true;
	private boolean enableInsertSelective = false;
	private boolean enableDeleteByPrimaryKey = false;
	private boolean enableDeleteByExample = false;
	private boolean enableUpdateByExample = false;
	private boolean enableUpdateByExampleSelective = false;
	private boolean enableUpdateByPrimaryKey = false;
	private boolean enableUpdateByPrimaryKeySelective = false;
	private boolean enableSelectByExample = false;
	private boolean enableSelectByPrimaryKey = false;
	private boolean enableCount = false;
	private boolean enablePage = false;

	public DaoPlugin() {
		super();
		methods = new ArrayList<Method>();
	}

	/**
	 * 读取配置文件，初始化
	 */
	@Override
	public boolean validate(List<String> warnings) {

		pagePath = properties.getProperty("pagePath");
		pageUtilPath = properties.getProperty("pageUtilPath");
		idPath = properties.getProperty("idPath");

		String enableId = properties.getProperty("enableId");
		String enableAnnotation = properties.getProperty("enableAnnotation");

		String enableInsert = properties.getProperty("enableInsert");
		String enableInsertSelective = properties.getProperty("enableInsertSelective"); // insert

		String enableDeleteByExample = properties.getProperty("enableDeleteByExample");
		String enableDeleteByPrimaryKey = properties.getProperty("enableDeleteByPrimaryKey"); // deleteById

		String enableUpdateByPrimaryKey = properties.getProperty("enableUpdateByPrimaryKey");
		String enableUpdateByPrimaryKeySelective = properties.getProperty("enableUpdateByPrimaryKeySelective"); // updateById
		String enableUpdateByExample = properties.getProperty("enableUpdateByExample");
		String enableUpdateByExampleSelective = properties.getProperty("enableUpdateByExampleSelective");

		String enableSelectByExample = properties.getProperty("enableSelectByExample");
		String enableSelectByPrimaryKey = properties.getProperty("enableSelectByPrimaryKey"); // selectById
		String enableCount = properties.getProperty("enableCount");
		String enablePage = properties.getProperty("enablePage");

		if (StringUtility.stringHasValue(enableId)) {
			this.enableId = StringUtility.isTrue(enableId);
		}
		if (StringUtility.stringHasValue(enableAnnotation)) {
			this.enableAnnotation = StringUtility.isTrue(enableAnnotation);
		}

		if (StringUtility.stringHasValue(enableInsert)) {
			this.enableInsert = StringUtility.isTrue(enableInsert);
		}

		if (StringUtility.stringHasValue(enableInsertSelective)) {
			this.enableInsertSelective = StringUtility.isTrue(enableInsertSelective);
		}

		if (StringUtility.stringHasValue(enableDeleteByExample)) {
			this.enableDeleteByExample = StringUtility.isTrue(enableDeleteByExample);
		}

		if (StringUtility.stringHasValue(enableDeleteByPrimaryKey)) {
			this.enableDeleteByPrimaryKey = StringUtility.isTrue(enableDeleteByPrimaryKey);
		}

		if (StringUtility.stringHasValue(enableUpdateByPrimaryKey)) {
			this.enableUpdateByPrimaryKey = StringUtility.isTrue(enableUpdateByPrimaryKey);
		}

		if (StringUtility.stringHasValue(enableUpdateByPrimaryKeySelective)) {
			this.enableUpdateByPrimaryKeySelective = StringUtility.isTrue(enableUpdateByPrimaryKeySelective);
		}

		if (StringUtility.stringHasValue(enableUpdateByExample)) {
			this.enableUpdateByExample = StringUtility.isTrue(enableUpdateByExample);
		}

		if (StringUtility.stringHasValue(enableUpdateByExampleSelective)) {
			this.enableUpdateByExampleSelective = StringUtility.isTrue(enableUpdateByExampleSelective);
		}

		if (StringUtility.stringHasValue(enableSelectByExample)) {
			this.enableSelectByExample = StringUtility.isTrue(enableSelectByExample);
		}

		if (StringUtility.stringHasValue(enableSelectByPrimaryKey)) {
			this.enableSelectByPrimaryKey = StringUtility.isTrue(enableSelectByPrimaryKey);
		}
		if (StringUtility.stringHasValue(enableCount)) {
			this.enableCount = StringUtility.isTrue(enableCount);
		}
		if (StringUtility.stringHasValue(enablePage)) {
			this.enablePage = StringUtility.isTrue(enablePage);
		}

		this.daoPack = properties.getProperty("targetPackage");
		this.daoImplPack = properties.getProperty("implementationPackage");
		this.project = properties.getProperty("targetProject");
		this.pojoUrl = context.getJavaModelGeneratorConfiguration().getTargetPackage();

		if (this.enableAnnotation) {
			autowired = new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired");
			service = new FullyQualifiedJavaType("org.springframework.stereotype.Repository");
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
		String table = introspectedTable.getBaseRecordType();
		String tableName = table.replaceAll(this.pojoUrl + ".", "");
		// 【com.roncoo.service.PetDao】
		interfaceType = new FullyQualifiedJavaType(daoPack + "." + tableName + "Dao");

		// 【com.roncoo.mapper.PetMapper】
		daoType = new FullyQualifiedJavaType(introspectedTable.getMyBatis3JavaMapperType());

		// 【com.roncoo.service.impl.PetDaoImpl】
		serviceType = new FullyQualifiedJavaType(daoImplPack + "." + tableName + "DaoImpl");

		// 【com.roncoo.domain.Pet】
		pojoType = new FullyQualifiedJavaType(pojoUrl + "." + tableName);

		// 【com.roncoo.domain.Criteria】
		pojoCriteriaType = new FullyQualifiedJavaType(pojoUrl + "." + tableName + "Criteria");

		// 【com.roncoo.domain.Example】
		pojoExampleType = new FullyQualifiedJavaType(pojoUrl + "." + tableName + "Example");

		// 【java.util.List】
		listType = new FullyQualifiedJavaType("java.util.List");

		// 【com.roncoo.pay.common.custom.bjui.PageBjui】
		pageType = new FullyQualifiedJavaType(pagePath);

		// 【com.roncoo.pay.common.custom.bjui.PageUtil】
		pageUtilType = new FullyQualifiedJavaType(pageUtilPath);
		
		idType = new FullyQualifiedJavaType(idPath);

		Interface interface1 = new Interface(interfaceType);
		TopLevelClass topLevelClass = new TopLevelClass(serviceType);

		// 导入必须的类
		addImport(interface1, topLevelClass);

		// 接口
		addService(interface1, introspectedTable, tableName, files);

		// 实现类
		addServiceImpl(topLevelClass, introspectedTable, tableName, files);

		return files;
	}

	/**
	 * add interface
	 * 
	 * @param tableName
	 * @param files
	 */
	protected void addService(Interface interface1, IntrospectedTable introspectedTable, String tableName, List<GeneratedJavaFile> files) {
		interface1.setVisibility(JavaVisibility.PUBLIC);
		Method method = null;

		if (enableInsert) {
			method = getOtherInsertboolean("", "insert", introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableInsertSelective) {
			method = getOtherInsertboolean("save", "insertSelective", introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableDeleteByExample) {
			method = getOtherInteger("deleteByExample", "deleteByExample", introspectedTable, tableName, 3);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableDeleteByPrimaryKey) {
			method = getOtherInteger("deleteById", "deleteByPrimaryKey", introspectedTable, tableName, 2);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableUpdateByPrimaryKeySelective) {
			method = getOtherInteger("updateById", "updateByPrimaryKeySelective", introspectedTable, tableName, 1);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableUpdateByPrimaryKey) {
			method = getOtherInteger("updateByPrimaryKey", "updateByPrimaryKey", introspectedTable, tableName, 1);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableUpdateByExampleSelective) {
			method = getOtherInteger("updateByExampleSelective", "updateByExampleSelective", introspectedTable, tableName, 4);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableUpdateByExample) {
			method = getOtherInteger("updateByExample", "updateByExample", introspectedTable, tableName, 4);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}

		if (enableSelectByPrimaryKey) {
			method = selectByPrimaryKey("getById", introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableSelectByExample) {
			method = selectByExample("listByExample", introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enableCount) {
			method = countByExample(introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}
		if (enablePage) {
			method = listForPage(introspectedTable, tableName);
			method.removeAllBodyLines();
			interface1.addMethod(method);
		}

		GeneratedJavaFile file = new GeneratedJavaFile(interface1, project, context.getJavaFormatter());
		files.add(file);
	}

	/**
	 * add implements class
	 * 
	 * @param introspectedTable
	 * @param tableName
	 * @param files
	 */
	protected void addServiceImpl(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String tableName, List<GeneratedJavaFile> files) {
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.addSuperInterface(interfaceType); // 实现类

		if (enableAnnotation) {
			topLevelClass.addAnnotation("@Repository");
			topLevelClass.addImportedType(service);
		}

		// add import dao
		addField(topLevelClass, tableName);

		/**
		 * type: pojo 1 ;key 2 ;example 3 ;pojo+example 4
		 */
		if (enableInsert) {
			topLevelClass.addMethod(getOtherInsertboolean("insert", "insert", introspectedTable, tableName));
		}

		if (enableInsertSelective) {
			topLevelClass.addMethod(getOtherInsertboolean("save", "insertSelective", introspectedTable, tableName));
		}

		if (enableDeleteByExample) {
			topLevelClass.addMethod(getOtherInteger("deleteByExample", "deleteByExample", introspectedTable, tableName, 3));
		}

		if (enableDeleteByPrimaryKey) {
			topLevelClass.addMethod(getOtherInteger("deleteById", "deleteByPrimaryKey", introspectedTable, tableName, 2));
		}

		if (enableUpdateByPrimaryKeySelective) {
			topLevelClass.addMethod(getOtherInteger("updateById", "updateByPrimaryKeySelective", introspectedTable, tableName, 1));
		}

		if (enableUpdateByPrimaryKey) {
			topLevelClass.addMethod(getOtherInteger("updateByPrimaryKey", "updateByPrimaryKey", introspectedTable, tableName, 1));
		}

		if (enableUpdateByExampleSelective) {
			topLevelClass.addMethod(getOtherInteger("updateByExampleSelective", "updateByExampleSelective", introspectedTable, tableName, 4));
		}

		if (enableUpdateByExample) {
			topLevelClass.addMethod(getOtherInteger("updateByExample", "updateByExample", introspectedTable, tableName, 4));
		}

		// add method
		if (enableSelectByExample) {
			topLevelClass.addMethod(selectByExample("listByExample", introspectedTable, tableName));
		}

		if (enableSelectByPrimaryKey) {
			topLevelClass.addMethod(selectByPrimaryKey("getById", introspectedTable, tableName));
		}

		if (enableCount) {
			topLevelClass.addMethod(countByExample(introspectedTable, tableName));
		}
		if (enablePage) {
			topLevelClass.addMethod(listForPage(introspectedTable, tableName));
		}

		GeneratedJavaFile file = new GeneratedJavaFile(topLevelClass, project, context.getJavaFormatter());
		files.add(file);
	}

	/**
	 * 添加字段
	 * 
	 * @param topLevelClass
	 */
	protected void addField(TopLevelClass topLevelClass, String tableName) {
		// add dao
		Field field = new Field();
		field.setName(toLowerCase(daoType.getShortName())); // set var name
		topLevelClass.addImportedType(daoType);
		field.setType(daoType); // type
		field.setVisibility(JavaVisibility.PRIVATE);
		if (enableAnnotation) {
			field.addAnnotation("@Autowired");
		}
		topLevelClass.addField(field);
	}

	/**
	 * add method
	 * 
	 */
	protected Method listForPage(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.setName("listForPage");
		method.setReturnType(new FullyQualifiedJavaType("Page<" + tableName + ">"));
		FullyQualifiedJavaType type = new FullyQualifiedJavaType("int");
		method.addParameter(new Parameter(type, "pageCurrent"));
		method.addParameter(new Parameter(type, "pageSize"));
		method.addParameter(new Parameter(pojoExampleType, "example"));
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		sb.append("int count = this.");
		sb.append(getDaoShort());
		sb.append("countByExample(example);");
		method.addBodyLine(sb.toString());
		method.addBodyLine("pageSize = PageUtil.checkPageSize(pageSize);");
		method.addBodyLine("pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);");
		method.addBodyLine("int totalPage = PageUtil.countTotalPage(count, pageSize);");
		method.addBodyLine("example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));");
		method.addBodyLine("example.setPageSize(pageSize);");
		StringBuilder sbs = new StringBuilder();
		sbs.append("return new Page<" + tableName + ">(");
		sbs.append("count, totalPage, pageCurrent, pageSize, this.");
		sbs.append(getDaoShort());
		sbs.append("selectByExample(example));");
		method.addBodyLine(sbs.toString());
		return method;
	}

	/**
	 * add method
	 * 
	 */
	protected Method selectByExample(String methodName1, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.setName(methodName1);
		method.setReturnType(new FullyQualifiedJavaType("List<" + tableName + ">"));
		method.addParameter(new Parameter(pojoExampleType, "example"));
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		sb.append("return this.");
		sb.append(getDaoShort());
		if (introspectedTable.hasBLOBColumns()) {
			sb.append("selectByExampleWithoutBLOBs");
		} else {
			sb.append("selectByExample");
		}
		sb.append("(");
		sb.append("example");
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	/**
	 * 添加方法
	 * 
	 */
	protected Method selectByPrimaryKey(String methodName1, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.setName(methodName1);
		method.setReturnType(pojoType);
		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
			method.addParameter(new Parameter(type, "key"));
		} else {
			for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
				FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
				method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
			}
		}
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		sb.append("return this.");
		sb.append(getDaoShort());
		sb.append("selectByPrimaryKey");
		sb.append("(");
		for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
			sb.append(introspectedColumn.getJavaProperty());
			sb.append(",");
		}
		sb.setLength(sb.length() - 1);
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	/**
	 * add method
	 * 
	 */
	protected Method countByExample(IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.setName("countByExample");
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		method.addParameter(new Parameter(pojoExampleType, "example"));
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		sb.append("return this.");
		sb.append(getDaoShort());
		sb.append("countByExample");
		sb.append("(");
		sb.append("example");
		sb.append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	/**
	 * 
	 * @param methodName1
	 *            方法名
	 * @param methodName
	 *            调用方法名
	 * @param introspectedTable
	 * @param tableName
	 * @param type
	 * @return
	 */
	protected Method getOtherInteger(String methodName1, String methodName, IntrospectedTable introspectedTable, String tableName, int type) {
		Method method = new Method();
		method.setName(methodName1);
		method.setReturnType(FullyQualifiedJavaType.getIntInstance());
		String params = addParams(introspectedTable, method, type);
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		sb.append("return this.");
		sb.append(getDaoShort());
		if (introspectedTable.hasBLOBColumns() && (!"updateByPrimaryKeySelective".equals(methodName) && !"deleteByPrimaryKey".equals(methodName) && !"deleteByExample".equals(methodName) && !"updateByExampleSelective".equals(methodName))) {
			sb.append(methodName + "WithoutBLOBs");
		} else {
			sb.append(methodName);
		}
		sb.append("(").append(params).append(");");
		method.addBodyLine(sb.toString());
		return method;
	}

	/**
	 * add method
	 * 
	 */
	protected Method getOtherInsertboolean(String methodName1, String methodName, IntrospectedTable introspectedTable, String tableName) {
		Method method = new Method();
		method.setName(methodName1);
		method.setReturnType(returnType);
		method.addParameter(new Parameter(pojoType, "record"));
		method.setVisibility(JavaVisibility.PUBLIC);
		StringBuilder sb = new StringBuilder();
		if (returnType == null) {
			sb.append("this.");
		} else {
			sb.append("return this.");
		}
		sb.append(getDaoShort());
		sb.append(methodName);
		sb.append("(").append("record").append(");");
		
		if(enableId) {
			method.addBodyLine("record.setId(IdWorker.getId());");
		}
		
		method.addBodyLine(sb.toString());
		return method;
	}

	/**
	 * type: pojo 1 key 2 example 3 pojo+example 4
	 */
	protected String addParams(IntrospectedTable introspectedTable, Method method, int type1) {
		switch (type1) {
		case 1:
			method.addParameter(new Parameter(pojoType, "record"));
			return "record";
		case 2:
			if (introspectedTable.getRules().generatePrimaryKeyClass()) {
				FullyQualifiedJavaType type = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
				method.addParameter(new Parameter(type, "key"));
			} else {
				for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
					FullyQualifiedJavaType type = introspectedColumn.getFullyQualifiedJavaType();
					method.addParameter(new Parameter(type, introspectedColumn.getJavaProperty()));
				}
			}
			StringBuffer sb = new StringBuffer();
			for (IntrospectedColumn introspectedColumn : introspectedTable.getPrimaryKeyColumns()) {
				sb.append(introspectedColumn.getJavaProperty());
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
			return sb.toString();
		case 3:
			method.addParameter(new Parameter(pojoCriteriaType, "example"));
			return "example";
		case 4:
			method.addParameter(0, new Parameter(pojoType, "record"));
			method.addParameter(1, new Parameter(pojoExampleType, "example"));
			return "record, example";
		case 5:
			method.addParameter(1, new Parameter(pojoExampleType, "example"));
			return "example";
		default:
			break;
		}
		return null;
	}

	protected void addComment(JavaElement field, String comment) {
		StringBuilder sb = new StringBuilder();
		field.addJavaDocLine("/**");
		sb.append(" * ");
		comment = comment.replaceAll("\n", "<br>\n\t * ");
		sb.append(comment);
		field.addJavaDocLine(sb.toString());
		field.addJavaDocLine(" */");
	}

	/**
	 * add field
	 * 
	 * @param topLevelClass
	 */
	protected void addField(TopLevelClass topLevelClass) {
		// add success
		Field field = new Field();
		field.setName("success"); // set var name
		field.setType(FullyQualifiedJavaType.getBooleanPrimitiveInstance()); // type
		field.setVisibility(JavaVisibility.PRIVATE);
		addComment(field, "excute result");
		topLevelClass.addField(field);
		// set result
		field = new Field();
		field.setName("message"); // set result
		field.setType(FullyQualifiedJavaType.getStringInstance()); // type
		field.setVisibility(JavaVisibility.PRIVATE);
		addComment(field, "message result");
		topLevelClass.addField(field);
	}

	/**
	 * add method
	 * 
	 */
	protected void addMethod(TopLevelClass topLevelClass) {
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("setSuccess");
		method.addParameter(new Parameter(FullyQualifiedJavaType.getBooleanPrimitiveInstance(), "success"));
		method.addBodyLine("this.success = success;");
		topLevelClass.addMethod(method);

		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getBooleanPrimitiveInstance());
		method.setName("isSuccess");
		method.addBodyLine("return success;");
		topLevelClass.addMethod(method);

		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("setMessage");
		method.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "message"));
		method.addBodyLine("this.message = message;");
		topLevelClass.addMethod(method);

		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(FullyQualifiedJavaType.getStringInstance());
		method.setName("getMessage");
		method.addBodyLine("return message;");
		topLevelClass.addMethod(method);
	}

	/**
	 * add method
	 * 
	 */
	protected void addMethod(TopLevelClass topLevelClass, String tableName) {
		Method method2 = new Method();
		for (int i = 0; i < methods.size(); i++) {
			Method method = new Method();
			method2 = methods.get(i);
			method = method2;
			method.removeAllBodyLines();
			method.removeAnnotation();
			StringBuilder sb = new StringBuilder();
			sb.append("return this.");
			sb.append(getDaoShort());
			sb.append(method.getName());
			sb.append("(");
			List<Parameter> list = method.getParameters();
			for (int j = 0; j < list.size(); j++) {
				sb.append(list.get(j).getName());
				sb.append(",");
			}
			sb.setLength(sb.length() - 1);
			sb.append(");");
			method.addBodyLine(sb.toString());
			topLevelClass.addMethod(method);
		}
		methods.clear();
	}

	/**
	 * BaseUsers to baseUsers
	 * 
	 * @param tableName
	 * @return
	 */
	protected String toLowerCase(String tableName) {
		StringBuilder sb = new StringBuilder(tableName);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * BaseUsers to baseUsers
	 * 
	 * @param tableName
	 * @return
	 */
	protected String toUpperCase(String tableName) {
		StringBuilder sb = new StringBuilder(tableName);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}

	/**
	 * import must class
	 */
	private void addImport(Interface interfaces, TopLevelClass topLevelClass) {
		// 接口类
		if (enableSelectByExample) {
			interfaces.addImportedType(listType);
			interfaces.addImportedType(pojoExampleType);
		}
		if (enablePage) {
			interfaces.addImportedType(pojoExampleType);
			interfaces.addImportedType(pageType);
		}
		interfaces.addImportedType(pojoType);

		// 实现类
		if (enableSelectByExample) {
			topLevelClass.addImportedType(listType);
			topLevelClass.addImportedType(pojoExampleType);
		}
		if (enablePage) {
			topLevelClass.addImportedType(pojoExampleType);
			topLevelClass.addImportedType(pageType);
			topLevelClass.addImportedType(pageUtilType);
		}
		if(enableId) {
			topLevelClass.addImportedType(idType);
		}
		topLevelClass.addImportedType(daoType); // mapper
		topLevelClass.addImportedType(interfaceType);
		topLevelClass.addImportedType(pojoType);

		if (enableAnnotation) {
			topLevelClass.addImportedType(service);
			topLevelClass.addImportedType(autowired);
		}
	}

	/**
	 * import logger
	 */
	protected void addLogger(TopLevelClass topLevelClass) {
		Field field = new Field();
		field.setFinal(true);
		field.setInitializationString("LoggerFactory.getLogger(" + topLevelClass.getType().getShortName() + ".class)"); // set value
		field.setName("logger"); // set var name
		field.setStatic(true);
		field.setType(new FullyQualifiedJavaType("Logger")); // type
		field.setVisibility(JavaVisibility.PRIVATE);
		topLevelClass.addField(field);
	}

	private String getDaoShort() {
		return toLowerCase(daoType.getShortName()) + ".";
	}

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze, IntrospectedTable introspectedTable) {
		returnType = method.getReturnType();
		return true;
	}
}
