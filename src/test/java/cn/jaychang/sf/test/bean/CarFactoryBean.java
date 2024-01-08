package cn.jaychang.sf.test.bean;

import cn.jaychang.sf.beans.factory.FactoryBean;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class CarFactoryBean implements FactoryBean<Car> {

    private String brandName;

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrandName("byd");
        return car;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
