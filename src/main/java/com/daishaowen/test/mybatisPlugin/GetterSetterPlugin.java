package com.daishaowen.test.mybatisPlugin;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * Created by disvenk.dai on 2018-10-16 18:39
 */
public class GetterSetterPlugin extends PluginAdapter {
    public GetterSetterPlugin() {
    }

    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType imp = new FullyQualifiedJavaType("lombok.Data");
        topLevelClass.addImportedType(imp);
        imp = new FullyQualifiedJavaType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addImportedType(imp);
        topLevelClass.addAnnotation("@Data");
        topLevelClass.getMethods().clear();
        return true;
    }

    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    public boolean validate(List<String> arg0) {
        return true;
    }
}
