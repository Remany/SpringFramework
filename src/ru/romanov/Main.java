package ru.romanov;

import org.springframework.beans.factory.BeanFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.instantiate("ru.romanov");
    }
}