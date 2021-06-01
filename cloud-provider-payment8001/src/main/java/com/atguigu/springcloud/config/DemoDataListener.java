package com.atguigu.springcloud.config;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.atguigu.springcloud.dao.CodeLibraryMapper;
import com.atguigu.springcloud.entities.CodeLibrary;
import com.atguigu.springcloud.service.CodeLibraryService;
import com.atguigu.springcloud.service.PaymentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jodd.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * excel监听器
 *
 * @author sunhcer.shi
 * @date 2021/05/15 20:02
 **/
@Slf4j
public class DemoDataListener extends AnalysisEventListener<Object> {

    private BeanFinder beanFinder;
    private PaymentService paymentService;


    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
    }


//------------------------------------自动生成3级字典(参数需要同步配置到service层)---------------------------------------


    public DemoDataListener() {
    }

    public DemoDataListener(int endRow,int generateLevel,String remark, BeanFinder beanFinder, int startRow, String code_no, int sort_no, String item_no1_pre, int item_no1_pre_no, int item_no1_column, int item_no2_pre_no, int item_no2_column, String relative_code1, int relative_code1_cloumn) {
        this.endRow=endRow;
        this.generateLevel=generateLevel;
        this.remark = remark;
        this.beanFinder = beanFinder;
        this.startRow = startRow;
        this.code_no = code_no;
        this.sort_no = sort_no;
        this.item_no1_pre = item_no1_pre;
        this.item_no1_pre_no = item_no1_pre_no;
        this.item_no1_column = item_no1_column;
        this.item_no2_pre_no = item_no2_pre_no;
        this.item_no2_column = item_no2_column;
        this.relative_code1 = relative_code1;
        this.relative_code1_cloumn = relative_code1_cloumn;
        paymentService = beanFinder.findOne(PaymentService.class);
        codeLibraryService = beanFinder.findOne(CodeLibraryService.class);
        codeLibraryMapper = beanFinder.findOne(CodeLibraryMapper.class);

    }

    //生成几级字典 1 则只读取父级所在列,2 则读取父子级所在列
    private int generateLevel;
    // 读起始行号
    private int startRow;
    // 读到第几行结束写字典
    private int endRow; //非必要,为0时,读取所有行
    //字典码
    private String code_no;//污染物-抽样
    //序号起始(需要在service层配置该参数)
    private int sort_no;//如果是0,自动获取数据库该字典的当前追到序列号

    //一级节点码前缀
    private String item_no1_pre;
    private int item_no1_pre_no;
    private int item_no1_column;

    //二级节点码前缀
