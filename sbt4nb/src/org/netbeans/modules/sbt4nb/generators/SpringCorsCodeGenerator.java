package org.netbeans.modules.sbt4nb.generators;

import java.util.Collections;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Lookup;

public class SpringCorsCodeGenerator implements CodeGenerator {

    JTextComponent textComp;

    private SpringCorsCodeGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    @MimeRegistration(mimeType = "text/x-java",position = 1000, service = CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {

        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new SpringCorsCodeGenerator(context));
        }
    }

    public String getDisplayName() {
        return "Spring CORS";
    }

    String template = "@Bean\n" +
"public FilterRegistrationBean corsFilter() {\n" +
"    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\n" +
"    CorsConfiguration config = new CorsConfiguration();\n" +
"    config.setAllowCredentials(true);\n" +
"    config.addAllowedOrigin(\"*\");\n" +
"    config.addAllowedHeader(\"*\");\n" +
"    config.addAllowedMethod(\"OPTIONS\");\n" +
"    config.addAllowedMethod(\"HEAD\");\n" +
"    config.addAllowedMethod(\"GET\");\n" +
"    config.addAllowedMethod(\"PUT\");\n" +
"    config.addAllowedMethod(\"POST\");\n" +
"    config.addAllowedMethod(\"DELETE\");\n" +
"    config.addAllowedMethod(\"PATCH\");\n" +
"    source.registerCorsConfiguration(\"/**\", config);\n" +
"    // return new CorsFilter(source);\n" +
"    final FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));\n" +
"    bean.setOrder(0);\n" +
"    return bean;\n" +
"}\n" +
"";
    public void invoke() {
        textComp.replaceSelection(template);
    }

}
