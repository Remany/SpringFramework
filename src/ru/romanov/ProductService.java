package ru.romanov;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.stereotype.Component;

import javax.annotations.PostConstruct;
import javax.annotations.PreDestroy;

@Component
public class ProductService {
    @Autowired
    private PromotionsService promotionsService;

    public PromotionsService getPromotionsService(){
        return promotionsService;
    }

    public void setPromotionsService(PromotionsService promotionsService) {
        this.promotionsService = promotionsService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Hello from init method");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Hello from destroy method");
    }

    @Override
    public String toString() {
        return "ProductService{" +
                "promotionsService=" + promotionsService +
                '}';
    }
}
