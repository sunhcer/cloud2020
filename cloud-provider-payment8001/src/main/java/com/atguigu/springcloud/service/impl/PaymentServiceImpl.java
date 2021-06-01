package com.atguigu.springcloud.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.atguigu.springcloud.config.BeanFinder;
import com.atguigu.springcloud.config.DemoDataListener;
import com.atguigu.springcloud.dao.CodeLibraryMapper;
import com.atguigu.springcloud.dao.PaymentDao;
import com.atguigu.springcloud.entities.CodeLibrary;
import com.atguigu.springcloud.entities.EntCodeMappingEntity;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.CodeLibraryService;
import com.atguigu.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private BeanFinder beanFinder;
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
        PageHelper.startPage(pageNum, pageSize);
        List<Payment> list = paymentDao.findAllPayment();
        return list;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCountry(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String countryNameNew = split[i];
            String[] split1 = countryNameNew.split("\\|");
            String itemNo = split1[0];
            String countrynameOld = split1[1];
            paymentDao.updateCountry(itemNo, countryNameNew, countrynameOld);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCountryEng(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String countryNameNew = split[i];
            String[] split1 = countryNameNew.split("\\|");
            String itemNo = split1[0];
            String code1 = split1[1];
            String codeNo = "TP_Nationality";
            int num = paymentDao.addCountryEng(itemNo, codeNo, code1);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(split1));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeContryForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Nationality";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\t");
            String hmItemName = split1[0];
            String itemName = split1[1];
            String[] split2 = itemName.split("\\|");
            String itemNo = split2[0];
            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibrary(hmCodeNo, hmItemName);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4403");
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("0");//客户端
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int supplementContryForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Nationality";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\t");
            String hmItemName = split1[1];
            String itemName = split1[1];
            String[] split2 = itemName.split("\\|");
            String itemNo = split2[0];
            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibrary(hmCodeNo, hmItemName);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4403");
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("0");//客户端
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDCountryForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "TP_Nationality";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\\|");
            String hmItemName = split1[1];
            String itemName = row;
            String itemNo = split1[0];
            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibrary(hmCodeNo, hmItemName);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDTPcoinsForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "TP_currencyCode";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\t");

            String itemName = split1[1];
            String itemNo = split1[0];

            if ("俄罗斯卢布".equals(itemName)) {
                log.error("跳过重复: " + itemName);
                continue;
            }
            if ("克瓦查".equals(itemName)) {
                log.error("跳过重复: " + itemName);
                continue;
            }

            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibrary(hmCodeNo, itemName);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + "itemname:" + itemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            if (codeLibrary == null) {
                log.error("名字未匹配到 , 跳过: " + "第" + i + "条:" + "itemname:" + itemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
                continue;
            }

            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDTPIndustryForMapping(StringBuilder stringBuilder) {

        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "TP_Industry";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\\|");

            String itemName = split1[1];
            String itemNo = split1[0];

            String likeItemNo = split1[0];
//            if (likeItemNo.startsWith("0")){
//                likeItemNo=likeItemNo.substring(1);
//            }


            //根据 itemName和 hmcodeNo找到 item_no
//            CodeLibrary codeLibrary=paymentDao.queryCodeLibrary2(hmCodeNo,likeItemNo,itemName);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
//            log.info("第"+i+"条:"+ "hmcode:"+hmCodeNo+ "itemname:"+itemName +"itemNo:"+itemNo+" 查询结果:"+JSONUtil.toJsonStr(codeLibrary));
//            if(codeLibrary==null){
//                log.error("名字未匹配到 , 跳过: "+ "第"+i+"条:"+ "hmcode:"+hmCodeNo+ "itemname:"+itemName +"itemNo:"+itemNo+" 查询结果:"+JSONUtil.toJsonStr(codeLibrary));
//                continue;
//            }

            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(itemNo);
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDCITCountryForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Nationality";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split("\t");
            String hmItemName = split1[0];
            String itemName = split1[1];
            String itemNo = split1[1];
            //根据 itemName和 hmcodeNo找到 item_no
//            CodeLibrary codeLibrary=paymentDao.queryCodeLibrary(hmCodeNo,hmItemName);
            CodeLibrary codeLibrary = paymentDao.queryLikeNameCodeLibrary(hmCodeNo, hmItemName);
            if ("孟加拉国".equals(hmItemName)) {
                log.error("手动插入:" + hmItemName + " 次序:" + i);
                continue;
            }
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDCITCountryDiffForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Nationality";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String[] split1 = row.split(" ");
            String hmItemName = split1[0].trim();
            String itemName = split1[1].trim();
            String itemNo = split1[1].trim();
            //根据 itemName和 hmcodeNo找到 item_no
//            CodeLibrary codeLibrary=paymentDao.queryCodeLibrary(hmCodeNo,hmItemName);
            CodeLibrary codeLibrary = paymentDao.queryLikeNameCodeLibrary(hmCodeNo, hmItemName);
            if ("孟加拉国".equals(hmItemName)) {
                log.error("手动插入:" + hmItemName + " 次序:" + i);
                continue;
            }
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGDCITCertifyForMapping(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "DocumentType";
        for (int i = 0; i < split.length; i++) {
            String row = split[i];
            String hmItemName = row;
            String itemName = row;
            String itemNo = row;
            //根据 itemName和 hmcodeNo找到 item_no
//            CodeLibrary codeLibrary=paymentDao.queryCodeLibrary(hmCodeNo,hmItemName);
            CodeLibrary codeLibrary = paymentDao.queryLikeNameCodeLibrary(hmCodeNo, hmItemName);
//            if ("孟加拉国".equals(hmItemName)){
//                log.error("手动插入:"+hmItemName+" 次序:"+i);
//                continue;
//            }
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4400");//广东
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//网页
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
            }
        }

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeIndustryForMapping(StringBuilder stringBuilder) {

        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Industry";
        for (int i = 0; i < split.length; i++) {
            if (i == 1216) {
                continue;
            }
            String row = split[i];
            String[] split1 = row.split("\t");
            String hmItemName = split1[1].trim();//0和华盟的对不上,要查一次拿到华盟对应的itemno

            String HmLikeItemNo = split1[0].trim();
            String itemNo = split1[2].trim();
            String itemName = split1[2].trim() + "|" + split1[3].trim();
            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibraryForIndustry(hmCodeNo, hmItemName, HmLikeItemNo);
            if (codeLibrary == null) {
                log.error("hmCodeNo:" + hmCodeNo + " hmItemName:" + hmItemName + "HmLikeItemNo" + HmLikeItemNo + "没查到记录跳过");
                continue;
            }
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4403");
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("0");//客户端
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            if (itemNo != "S9233") {
                int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
                log.info("成功的条数:" + num);
                if (num == 0) {
                    log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
                }
            }
        }
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeGDCITIndustryForMapping(StringBuilder stringBuilder) {

        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        String hmCodeNo = "Industry";
        for (int i = 0; i < split.length; i++) {
//            if (i==1216){
//                continue;
//            }
            String row = split[i];
            String[] split1 = row.split("\t");
            String hmItemName = split1[1].trim();//0和华盟的对不上,要查一次拿到华盟对应的itemno

            String HmLikeItemNo = split1[0].trim();
            String gdtrim = split1[2].trim();
            String[] split2 = gdtrim.split("\\|");
            String itemNo = split2[0].trim();
            String itemName = gdtrim;
            //根据 itemName和 hmcodeNo找到 item_no
            CodeLibrary codeLibrary = paymentDao.queryCodeLibraryForIndustry(hmCodeNo, hmItemName, HmLikeItemNo);
            if (codeLibrary == null) {
                log.error("hmCodeNo:" + hmCodeNo + " hmItemName:" + hmItemName + "HmLikeItemNo" + HmLikeItemNo + "没查到记录跳过");
                continue;
            }
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            log.info("第" + i + "条:" + "hmcode:" + hmCodeNo + " hmname:" + hmItemName + " 查询结果:" + JSONUtil.toJsonStr(codeLibrary));
            entCodeMappingEntity.setCityCode("4400");
            entCodeMappingEntity.setHmCodeNo(hmCodeNo);
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setCreateTime(DateUtil.formatDateTime(new Date()));
            entCodeMappingEntity.setDeclareMethod("1");//客户端
            entCodeMappingEntity.setItemName(itemName);
            entCodeMappingEntity.setItemNo(itemNo);
            if (itemNo != "S9233") {
                int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
                log.info("成功的条数:" + num);
                if (num == 0) {
                    log.info("失败的记录:" + i + JSONUtil.toJsonStr(row));
                }
            }
        }

        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeCodeNoForMapping(String codeNo) {
        List<CodeLibrary> codeLibraries = paymentDao.queryCodeLibraryList(codeNo);
        String now = DateUtil.now();
        for (int i = 0; i < codeLibraries.size(); i++) {
            CodeLibrary codeLibrary = codeLibraries.get(i);
            EntCodeMappingEntity entCodeMappingEntity = new EntCodeMappingEntity();
            entCodeMappingEntity.setItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setDeclareMethod("0");
            entCodeMappingEntity.setItemName(codeLibrary.getItemName());
            entCodeMappingEntity.setHmItemNo(codeLibrary.getItemNo());
            entCodeMappingEntity.setHmCodeNo(codeLibrary.getCodeNo());
            entCodeMappingEntity.setCityCode("4403");
            entCodeMappingEntity.setCreateTime(now);
            log.info("处理第" + i + "条记录:" + JSONUtil.toJsonStr(entCodeMappingEntity));
            int num = paymentDao.insertEntCodeMapping(entCodeMappingEntity);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(entCodeMappingEntity));
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String hm = "008|阿尔巴尼亚";
        String sz = "008|阿尔巴尼亚共和国";
        int i = sz.indexOf(hm);
        boolean b = sz.startsWith(hm);
        System.out.println(b);
        System.out.println("不匹配" + sz.startsWith("qweq"));
        System.out.println("匹配数: " + i);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sortCountry(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("读取的值" + substring);
        String[] split = substring.split(",");
        for (int i = 0; i < split.length; i++) {
            String countryNameNew = split[i];
            String[] split1 = countryNameNew.split("\\|");
            String itemNo = split1[0];
            String countrynameOld = split1[1];
            Integer sortNo = i + 1;
            int num = paymentDao.sortCountry(itemNo, countryNameNew, sortNo);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(countryNameNew));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCertificate(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("证件----读取的值: " + substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:" + split.length);
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String certificateNameNeed = split1[1];
            //获取excel要求的
            String type = "TP_DocumentType";
            log.info("更新第" + i + "条:" + JSONUtil.toJsonStr(split1));
            int num = paymentDao.updateCertificate(itemNoNeed, certificateNameNeed, type);
            log.info("更新第" + i + "条结果:" + num);
            if (num == 0) {
                log.info("失败的记录:" + i + JSONUtil.toJsonStr(split1));
            }
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int sortCertificate(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("证件----读取的值: " + substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:" + split.length);
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String certificateNameNeed = split1[1];
            //获取excel要求的
            String type = "TP_DocumentType";
            log.info("更新第" + i + "条:" + JSONUtil.toJsonStr(split1));
            Integer sortNo = i + 1;
            int num = paymentDao.sortCertificate(itemNoNeed, certificateNameNeed, sortNo);
            log.info("更新第" + i + "条结果:" + num);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeCoin(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("币种----读取的值: " + substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:" + split.length);
        List list = new ArrayList();
        list.add("克瓦查");
        list.add("俄罗斯卢布");
        Map map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String coinNameNeed = split1[1];
            //获取excel要求的
            String type = "currencyCode";
            log.info("更新第" + i + "条:" + JSONUtil.toJsonStr(split1));
            //如果是有重复的条数 先跳过 后面手动处理
            if (list.contains(coinNameNeed)) {
                log.info("定位到:" + coinNameNeed + "----------跳过");
                continue;
            }
            Integer sortNo = i + 1;
            int num = paymentDao.updateCoin(itemNoNeed, coinNameNeed, type, sortNo);
            log.info("更新第" + i + "条结果:" + num);
            if (num == 0) {
                map.put(i, "失败" + JSONUtil.toJsonStr(split1));
            }
        }
        log.info("失败集合:" + JSONUtil.toJsonStr(map));
        return 0;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int changeAndSortExchange(StringBuilder stringBuilder) {
        String substring = stringBuilder.toString().substring(0, stringBuilder.length() - 1);
        System.out.println("交易所----读取的值: " + substring);
        String[] split = substring.split(",");
        System.out.println("总共需要插入的条数为:" + split.length);
//        List list=new ArrayList();
//        list.add("克瓦查");
//        list.add("俄罗斯卢布");
        Map map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            String certificateNameNew = split[i];
            String[] split1 = certificateNameNew.split("\\|");
            String itemNoNeed = split1[0];
            String coinNameNeed = split1[1];
            //获取excel要求的
            String type = "ListedExchange";
            log.info("更新第" + i + "条:" + JSONUtil.toJsonStr(split1));
            //如果是有重复的条数 先跳过 后面手动处理
//            if (list.contains(coinNameNeed)){
//                log.info("定位到:"+coinNameNeed+"----------跳过");
//                continue;
//            }
            Integer sortNo = i + 1;
            int num = paymentDao.updateExchange(itemNoNeed, coinNameNeed, type, sortNo);
            log.info("更新第" + i + "条结果:" + num);
            if (num == 0) {
                map.put(i, "失败" + JSONUtil.toJsonStr(split1));
            }
        }
        log.info("失败集合:" + JSONUtil.toJsonStr(map));
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNameForMapping() {
        // 补充华盟name给 测试
        List<EntCodeMappingEntity> list = paymentDao.queryCodeLibraryListAll();
        for (int i = 0; i < list.size(); i++) {
            int num = paymentDao.addNameForMapping(list.get(i));
            if (num == 0) {
                log.error("更新失败" + JSONUtil.toJsonStr(list.get(i)));
            }
        }
    }

    //********公共参数******* start
    //读取路径
    private String fileNameStr ="C:\\Users\\sunhcer.shi\\Desktop\\上线文件\\0515环境保护税\\污染物字典.xlsx";

    //生成几级字典 1 则只读取父级所在列,2 则读取父子级所在列
    private int generateLevel= 2;
    //字典码
    private String code_no = "pollutants";
    //备注
    private String remark = "环境保护税-抽样测算";
    //排序号 初始值(需要在service层配置该参数)
    private int sort_no = 0;//如果是0,自动获取数据库该字典的当前最大序列号
    //父级节点码前缀
    private String item_no1_pre = "sp";
    //********公共参数*******end


    //********解析参数******* start
    // 从第几行开始 读内容写字典
    private int startRow = 2;
    // 读到第几行结束写字典
    private int endRow = 0 ; //非必要,为0时,读取所有行

    //父级起始编码
    private int item_no1_pre_no = 1;
    //父级所在列
    private int item_no1_column = 1;

    //子级起始编码 注: 子级以父级为前缀 //若需要补充字典,则此项需要比数据库的项目大
    private int item_no2_pre_no = 1;
    //子级所在列
    private int item_no2_column = 2;
    //********解析参数******* end


    //父节点编码
    private String relative_code1 = "";//不配置则父节点为空;auto则自动获取父级节点所在列的数据库编码;其他情况比如取 p01 则 父节点编码填充 p01

    //父级节点所在列
    private int relative_code1_cloumn = 5;//relative_code1 配置为空,则不取这一项
    //模板
    private String pattern = "INSERT INTO `code_libaray`(`code_no`, `item_no`, `item_name`, `sort_no`, `enable_flag`, `relative_code1`, `relative_code2`, `relative_code3`, `relative_code4`, `relative_code5`, `remark`, `create_time`, `update_time`)  " +
            "  VALUES ('#{01}', '#{02}',   '#{03}',      #{04}     , '1',           '#{05}'     ,    NULL,             NULL,             NULL,              NULL          , '非抽样测算-污染物', '2021-04-21 17:32:40', '2021-04-21 17:32:40');";

    @Autowired
    private CodeLibraryService codeLibraryService;

    @Autowired
    private CodeLibraryMapper codeLibraryMapper;

    /**
     * 自动生成字典
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void generateTwoLevel() {
        // 初始化模板序列号
        QueryWrapper<CodeLibrary> codeLibraryQueryWrapper = new QueryWrapper<>();
        codeLibraryQueryWrapper.lambda().eq(CodeLibrary::getCodeNo, code_no);
        List<CodeLibrary> list = codeLibraryService.list(codeLibraryQueryWrapper);
        if (CollectionUtils.isNotEmpty(list)) {
            CodeLibrary codeLibrary = list.stream().max(Comparator.comparing(CodeLibrary::getSortNo)).get();
            sort_no = codeLibrary.getSortNo() + 1;
        } else {
            sort_no = 1;
        }

        //减免政策
        String fileName = fileNameStr;
        // 写法2：
        ExcelReader excelReader = null;
        //初始化监听类
        DemoDataListener listener = new DemoDataListener(endRow,generateLevel,remark,beanFinder, startRow, code_no, sort_no, item_no1_pre, item_no1_pre_no, item_no1_column, item_no2_pre_no, item_no2_column, relative_code1, relative_code1_cloumn);
        try {
            excelReader = EasyExcel.read(fileName, listener).build();
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            excelReader.read(readSheet);

        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
        log.info("完毕");
    }
}
