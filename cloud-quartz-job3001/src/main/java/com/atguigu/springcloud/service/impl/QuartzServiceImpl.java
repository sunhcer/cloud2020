package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.QuartzService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * service
 * @author sunhcer
 * @date 2021/02/06 13:01
 **/
@Service
public class QuartzServiceImpl implements QuartzService {

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
}
