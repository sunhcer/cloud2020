package com.atguigu.springcloud.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.CodeLibrary;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(Payment payment) {
        int i = paymentDao.create(payment);
//        int j=1/0;
        return i;
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public List<Payment> getPaymentList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Payment> list = paymentDao.findAllPayment();
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCountry(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("读取的值"+substring);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String countryNameNew = split[i];
            String[] split1 = countryNameNew.split("\\|");
            String itemNo = split1[0];
            String countrynameOld = split1[1];
            paymentDao.updateCountry(itemNo,countryNameNew,countrynameOld);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sortCountry(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("读取的值"+substring);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String countryNameNew = split[i];
            String[] split1 = countryNameNew.split("\\|");
            String itemNo = split1[0];
            String countrynameOld = split1[1];
            Integer sortNo=i+1;
            int num=paymentDao.sortCountry(itemNo,countryNameNew,sortNo);
            if(num==0){
                log.info("失败的记录:"+i+JSONUtil.toJsonStr(countryNameNew));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCertificate(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("证件----读取的值: "+substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:"+split.length);
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String certificateNameNeed = split1[1];
            //获取excel要求的
            String type="TP_DocumentType";
            log.info("更新第"+i+"条:"+ JSONUtil.toJsonStr(split1));
            int num= paymentDao.updateCertificate(itemNoNeed,certificateNameNeed,type);
            log.info("更新第"+i+"条结果:"+num);
            if(num==0){
                log.info("失败的记录:"+i+JSONUtil.toJsonStr(split1));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sortCertificate(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("证件----读取的值: "+substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:"+split.length);
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String certificateNameNeed = split1[1];
            //获取excel要求的
            String type="TP_DocumentType";
            log.info("更新第"+i+"条:"+ JSONUtil.toJsonStr(split1));
            Integer sortNo=i+1;
            int num= paymentDao.sortCertificate(itemNoNeed,certificateNameNeed,sortNo);
            log.info("更新第"+i+"条结果:"+num);

        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeCoin(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("币种----读取的值: "+substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:"+split.length);
        List list=new ArrayList();
        list.add("克瓦查");
        list.add("俄罗斯卢布");
        Map map=new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String coinNameNeed = split1[1];
            //获取excel要求的
            String type="currencyCode";
            log.info("更新第"+i+"条:"+ JSONUtil.toJsonStr(split1));
            //如果是有重复的条数 先跳过 后面手动处理
            if (list.contains(coinNameNeed)){
                log.info("定位到:"+coinNameNeed+"----------跳过");
                continue;
            }
            Integer sortNo=i+1;
            int num= paymentDao.updateCoin(itemNoNeed,coinNameNeed,type,sortNo);
            log.info("更新第"+i+"条结果:"+num);
            if (num==0){
                map.put(i,"失败"+JSONUtil.toJsonStr(split1));
            }
        }
        log.info("失败集合:"+JSONUtil.toJsonStr(map));
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeAndSortExchange(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length()-1);
        System.out.println("交易所----读取的值: "+substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:"+split.length);
//        List list=new ArrayList();
//        list.add("克瓦查");
//        list.add("俄罗斯卢布");
        Map map=new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String coinNameNeed = split1[1];
            //获取excel要求的
            String type="ListedExchange";
            log.info("更新第"+i+"条:"+ JSONUtil.toJsonStr(split1));
            //如果是有重复的条数 先跳过 后面手动处理
//            if (list.contains(coinNameNeed)){
//                log.info("定位到:"+coinNameNeed+"----------跳过");
//                continue;
//            }
            Integer sortNo=i+1;
            int num= paymentDao.updateExchange(itemNoNeed,coinNameNeed,type,sortNo);
            log.info("更新第"+i+"条结果:"+num);
            if (num==0){
                map.put(i,"失败"+JSONUtil.toJsonStr(split1));
            }
        }
        log.info("失败集合:"+JSONUtil.toJsonStr(map));
        return 0;
    }
}
