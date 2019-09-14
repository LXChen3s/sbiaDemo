package com.sbia.sbiademo.tools.mybatis_generator;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.util.Properties;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

public class MyGeneratorComment extends DefaultCommentGenerator{
	private boolean suppressAllComments;
	private boolean addRemarkComments;
	
	public void addConfigurationProperties(Properties properties) {
        super.addConfigurationProperties(properties);
        
        suppressAllComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));

        addRemarkComments = isTrue(properties
                .getProperty(PropertyRegistry.COMMENT_GENERATOR_ADD_REMARK_COMMENTS));
    }
	
	public void addFieldComment(Field field,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }

        field.addJavaDocLine("/**"); 
        //获取数据库字段备注信息
        String remarks = introspectedColumn.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));  //$NON-NLS-1$
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" *   " + remarkLine);  //$NON-NLS-1$
            }
        }
        //获取数据库字段名
        field.addJavaDocLine(" *   "+introspectedColumn.getActualColumnName()); 
        field.addJavaDocLine(" */"); 
    }
	
	public void addClassComment(InnerClass innerClass,
            IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        innerClass.addJavaDocLine("/**"); 
        innerClass.addJavaDocLine(" *   "+introspectedTable.getFullyQualifiedTable());
        innerClass.addJavaDocLine(" */"); 
    }
	
	public void addModelClassComment(TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {
        if (suppressAllComments  || !addRemarkComments) {
            return;
        }

        topLevelClass.addJavaDocLine("/**"); 

        String remarks = introspectedTable.getRemarks();
        if (addRemarkComments && StringUtility.stringHasValue(remarks)) {
            topLevelClass.addJavaDocLine(" * Database Table Remarks:");
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));  
            for (String remarkLine : remarkLines) {
                topLevelClass.addJavaDocLine(" *   " + remarkLine);  
            }
        }
        topLevelClass.addJavaDocLine(" *   "+introspectedTable.getFullyQualifiedTable()+"表");
        addJavadocTag(topLevelClass, true);

        topLevelClass.addJavaDocLine(" */"); 
    }
	
	public void addGeneralMethodComment(Method method,
            IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
    }
	
	public void addGetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
    }
	
	public void addSetterComment(Method method,
            IntrospectedTable introspectedTable,
            IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
    }
	
	protected void addJavadocTag(JavaElement javaElement,
            boolean markAsDoNotDelete) {
    }
	
	public void addComment(XmlElement xmlElement) {
        if (suppressAllComments) {
            return;
        }
    }
	
}
