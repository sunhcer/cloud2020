package com.atguigu.springcloud.mode.proxy;

/**
 * 主体类
 * @author sunhcer
 * @date 2021/01/17 15:46
 **/
public class GamePlayer implements IGamePlayer{

    private String name;

    public GamePlayer(String name) {
        System.out.println("玩家"+name+"进入游戏!!!!!!");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GamePlayer{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public void killBoss() {
        System.out.println("玩家:"+name+" 完成冰霜亚龙首杀-----");
    }

    @Override
    public void upGrade() {
        System.out.println("玩家"+name+" 升到了97级----");
    }
}
