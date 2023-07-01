package org.springframework.beans.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.beans.factory.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class BeanFactory {
    private Map<String, Object> singletons = new HashMap<>();
    public Object getBean(String beanName){
        return singletons.get(beanName);
    }

    private void newBean(String className, Class classObject) throws InstantiationException, IllegalAccessException {
        Object instance = classObject.newInstance();
        String beanName = className.substring(0, 1).toLowerCase() + className.substring(1);
        singletons.put(beanName, instance);
    }

    public void instantiate(String basePackage) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        String path = basePackage.replace(".", "/"); // ru.romanov -> ru/romanov
        Enumeration<URL> resources = classLoader.getResources(path);
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            File file = new File(resource.toURI());
            for (File classFile : file.listFiles()) {
                String fileName = classFile.getName(); // ProductService.class
                if (fileName.endsWith(".class")) {
                    String className = fileName.substring(0, fileName.lastIndexOf("."));
                    Class classObject = Class.forName(basePackage + "." + className);
                    if (classObject.isAnnotationPresent(Component.class)){
                        System.out.println("Component: " + classObject);
                        newBean(className, classObject);
                    }
                    if (classObject.isAnnotationPresent(Service.class)){
                        System.out.println("Service: " + classObject);
                        newBean(className, classObject);
                    }
                }
            }
        }
    }

    public void populateProperties(){
        System.out.println("==populateProperties==");
        for (Object object: singletons.values()) {
            for (Field field: object.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    for (Object dependency: singletons.values()) {
                        if (dependency.getClass().equals(field.getType())) {

                        }
                    }
                }
            }
        }
    }

}