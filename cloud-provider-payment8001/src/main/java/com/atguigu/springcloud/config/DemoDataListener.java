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

    // 读起始行号
    private int startRow = 2;
    //字典码
    private String code_no="samplePollutants";//污染物-抽样

    //本级节点码前缀
    private String item_no="sp";
    //本级节点所在列
    private int item_no_column=6;

    //父节点编码
    private String relative_code1="";//不配置则父节点为空;auto则自动获取父级节点所在列的数据库编码;其他情况比如取 p01 则 父节点编码填充 p01

    //父级节点所在列
    private int relative_code1_cloumn=5;//relative_code1 配置为空,则不取这一项

    @Override
    public void invoke(Object o, AnalysisContext context) {
        //进来是整行数据 key为行号 value为单元格值
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        //获取行号
        Integer rowIndex = context.readRowHolder().getRowIndex();

        for (Map.Entry cell :cellMap.entrySet()) {
//            System.out.println(cell.getValue());
            System.out.println(cell.getKey());
        }
        System.out.println(cellMap.get(0));
    }
}
