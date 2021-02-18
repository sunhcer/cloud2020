package com.atguigu.springcloud.mode.factorymethod.factory;

import com.atguigu.springcloud.mode.factorymethod.Pizza.BJCheesePizza;
import com.atguigu.springcloud.mode.factorymethod.Pizza.BJPepperPizza;
import com.atguigu.springcloud.mode.factorymethod.Pizza.Pizza;

/**
 * 子工厂
 * @author sunhcer
 * @date 2021/01/21 23:46
 **/
public class PizzaFactorySonBJ extends PizzaFactory {


    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new BJCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new BJPepperPizza();
        }
        // TODO Auto-generated method stub
        return pizza;
    }

}
