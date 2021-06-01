package com.atguigu.springcloud.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * excel监听器
 *
 * @author sunhcer.shi
 * @date 2021/05/15 20:02
 **/
@Slf4j
public class DemoDataListenerTest extends AnalysisEventListener<Object> {

    private BeanFinder beanFinder;
    private PaymentService paymentService;

    public static void main(String[] args) {
        // 初始化模板序列号


        String fileName = "C:\\Users\\sunhcer.shi\\Desktop\\上线文件\\0515环境保护税\\污染物字典.xlsx";
        // 写法2：
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, new DemoDataListenerTest()).build();
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


    public DemoDataListenerTest() {
    }

    @Override
    public void invoke(Object o, AnalysisContext context) {
        //进来是整行数据 key为列号 value为单元格值
        Map<Integer, Cell> cellMap = context.readRowHolder().getCellMap();
        //获取行号
        Integer rowIndex = context.readRowHolder().getRowIndex();
        //匹配到起始行

        for (Map.Entry cell : cellMap.entrySet()) {
            System.out.println("cell = " + cell.toString());
        }


    }
}
