package cn.jaychang.sf.aop;

public class WorldServiceImpl implements WorldService{

    public String explode() {
        System.out.println("WorldServiceImpl.explode");
        return "Something will not explode.";
    }
}
