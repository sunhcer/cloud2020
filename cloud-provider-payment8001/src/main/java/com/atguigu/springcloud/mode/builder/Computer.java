package com.atguigu.springcloud.mode.builder;

import javafx.util.Builder;

/**
 * 建造者模式
 * @author sunhcer
 * @date 2021/05/29 20:44
 **/
public class Computer {
    //必须为final类型吗?
    private String cpu;
    private String ram;
    private String keyBoard;
    private Integer usbCount;

    private Computer(Builder builder) {
        cpu = builder.cpu;
        ram = builder.ram;
        keyBoard = builder.keyBoard;
        usbCount = builder.usbCount;
    }

//    private Computer(Builder builder) {
//        cpu = builder.cpu;
//        ram = builder.ram;
//        keyBoard = builder.keyBoard;
//        usbCount = builder.usbCount;
//    }

//    //持有静态内部类
//    public static class Builder {
//        private String cpu;
//        private String ram;
//        private String keyBoard;
//        private Integer usbCount;
//
//        //builder构造方法 ,可以限定某个成员变量必带
//        public Builder(String cpu) {
//            this.cpu = cpu;
//        }
//
//
//        public Builder() {
//        }
//
//        public Builder cpu(String val) {
//            cpu = val;
//            return this;
//        }
//
//        public Builder ram(String val) {
//            ram = val;
//            return this;
//        }
//
//        public Builder keyBoard(String val) {
//            keyBoard = val;
//            return this;
//        }
//
//        public Builder usbCount(Integer val) {
//            usbCount = val;
//            return this;
//        }
//
//        public Computer build() {
//            return new Computer(this);
//        }
//    }


    public static final class Builder {
        private String cpu;
        private String ram;
        private String keyBoard;
        private Integer usbCount;

        public Builder() {
        }

        public Builder cpu(String val) {
            cpu = val;
            return this;
        }

        public Builder ram(String val) {
            ram = val;
            return this;
        }

        public Builder keyBoard(String val) {
            keyBoard = val;
            return this;
        }

        public Builder usbCount(Integer val) {
            usbCount = val;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }


    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", keyBoard='" + keyBoard + '\'' +
                ", usbCount=" + usbCount +
                '}';
    }

    public static void main(String[] args) {
        Computer build = new Builder().cpu("Cpu").keyBoard("qeq").ram("qwe").build();
        System.out.println(build.toString());
    }
}
