/*
 *  Copyright 2012 The MyBatis Team
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

import com.roncoo.mybatis.generator.api.dom.java.Interface;
import com.roncoo.mybatis.generator.codegen.AbstractXmlGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedDeleteByPrimaryKeyMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedInsertMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectAllMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedSelectByPrimaryKeyMethodGenerator;
import com.roncoo.mybatis.generator.codegen.mybatis3.javamapper.elements.annotated.AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator;

/**
 * @author Jeff Butler
 * 
 */
public class SimpleAnnotatedClientGenerator extends SimpleJavaClientGenerator {

    /**
     * 
     */
    public SimpleAnnotatedClientGenerator() {
        super(false);
    }

    @Override
    protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateDeleteByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedDeleteByPrimaryKeyMethodGenerator(true);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addInsertMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateInsert()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedInsertMethodGenerator(true);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateSelectByPrimaryKey()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectByPrimaryKeyMethodGenerator(false, true);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    protected void addSelectAllMethod(Interface interfaze) {
        AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedSelectAllMethodGenerator();
        initializeAndExecuteGenerator(methodGenerator, interfaze);
    }

    @Override
    protected void addUpdateByPrimaryKeyMethod(Interface interfaze) {
        if (introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
            AbstractJavaMapperMethodGenerator methodGenerator = new AnnotatedUpdateByPrimaryKeyWithoutBLOBsMethodGenerator(true);
            initializeAndExecuteGenerator(methodGenerator, interfaze);
        }
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return null;
    }
}
