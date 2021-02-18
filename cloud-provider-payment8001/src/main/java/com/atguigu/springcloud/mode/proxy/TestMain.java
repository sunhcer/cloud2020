package com.atguigu.springcloud.mode.proxy;

/**
 * 测试代理模式
 * @author sunhcer
 * @date 2021/01/17 15:59
 **/
public class TestMain {

    public static void main(String[] args) {
        GamePlayer sunhcer = new GamePlayer("sunhcer");
        GamePlayerProxy gamePlayerProxy = new GamePlayerProxy(sunhcer);
        gamePlayerProxy.killBoss();
        gamePlayerProxy.upGrade();
    }
}
