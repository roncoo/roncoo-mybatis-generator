/*
 *  Copyright 2010 The MyBatis Team
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.roncoo.mybatis.generator.codegen.mybatis3.javamapper;

import static com.roncoo.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import com.roncoo.mybatis.generator.api.CommentGenerator;
import com.roncoo.mybatis.generator.api.dom.java.CompilationUnit;
import com.roncoo.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import com.roncoo.mybatis.generator.api.dom.java.JavaVisibility;
import com.roncoo.mybatis.generator.api.dom.java.TopLevelClass;
import com.roncoo.mybatis.generator.codegen.AbstractJavaGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.AbstractJavaProviderMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderApplyWhereMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderCountByExampleMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderDeleteByExampleMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderInsertSelectiveMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByExampleWithBLOBsMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderSelectByExampleWithoutBLOBsMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleSelectiveMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleWithBLOBsMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByExampleWithoutBLOBsMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.sqlprovider.ProviderUpdateByPrimaryKeySelectiveMethodGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class SqlProviderGenerator extends AbstractJavaGenerator {

    public SqlProviderGenerator() {
        super();
    }

    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.18", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getMyBatis3SqlProviderType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        boolean addApplyWhereMethod = false;
        addApplyWhereMethod |= addCountByExampleMethod(topLevelClass);
        addApplyWhereMethod |= addDeleteByExampleMethod(topLevelClass);
        addInsertSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByExampleWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addSelectByExampleWithoutBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleSelectiveMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleWithBLOBsMethod(topLevelClass);
        addApplyWhereMethod |= addUpdateByExampleWithoutBLOBsMethod(topLevelClass);
        addUpdateByPrimaryKeySelectiveMethod(topLevelClass);

        if (addApplyWhereMethod) {
            addApplyWhereMethod(topLevelClass);
        }
        
        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        
        if (topLevelClass.getMethods().size() > 0) {
            if (context.getPlugins().providerGenerated(topLevelClass,
                introspectedTable)) {
                answer.add(topLevelClass);
            }
        }

        return answer;
    }

    protected boolean addCountByExampleMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateCountByExample()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderCountByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addDeleteByExampleMethod(TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateDeleteByExample()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderDeleteByExampleMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addInsertSelectiveMethod(TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateInsertSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderInsertSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected boolean addSelectByExampleWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByExampleWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addSelectByExampleWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderSelectByExampleWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleSelectiveMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleSelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleSelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleWithBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleWithBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected boolean addUpdateByExampleWithoutBLOBsMethod(
            TopLevelClass topLevelClass) {
        boolean rc = false;
        if (introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByExampleWithoutBLOBsMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
            rc = true;
        }

        return rc;
    }

    protected void addUpdateByPrimaryKeySelectiveMethod(
            TopLevelClass topLevelClass) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaProviderMethodGenerator methodGenerator = new ProviderUpdateByPrimaryKeySelectiveMethodGenerator();
            initializeAndExecuteGenerator(methodGenerator, topLevelClass);
        }
    }

    protected void addApplyWhereMethod(TopLevelClass topLevelClass) {
        AbstractJavaProviderMethodGenerator methodGenerator = new ProviderApplyWhereMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, topLevelClass);
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaProviderMethodGenerator methodGenerator,
            TopLevelClass topLevelClass) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addClassElements(topLevelClass);
    }
}
