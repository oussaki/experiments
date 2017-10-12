package com.oussaki;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

public class NavigiProcessor extends AbstractProcessor {
    private Filer filer;
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Navigi.class)) {
            String action = element.getAnnotation(Navigi.class).action();
            String _class = element.getAnnotation(Navigi.class).target().toString();

            TypeSpec.classBuilder("Navigi")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .addField(TypeName.VOID, "Hello", Modifier.PUBLIC)
                    .build();

        }



        return false;
    }
}
