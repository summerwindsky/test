package designmodel.observe;

import java.util.Observable;

public class Product extends Observable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        ///下面两段代码用于监控
        setChanged();
        notifyObservers(name);
    }
}