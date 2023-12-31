package ru.romanov;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.stereotype.Component;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@Component
public class PromotionsService implements BeanNameAware, BeanFactoryAware, ApplicationListener<ContextClosedEvent> {
    private String beanName;
    private BeanFactory beanFactory;
    @Override
    public String toString(){
        return "PromotionService";
    }

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("Context Event");
    }
}
