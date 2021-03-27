package com.atguigu.springcloud.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.github.jonathanlink.PDFLayoutTextStripper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import technology.tabula.CommandLineApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * pdf转csv,再读取csv文件
 * @author sunhcer.shi
 * @date 2021/03/27 10:20
 **/
@RestController
@RequestMapping("/pdfParse")
@Slf4j
public class PdfController {
//    tabula解析表格,有几个问题: 1.换行读成多个cell 2:当其中一列的内容很长,会被挤到前面一个格子里面,前面一个格子也有这种情况
    @GetMapping("/csvParse")
    public void parsePDF(){
        //解密文件路径
        String filePath = "E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf";
        //获取pdf的所有填报的表格信息
        JSONArray jsonArray1 = returnPdfEntityList(filePath);
        JSONArray array = (JSONArray) jsonArray1.get(22);
        JSONArray array1 = (JSONArray) array.get(17);
        JSONObject o = (JSONObject)array1.get(2);
        Object text = o.get("text");
        System.out.println("=========================================="+text.toString());

    }

    //不行的,tabula 生成的csv文件还是会挤在一起
    @GetMapping("/pdf2Csv")
    public void pdf2Csv(){
        //pdf先转换成csv文件 输出到文件
        String[] ar={"-o=E:/TaxSaaS/pdfParse/output.csv","-p=all","E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf"};
        //提取的信息输出到控制台
        String[] ar1={"-p=all","E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf"};

        CommandLineApp.main(ar);
        //PdfUtil.convert2Csv("E:\\doc\\pdf\\(2018) .pdf", "output.csv");
    }

    //换成 itext解析

    //换成 pdflayouttextStripper解析
    @GetMapping("/pdf2Text")
    public void pdf2Text(){
        String string = null;
        try {
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(new File("E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf"), "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper pdfTextStripper = new PDFLayoutTextStripper();
            string = pdfTextStripper.getText(pdDocument);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        };
        System.out.println(string);
    }



    public JSONArray returnPdfEntityList(String filePath) {
        String[] args = new String[]{"-f=JSON", "-p=all", filePath};
        JSONArray data=new JSONArray();
        try {
            //解析pdf当中的所有表格信息
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), args);
            StringBuilder stringBuilder = new StringBuilder();
            new CommandLineApp(stringBuilder, cmd).extractTables(cmd);
            String resul = stringBuilder.toString();
            //解析出来的Strign 字符 转为JSONArray
            JSONArray jsonArray = JSONUtil.parseArray(resul);
            //通过循环获取第二层的data值
            for (Object o : jsonArray) {
                JSONObject jsonObject= (JSONObject) o;
                JSONArray data1 = (JSONArray) jsonObject.get("data");

                if (data1.size() !=0 && data1.size() !=2){
                    data.add(data1);
                }
            }
            log.info("解析表格结束........");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
