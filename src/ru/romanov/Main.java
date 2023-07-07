package ru.romanov;

import org.springframework.beans.factory.BeanFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException,
            ClassNotFoundException, InstantiationException, IllegalAccessException,
            InvocationTargetException, NoSuchMethodException {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.addPostProcessor(new CommonAnnotationBeanPostProcessor());
        beanFactory.instantiate("ru.romanov");
        beanFactory.populateProperties();
        beanFactory.injectBeanNames();
        beanFactory.injectBeanFactory();
        beanFactory.initializeBean();
        beanFactory.close();
    }
}