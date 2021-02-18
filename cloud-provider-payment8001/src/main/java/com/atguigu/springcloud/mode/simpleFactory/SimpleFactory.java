package com.atguigu.springcloud.mode.simpleFactory;

import com.atguigu.springcloud.mode.simpleFactory.vo.CheesePizza;
import com.atguigu.springcloud.mode.simpleFactory.vo.GreekPizza;
import com.atguigu.springcloud.mode.simpleFactory.vo.PepperPizza;
import com.atguigu.springcloud.mode.simpleFactory.vo.Pizza;

/**
 * 简单工厂
 * @author sunhcer
 * @date 2021/01/21 01:26
 **/
public class SimpleFactory {
    public Pizza createPizza(String orderType) {


        Pizza pizza = null;

        System.out.println("使用简单工厂模式"); if (orderType.equals("greek")) {
            pizza = new GreekPizza();
            pizza.setName(" 希腊披萨 ");
        } else if (orderType.equals("cheese")) { pizza = new CheesePizza();
            pizza.setName(" 奶酪披萨 ");
        } else if (orderType.equals("pepper")) { pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }


        return pizza;
    }

}
