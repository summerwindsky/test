package designmodel.observe;

import java.util.Observable;
import java.util.Observer;

public class NameObserver implements Observer {
    private String name = null;

    public void update(Observable obj, Object arg) {
        if (arg instanceof String) {
            name = (String) arg;
            System.out.println("名字有变化");
        }
    }

///main用于测试

    public static void main(String[] str) {
        NameObserver nameObserver = new NameObserver();
        Product product = new Product();
        product.addObserver(nameObserver);
//
        product.setName("ccc");

    }

}