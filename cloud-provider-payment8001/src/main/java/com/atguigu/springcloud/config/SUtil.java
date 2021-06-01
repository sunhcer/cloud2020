package com.atguigu.springcloud.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * 自定义工具类
 *
 * @author sunhcer.shi
 * @date 2021/05/22 11:43
 **/

public class SUtil {

    public static List<String> getMonthListByQuarter(String quarter) {
        List<String> monthList = new ArrayList<>();
        switch (quarter) {
            case "1":
                monthList = Arrays.asList("1", "2", "3");
                break;
            case "2":
                monthList = Arrays.asList("4", "5", "6");
                break;
            case "3":
                monthList = Arrays.asList("7", "8", "9");
                break;
            case "4":
                monthList = Arrays.asList("10", "11", "12");
                break;
            default:
                break;
        }
        return monthList;
    }

    /**
     * 获取pdf导入数据的结转月份和年份
     * 例如:第一季度来导入去年第3季度的数据,自动生成去年第四季度的数据
     * 导入的数据时间 = 当前季度-2; 生成的数据= 当前季度-1
     *
     * @return
     */
//    public static String getCarryOverSeason() {
//        int season = DateUtils.getSeason(new Date()) - 1;
//        if (season == 0) {
//            season = 4;
//        }
//        return String.valueOf(season);
//    }


    /**
     * 获取pdf导入的结转年份
     *
     * @param season
     * @return
     */
    public static String getCarryOverYear(String season) {
        int year = DateUtil.year(new Date());
        String yearStr = String.valueOf(year);
        if (Integer.parseInt(season) == 4) {
            yearStr = String.valueOf(year - 1);
        }
        return yearStr;
    }

    /**
     * str减
     *
     * @param source
     * @param subValue
     * @return
     */
    public static String StrSub(String source, int subValue) {
        return String.valueOf(Integer.parseInt(source) - subValue);
    }


    /**
     * str加
     *
     * @param source
     * @param addValue
     * @return
     */
    public static String StrAdd(String source, int addValue) {
        return String.valueOf(Integer.parseInt(source) + addValue);
    }

    public static BigDecimal defaultBig(BigDecimal value) {
        return Optional.ofNullable(value)
                .orElse(new BigDecimal(BigInteger.ZERO, 2));
    }


    /**
     * copy每月数据,初始化
     *
     * @param n
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> List<T> ncopies(int n, T t) throws IllegalAccessException, InstantiationException {
        ArrayList<T> ts = new ArrayList<>();
        ts.add(t);
        for (int i = 0; i < n; i++) {
            Object o1 = t.getClass().newInstance();
            BeanUtil.copyProperties(t, o1);
            ts.add((T) o1);
        }
        return ts;
    }

    public static <T> List<T> ifNullDefault(List<T> t) {
        if (CollectionUtils.isEmpty(t)) {
            return new ArrayList<>();
        }
        return t;
    }

    /**
     * 调用copy返回 填充后的实体
     */
    public static <T, V> V copyAndReturn(T source, V target) {
        if (ObjectUtil.isNull(source) || ObjectUtil.isNull(target)) {
//            throw new BizException(ResultEnum.CHECK_FAILED.getCode(), ResultEnum.CHECK_FAILED.getMsg());
        }
        BeanUtil.copyProperties(source, target);
        return target;
    }

}



