package com.atguigu.springcloud.config;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Cell;
import com.alibaba.excel.read.metadata.ReadSheet;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * excel监听器
 *
 * @author sunhcer.shi
 * @date 2021/05/15 20:02
 **/
@Slf4j
public class DemoDataListener extends AnalysisEventListener<Object> {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\sunhcer.shi\\Desktop\\上线文件\\0515环境保护税\\污染物字典.xlsx";
        // 写法2：
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, new DemoDataListener()).build();
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
    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
    }


//------------------------------------自动生成3级字典---------------------------------------

    // 读起始行号
    private int startRow = 2;
    //字典码
    private String code_no="samplePollutants";//污染物-抽样

    //一级节点码前缀
    private String item_no1="sp";
    //一级节点所在列
    private int item_no_column1=4;


    //二级节点码前缀
//    private String item_no2="sp";
    //二级节点所在列
    private int item_no_column2=5;


    //三级节点码前缀
//    private String item_no3="sp";
    //三级节点所在列
    private int item_no_column3=6;


    //父节点编码
    private String relative_code1="";//不配置则父节点为空;auto则自动获取父级节点所在列的数据库编码;其他情况比如取 p01 则 父节点编码填充 p01

    //父级节点所在列
    private int relative_code1_cloumn=5;//relative_code1 配置为空,则不取这一项



    @Override
    public void invoke(Object o, AnalysisContext context) {
        //进来是整行数据 key为列号 value为单元格值
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        //获取行号
        Integer rowIndex = context.readRowHolder().getRowIndex();
        //匹配到起始行
        if (rowIndex.intValue()>=startRow){
            for (Map.Entry cell :cellMap.entrySet()) {
//            System.out.println(cell.getValue());
//                System.out.println(cell.getKey());
//                if (){
//
//                }
            }

//            doPadding();
        }


        System.out.println(cellMap.get(0));
    }




    //01
    private String pattern = "INSERT INTO `code_libaray`(`code_no`, `item_no`, `item_name`, `sort_no`, `enable_flag`, `relative_code1`, `relative_code2`, `relative_code3`, `relative_code4`, `relative_code5`, `remark`, `create_time`, `update_time`)  " +
                                                 "  VALUES ('#{01}', '#{02}',   '#{03}',      #{04}     , '1',           '#{05}'     ,    NULL,             NULL,             NULL,              NULL          , '非抽样测算-污染物', '2021-04-21 17:32:40', '2021-04-21 17:32:40');";

//    private String pattern = "INSERT INTO `code_libaray`(`code_no`, `item_no`, `item_name`, `sort_no`, `enable_flag`, `relative_code1`, `relative_code2`, `relative_code3`, `relative_code4`, `relative_code5`, `remark`, `create_time`, `update_time`)  " +
//            "  VALUES ('#{01}', '#{02}',   '#{03}',      #{04}     , '1',           '#{05}'     ,    NULL,             NULL,             NULL,              NULL          , '非抽样测算-污染物', '2021-04-21 17:32:40', '2021-04-21 17:32:40');";
}
