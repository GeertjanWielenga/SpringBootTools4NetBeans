package org.netbeans.modules.sbt4nb.generators;

import java.util.Collections;
import java.util.List;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.codegen.CodeGenerator;
import org.openide.util.Lookup;

public class SpringLoadedCodeGenerator implements CodeGenerator {

    JTextComponent textComp;

    private SpringLoadedCodeGenerator(Lookup context) { // Good practice is not to save Lookup outside ctor
        textComp = context.lookup(JTextComponent.class);
    }

    @MimeRegistration(mimeType = "text/x-maven-pom+xml",position = 1000, service = CodeGenerator.Factory.class)
    public static class Factory implements CodeGenerator.Factory {

        public List<? extends CodeGenerator> create(Lookup context) {
            return Collections.singletonList(new SpringLoadedCodeGenerator(context));
        }
    }

    public String getDisplayName() {
        return "Spring Loaded";
    }

    String template = "<dependencies>\n" +
"                    <dependency>\n" +
"                        <groupId>org.springframework</groupId>\n" +
"                        <artifactId>springloaded</artifactId>\n" +
"                        <version>1.2.5.RELEASE</version>\n" +
"                    </dependency>\n" +
"                </dependencies>";
    public void invoke() {
        textComp.replaceSelection(template);
    }

}
