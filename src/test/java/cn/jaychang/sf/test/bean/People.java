package cn.jaychang.sf.test.bean;

import cn.jaychang.sf.beans.factory.DisposableBean;
import cn.jaychang.sf.beans.factory.InitializingBean;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class People implements InitializingBean, DisposableBean {
    private String name;
    private Integer age;
    private Car car;

    public People() {
    }

    public People(String name, Integer age, Car car) {
        this.name = name;
        this.age = age;
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }


    public void customInitMethod() {
        System.out.println("People.customInitMethod");
    }

    public void customDestroyMethod() {
        System.out.println("People.customDestroyMethod");
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("People.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("People.init");
    }
}