//    private String item_no2="sp";
    //二级起始编码
    private int item_no2_pre_no;
    //二级节点所在列
    private int item_no2_column;

    //备注
    private String remark;
    //父节点编码
    private String relative_code1;//不配置则父节点为空;auto则自动获取父级节点所在列的数据库编码;其他情况比如取 p01 则 父节点编码填充 p01

    //父级节点所在列
    private int relative_code1_cloumn;//relative_code1 配置为空,则不取这一项


    private CodeLibraryService codeLibraryService;


    private CodeLibraryMapper codeLibraryMapper;

    private String fatherItemNo = "";

    @Override
    public void invoke(Object o, AnalysisContext context) {
        //进来是整行数据 key为列号 value为单元格值
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        //获取行号
        Integer rowIndex = context.readRowHolder().getRowIndex();
        //是否读截止
        if (endRow>0){
            //行截止读
            readQuit(cellMap,rowIndex);
        }else {
            //行无限读
            readUnlimited(cellMap,rowIndex);
        }
        System.out.println(cellMap.get(0));
    }

    private void readUnlimited(Map<Integer, Cell> cellMap, Integer rowIndex) {
        //匹配到起始行
        if (rowIndex >= startRow) {
            cellMap.forEach((key, value) -> {
                int column = Integer.parseInt(key.toString());
                //一级起始列
                if (column == item_no1_column) {
                    //填充一级 并插入
                    doPaddingOneLevel(value);
                }
                if (generateLevel == 2) {//1只生成父级,2生成父子级
                    //二级起始列
                    if (column == item_no2_column) {
                        //填充二级 并插入
                        doPaddingTwoLevel(value);
                    }
                }
            });

        }
    }

    public void readQuit(Map<Integer, Cell> cellMap, Integer rowIndex) {
        //匹配到起始行
        if (rowIndex >= startRow&&rowIndex<=endRow) {
            cellMap.forEach((key, value) -> {
                int column = Integer.parseInt(key.toString());
                //一级起始列
                if (column == item_no1_column) {
                    //填充一级 并插入
                    doPaddingOneLevel(value);
                }
                if (generateLevel == 2) {//1只生成父级,2生成父子级
                    //二级起始列
                    if (column == item_no2_column) {
                        //填充二级 并插入
                        doPaddingTwoLevel(value);
                    }
                }
            });
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void doPaddingTwoLevel(Object value) {
        if (ObjectUtil.isNotEmpty(value)) {
            String valueStr = value.toString();
            //初始化模板
            CodeLibrary codeLibrary = doConstrustPatternTwoLevel(valueStr);
            if (ObjectUtil.isNotEmpty(codeLibrary)) {
                //保存第二级
                QueryWrapper<CodeLibrary> codeLibraryQueryWrapper = new QueryWrapper<>();
                //如果有重名的需要单独拎出来
//                codeLibraryQueryWrapper.lambda().eq(CodeLibrary::getCodeNo,code_no).eq(CodeLibrary::getItemNo,codeLibrary.getItemNo());
                codeLibraryQueryWrapper.lambda().eq(CodeLibrary::getCodeNo, code_no).eq(CodeLibrary::getItemName, codeLibrary.getItemName());
                CodeLibrary one = codeLibraryService.getOne(codeLibraryQueryWrapper);
                if (ObjectUtil.isNotEmpty(one)) {
//                codeLibraryService.save(codeLibrary);
                    log.info("命中重名: {}", JSONUtil.toJsonStr(one));
                    //判断该父级下是否有该名字
                    QueryWrapper<CodeLibrary> codeLibraryQueryWrapper1 = new QueryWrapper<>();
                    codeLibraryQueryWrapper1.lambda().eq(CodeLibrary::getCodeNo, code_no).eq(CodeLibrary::getItemName, codeLibrary.getItemName())
                            .eq(CodeLibrary::getRelativeCode1, codeLibrary.getRelativeCode1());
                    CodeLibrary one1 = codeLibraryService.getOne(codeLibraryQueryWrapper1);
                    if (ObjectUtil.isNotEmpty(one1)) {
                        //命中二次则数据有问题
                        log.info("命中重名 二次: {}", JSONUtil.toJsonStr(one));
//                        throw new RuntimeException("命中重名 二次 : excel数据有误" + JSONUtil.toJsonStr(one));
                    } else {
                        boolean save = codeLibraryService.save(codeLibrary);
                        if (!save) {
                            throw new RuntimeException("异常 cellData:" + value);
                        }

                        //插入成功,排序号和 item_no递增
                        item_no2_pre_no++;
                        sort_no++;
                    }

                } else {
                    boolean save = codeLibraryService.save(codeLibrary);
                    if (!save) {
                        throw new RuntimeException("异常 cellData:" + value);
                    }

                    //插入成功,排序号和 item_no递增
                    item_no2_pre_no++;
                    sort_no++;
                }
            } else {
                System.out.println("跳過");
            }

            //清空父级缓存
            fatherItemNo = "";

        }
    }

    /**
     * 初始化二级模板
     *
     * @param cellData
     * @return
     */
    public CodeLibrary doConstrustPatternTwoLevel(String cellData) {
        if ("skip".equals(cellData)) {
            return null;
        }
        CodeLibrary codeLibrary = new CodeLibrary();
        codeLibrary.setCodeNo(code_no);
        //查询父级itemno缓存
        if (StrUtil.isNotEmpty(fatherItemNo)) {
            codeLibrary.setRelativeCode1(fatherItemNo);
        } else {
            throw new RuntimeException("父级item_no:" + fatherItemNo + " 为空,cellData:" + cellData);
        }
        //二级的前缀是一级的code
        codeLibrary.setSortNo(sort_no);
        codeLibrary.setItemNo(getIncreasePre(fatherItemNo, item_no2_pre_no));
        codeLibrary.setItemName(cellData);
        codeLibrary.setEnableFlag("1");
        codeLibrary.setCreateTime(DateUtil.date());
        codeLibrary.setUpdateTime(DateUtil.date());
        codeLibrary.setRemark(remark);
        return codeLibrary;
    }

    /**
     * 根据前缀和起始序号获取递增code
     *
     * @param value
     */
    @Transactional(rollbackFor = Exception.class)
    public void doPaddingOneLevel(Object value) {
        if (ObjectUtil.isNotEmpty(value)) {
            String cellData = value.toString();
            //初始化模板
            CodeLibrary codeLibrary = doConstrustPatternOneLevel(cellData);
        }
    }

    //    private String pattern = "INSERT INTO `code_libaray`(`code_no`, `item_no`, `item_name`, `sort_no`, `enable_flag`, `relative_code1`, `relative_code2`, `relative_code3`, `relative_code4`, `relative_code5`, `remark`, `create_time`, `update_time`)  " +
//                                                   "  VALUES ('#{01}', '#{02}',   '#{03}',      #{04}     , '1',           '#{05}'     ,    NULL,             NULL,             NULL,              NULL          , '非抽样测算-污染物', '2021-04-21 17:32:40', '2021-04-21 17:32:40');";


    private CodeLibrary doConstrustPatternOneLevel(String cellData) {
        QueryWrapper<CodeLibrary> codeLibraryQueryWrapper = new QueryWrapper<>();

        CodeLibrary codeLibrary = new CodeLibrary();
        codeLibrary.setCodeNo(code_no);
        codeLibrary.setSortNo(sort_no);
        codeLibrary.setItemName(cellData);
        codeLibrary.setEnableFlag("1");
        codeLibrary.setCreateTime(DateUtil.date());
        codeLibrary.setUpdateTime(DateUtil.date());
        codeLibrary.setRemark(remark);
        codeLibraryQueryWrapper.lambda().eq(CodeLibrary::getCodeNo, code_no).eq(CodeLibrary::getItemName, codeLibrary.getItemName());
        CodeLibrary one = codeLibraryService.getOne(codeLibraryQueryWrapper);
        if (ObjectUtil.isNotEmpty(one)) {
            //如果父级已经插入了,就把父级的数据同步到成员变量
//          codeLibraryService.save(codeLibrary);
            fatherItemNo = one.getItemNo();
        } else {
            codeLibrary.setItemNo(getIncreasePre(item_no1_pre, item_no1_pre_no));
            //更新父级item_no
            fatherItemNo = codeLibrary.getItemNo();
            boolean save = codeLibraryService.save(codeLibrary);
            if (!save) {
                throw new RuntimeException("异常 cellData:" + cellData);
            }
            //插入成功,排序号和 item_no递增
            item_no1_pre_no++;
            sort_no++;
        }

        return codeLibrary;

    }


    /**
     * 根据起始序列号和前缀 生成递增的item_no
     *
     * @param item_no1_pre
     * @param item_no1_pre_no
     * @return
     */
    private String getIncreasePre(String item_no1_pre, int item_no1_pre_no) {
        String item_no = "";
        if (!StringUtil.isEmpty(item_no1_pre)) {
            //拼接前缀
            item_no = item_no1_pre + getIncreasePreNo(item_no1_pre_no);
        } else {
            item_no = getIncreasePreNo(item_no1_pre_no);
        }
        return item_no;
    }

    private String getIncreasePreNo(int item_no1_pre_no) {
        if (item_no1_pre_no < 10) {
            return "0" + item_no1_pre_no;
        } else {
            return String.valueOf(item_no1_pre_no);
        }
    }

    //01
    private String pattern;


}
