package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
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
}
