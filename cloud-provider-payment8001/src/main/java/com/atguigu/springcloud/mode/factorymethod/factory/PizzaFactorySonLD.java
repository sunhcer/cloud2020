package com.atguigu.springcloud.mode.factorymethod.factory;

import com.atguigu.springcloud.mode.factorymethod.Pizza.LDCheesePizza;
import com.atguigu.springcloud.mode.factorymethod.Pizza.LDPepperPizza;
import com.atguigu.springcloud.mode.factorymethod.Pizza.Pizza;

/**
 * PizzaFactorySonLD
 * @author sunhcer
 * @date 2021/01/21 23:49
 **/
public class PizzaFactorySonLD extends PizzaFactory {


    @Override
    Pizza createPizza(String orderType) {

        Pizza pizza = null;
        if(orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        // TODO Auto-generated method stub
        return pizza;
    }

}
