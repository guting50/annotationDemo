package com.gt.compiler;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

public class ProxyInfo {
    private String packageName;

    private String proxyClassName;
    private TypeElement typeElement;

    public static final String PROXY = "Gt";

    public ProxyInfo(Elements elementUtils, TypeElement classElement) {
        this.typeElement = classElement;
        PackageElement packageElement = elementUtils.getPackageOf(classElement);
        String packageName = packageElement.getQualifiedName().toString();
        String className = ClassValidator.getClassName(classElement, packageName);
        this.packageName = packageName;
        this.proxyClassName = className + "$$" + PROXY;
    }

    public String generateJavaCode() {
        StringBuilder builder = new StringBuilder();
        builder.append("// Generated code. Do not modify!\n");
        builder.append("package ").append(packageName).append(";\n\n");
        builder.append("import android.util.Log;\n");
        builder.append('\n');
        builder.append("public class ").append(proxyClassName);
//        builder.append("public class ").append(proxyClassName).append(" implements " + ProxyInfo.PROXY + "<" + typeElement.getQualifiedName() + ">");
        builder.append(" {\n");

        generateAddTable(builder);

        builder.append('\n');
        builder.append("}\n");
        return builder.toString();
    }

    private void generateAddTable(StringBuilder builder) {
//        builder.append("@Override\n ");
        builder.append("public static void init() {\n");
        builder.append("Log.e(\"" + typeElement.getQualifiedName() + ".class\",\"hello word\");\n");
        builder.append("}");
    }

    public String getProxyClassFullName() {
        return packageName + "." + proxyClassName;
    }

    public TypeElement getTypeElement() {
        return typeElement;
    }

}