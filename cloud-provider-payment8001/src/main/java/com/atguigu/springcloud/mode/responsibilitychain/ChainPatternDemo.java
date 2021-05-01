package com.atguigu.springcloud.mode.responsibilitychain;

/**
 * 记录器链条
 * @author sunhcer.shi
 * @date 2021/04/15 16:09
 **/

public class ChainPatternDemo {

    private static Abstractlogger getChainOfLoggers(){
        Abstractlogger errorLogger = new ErrorLogger(Abstractlogger.ERROR);
        Abstractlogger fileLogger = new FileLogger(Abstractlogger.DEBUG);
        Abstractlogger consoleLogger = new ConsoleLogger(Abstractlogger.INFO);
        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);
        return errorLogger;
    }

    public static void main(String[] args) {
        //内存
        Abstractlogger loggerChain=getChainOfLoggers();
        loggerChain.logMessage(Abstractlogger.INFO, "info级别.");
        loggerChain.logMessage(Abstractlogger.DEBUG,"debug级别.");
        loggerChain.logMessage(Abstractlogger.ERROR,"error级别.");

    }

}
