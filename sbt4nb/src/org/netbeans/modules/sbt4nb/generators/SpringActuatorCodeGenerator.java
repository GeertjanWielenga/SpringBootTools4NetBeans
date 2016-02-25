package org.netbeans.modules.sbt4nb.generators;

import java.util.Collections;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Lookup;

public class SpringActuatorCodeGenerator implements CodeGenerator {

    JTextComponent textComp;

    private SpringActuatorCodeGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    @MimeRegistration(mimeType = "text/x-maven-pom+xml",position = 950, service = CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {

        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new SpringActuatorCodeGenerator(context));
        }
    }

    public String getDisplayName() {
        return "Spring Actuator";
    }

    String template = "<dependency>\n" +
"            <groupId>org.springframework.boot</groupId>\n" +
"            <artifactId>spring-boot-starter-actuator</artifactId>\n" +
"            <version>1.3.2.RELEASE</version>\n" +
"        </dependency>";
    public void invoke() {
        textComp.replaceSelection(template);
    }

}
