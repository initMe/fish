package com.fish.plugin;

import org.mybatis.generator.api.*;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Document;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mybatis 代码自动生成插件
 * @author fish
 * @date 2016/5/11
 */
public class MybatisGeneratorPlugin extends PluginAdapter{

    public static void generate() {

        String config = MybatisGeneratorPlugin.class.getClassLoader().getResource(
                "generator.xml").getFile();
        String[] arg = {"-configfile", config, "-overwrite"};
        ShellRunner.main(arg);

    }

    public static void main(String[] args) {
        generate();
    }

    /**
     * This plugin is always valid - no properties are required
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        interfaze.addImportedType(new FullyQualifiedJavaType(
                "org.springframework.stereotype.Repository"));
        interfaze.addAnnotation("@Repository");
        return super.clientGenerated(interfaze, topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
                                              IntrospectedTable introspectedTable) {
        /**
         * mybaties自动生成mapper的时候是追加操作,但是我们平常都是删除重新生成,
         * 所以需要扩展删除原来的mapper让mybaties再生成一个
         **/
        List<GeneratedXmlFile> generatedXmlFiles = introspectedTable.getGeneratedXmlFiles();
        for (GeneratedFile generatedFile : generatedXmlFiles) {
            generatedFile.getFormattedContent();

            File project = new File(generatedFile.getTargetProject() + generatedFile.getTargetPackage());
            File file = new File(project, generatedFile.getFileName());
            if (file.exists()) {
                file.delete();
            }
        }


        return super.modelExampleClassGenerated(topLevelClass,
                introspectedTable);
    }

    private void generateToString(IntrospectedTable introspectedTable, TopLevelClass topLevelClass) {

        List<Field> fields = topLevelClass.getFields();
        Map<String, Field> map = new HashMap<String, Field>();
        for (Field field : fields) {
            map.put(field.getName(), field);
        }
        List<IntrospectedColumn> columns = introspectedTable.getAllColumns();
        for (IntrospectedColumn column : columns) {
            Field f = map.get(column.getJavaProperty());
            if (f != null) {
                f.getJavaDocLines().clear();
                if (column.getRemarks() != null) {
                    f.addJavaDocLine("/** " + column.getRemarks() + " */");
//                    if (column.getRemarks().indexOf("@BizId") != -1 || column.getRemarks().indexOf("BizId") != -1
//                            || column.getRemarks().indexOf("bizid") != -1 || column.getRemarks().indexOf("BIZID") != -1) {
//                        f.addAnnotation("@BizId");
//                        topLevelClass.addImportedType(new FullyQualifiedJavaType("com.shufensoft.crm.biz.annotation.BizId"));
//                    }

                }

            }
        }
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document,
                                           IntrospectedTable introspectedTable) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }

    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        generateToString(introspectedTable, topLevelClass);
        return super.modelBaseRecordClassGenerated(topLevelClass, introspectedTable);
    }
}
