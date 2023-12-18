package cn.jaychang.sf.test.bean;

/**
 * @author jaychang
 * @description TODO
 * @date 2023/12/14
 **/
public class Car {
    private String brandName;

    public Car() {
    }

    public Car(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brandName='" + brandName + '\'' +
                '}';
    }
}
