package com.example;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;

@SupportedAnnotationTypes("com.example.Tarakha")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class myprocessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        MethodSpec main = MethodSpec.methodBuilder("main")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .returns(void.class)
                .addParameter(String[].class, "args")
                .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
                .addStatement("")
                .build();



        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(main)
                .build();

        JavaFile javaFile = JavaFile.builder("com.example.generated", helloWorld)
                .build();
        try {
            javaFile.writeTo(processingEnv.getFiler());
        } catch (IOException e) {
            e.printStackTrace();
        }


        StringBuilder builder = new StringBuilder()
                .append("package com.example.generated; \n")
                .append("public class GeneratedClass {\n \n") // open class
                .append("  public String getMessage() {\n") // open method
                .append("   return \"");
        // for each javax.lang.model.element.Element annotated with the CustomAnnotation
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Tarakha.class)) {
            String objectType = element.getSimpleName().toString();

            // this is appending to the return statement
            builder.append(objectType).append(" says hello! "+  element.getAnnotation(Tarakha.class).name()
            +", id :"+ element.getAnnotation(Tarakha.class).id());
            System.out.println(objectType+" says hello ");
        }

        builder.append("\"; \n") // end return
                .append("} \n") // close method
                .append("} \n"); // close class

        try { // write the file


            JavaFileObject source = processingEnv.getFiler().createSourceFile("com.example.generated.GeneratedClass");
            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // Note: calling e.printStackTrace() will print IO errors
            // that occur from the file already existing after its first run, this is normal
        }
        return false;
    }
}
