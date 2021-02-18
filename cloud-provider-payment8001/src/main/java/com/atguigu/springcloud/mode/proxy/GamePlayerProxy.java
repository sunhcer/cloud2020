package com.atguigu.springcloud.mode.proxy;

/**
 * 代理类
 * @author sunhcer
 * @date 2021/01/17 15:51
 **/
public class GamePlayerProxy implements IGamePlayer {

    private GamePlayer gamePlayer;

    public GamePlayerProxy(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public void log(){
        System.out.print("!!!!!!!!!!!!!!!全服广播:" );
    }

    @Override
    public void killBoss() {
        gamePlayer.killBoss();
        log();
        gamePlayer.killBoss();
    }

    @Override
    public void upGrade() {
        gamePlayer.upGrade();
        log();
        gamePlayer.upGrade();
    }
}
