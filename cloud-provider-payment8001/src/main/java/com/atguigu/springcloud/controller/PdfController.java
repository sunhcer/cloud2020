package com.atguigu.springcloud.controller;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.atguigu.springcloud.constant.CommonConstant;
import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.mode.strategy.strategyfactory.Context;
import com.atguigu.springcloud.service.PaymentService;
import io.github.jonathanlink.PDFLayoutTextStripper;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.cli.CommandLine;
//import org.apache.commons.cli.CommandLineParser;
//import org.apache.commons.cli.DefaultParser;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//import technology.tabula.CommandLineApp;

import java.io.*;

/**
 * pdf转csv,再读取csv文件
 * @author sunhcer.shi
 * @date 2021/03/27 10:20
 **/
@RestController
@RequestMapping("/pdfParse")
@Slf4j
public class PdfController {

    @Autowired
    private PaymentService paymentService;
//    tabula解析表格,有几个问题: 1.换行读成多个cell 2:当其中一列的内容很长,会被挤到前面一个格子里面,前面一个格子也有这种情况

    @Autowired
    private Context context;

    @GetMapping("/strategy")
    public CommonResult getStrategy(){

        context.doAnything(CommonConstant.TYPE_01);
        return CommonResult.succ(CommonConstant.TYPE_01);
    }




    @GetMapping("/csvParse")
    public void parsePDF(){
        //解密文件路径
//        String filePath = "E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf";
//        //获取pdf的所有填报的表格信息
//        JSONArray jsonArray1 = returnPdfEntityList(filePath);
//        JSONArray array = (JSONArray) jsonArray1.get(23);
//        System.out.println(array.toString());
//        JSONArray array1 = (JSONArray) array.get(8);
//        JSONObject o = (JSONObject)array1.get(2);
//        Object text = o.get("text");
//        System.out.println("=========================================="+text.toString());
//        System.out.println(array1.get(3).toString());
//        System.out.println(array1.get(4).toString());

    }


    //不行的,tabula 生成的csv文件还是会挤在一起
    @GetMapping("/pdf2Csv")
    public void pdf2Csv(){
//        //pdf先转换成csv文件 输出到文件
//        String[] ar={"-o=E:/TaxSaaS/pdfParse/output.csv","-p=all","E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf"};
//        //提取的信息输出到控制台
//        String[] ar1={"-p=all","E:/TaxSaaS/upload/2021/03/26/202103261726111895072263.pdf"};
//
//        CommandLineApp.main(ar);
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
        // 增加了-l之后可以让他 黏在一起的值分开
//        String[] args = new String[]{"-f=JSON", "-p=all","-l", filePath};
//        JSONArray data=new JSONArray();
//        try {
//            //解析pdf当中的所有表格信息
//            CommandLineParser parser = new DefaultParser();
//            CommandLine cmd = parser.parse(CommandLineApp.buildOptions(), args);
//            StringBuilder stringBuilder = new StringBuilder();
//            new CommandLineApp(stringBuilder, cmd).extractTables(cmd);
//            String resul = stringBuilder.toString();
//            //解析出来的Strign 字符 转为JSONArray
//            JSONArray jsonArray = JSONUtil.parseArray(resul);
//            //通过循环获取第二层的data值
//            for (Object o : jsonArray) {
//                JSONObject jsonObject= (JSONObject) o;
//                JSONArray data1 = (JSONArray) jsonObject.get("data");
//
//                if (data1.size() !=0 && data1.size() !=2){
//                    data.add(data1);
//                }
//            }
//            log.info("解析表格结束........");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return data;
        return null;
    }
// 按行读取txt,然后更新国家名称和编码
    @GetMapping("/changeTxt")
    public void changecountry(@RequestParam String type,@RequestParam(required = false) String codeNo) throws IOException {
        //BufferedReader是可以按行读取文件
        switch (type){
            case "0":
                //改变国家编码
                changeCountry();
                break;
            case "01":
                //国家按照excel排序
                sortCountry();
                break;
            case "02":
                //增加国家英文
                addCountryEng();
                break;
            case "1":
                //改证件类型
                changeCertificate();
                break;
            case "11":
                //证件类型按照execl排序
                sortCertificate();
                break;
            case "2":
                //修改币种--并且排序
                changeCoin();
                break;
            case "3":
                //修改交易所 + 排序
                changeAndSortExchange();
                break;
            case "4":
                //mapping 表增加映射国家
                changeContryForMapping();
                break;
            case "5":
                //mapping 根据需要的字典类别 导入原来的
                changeCodeNoForMapping(codeNo);
                break;
            case "6":
                //mapping 国民经济行业
                changeIndustryForMapping();
                break;
            case "61":
                //mapping 国民经济行业 广东 cit
                changeGDCITIndustryForMapping();
                break;
            case "7":
                //mapping 补充华盟name
                addNameForMapping();
                break;
            case "8":
                //mapping 补充 国家
                supplementNameForMapping();
                break;
            case "9":
                //mapping 广东 国家 tp
                addGDCountryForMapping();
                break;
            case "10":
                //mapping 广东 国家 CIT
                addGDCITCountryForMapping();
                break;
            case "111":
                //mapping 广东 股东信息证件类型 CIT
                addGDCITCertifyForMapping();
                break;
            case "12":
                //mapping 广东 国家 CIT 补充华盟缺少的一部分
                addGDCITCountryDiffForMapping();
                break;


            case "13":
                //mapping 广东 gd_tp_coins 币种
                addGDTPcoinsForMapping();
                break;
            case "14":
                //mapping 广东 gd_tp_industry 国民经济行业
                addGDTPIndustryForMapping();
                break;
        }
    }

    private void addGDTPIndustryForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/gd_tp_industry.txt");
        int num=paymentService.addGDTPIndustryForMapping(stringBuilder);
    }

    private void addGDTPcoinsForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/gd_tp_coins.txt");
        int num=paymentService.addGDTPcoinsForMapping(stringBuilder);
    }

    private void addGDCITCountryDiffForMapping() throws IOException {
        //        StringBuilder stringBuilder = parseTextFile("d://txt/gd_industry_一级.txt");
        StringBuilder stringBuilder = parseTextFile("d://txt/gd_cit_country_diff.txt");
//        StringBuilder stringBuilder = parseTextFile("d://txt/industry_2.txt");
        int num=paymentService.addGDCITCountryDiffForMapping(stringBuilder);
    }

    private void changeGDCITIndustryForMapping() throws IOException {
//        StringBuilder stringBuilder = parseTextFile("d://txt/gd_industry_一级.txt");
        StringBuilder stringBuilder = parseTextFile("d://txt/gd_industry_三级.txt");
//        StringBuilder stringBuilder = parseTextFile("d://txt/industry_2.txt");
        int num=paymentService.changeGDCITIndustryForMapping(stringBuilder);
    }

    private void addGDCITCertifyForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/gd_cit_certify.txt");
        int num=paymentService.addGDCITCertifyForMapping(stringBuilder);
    }

    private void addGDCITCountryForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/gz_cit_country.txt");
        int num=paymentService.addGDCITCountryForMapping(stringBuilder);
    }

    private void addGDCountryForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/gz_country.txt");
        int num=paymentService.addGDCountryForMapping(stringBuilder);
    }

    private void supplementNameForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/country_补充.txt");
        int num=paymentService.supplementContryForMapping(stringBuilder);
    }

    private void addNameForMapping() {
        paymentService.addNameForMapping();
    }

    private void changeIndustryForMapping() throws IOException {
//        StringBuilder stringBuilder = parseTextFile("d://txt/gd_industry_一级.txt");
        StringBuilder stringBuilder = parseTextFile("d://txt/industry_2.txt");
        int num=paymentService.changeIndustryForMapping(stringBuilder);
    }

    private void addCountryEng() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/sql_addcountryent.txt");
        int num=paymentService.addCountryEng(stringBuilder);
    }

    private void changeCodeNoForMapping(String codeNo) {
        int num=paymentService.changeCodeNoForMapping(codeNo);
    }

    private void changeContryForMapping() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://txt/country.txt");
//        StringBuilder stringBuilder = parseTextFile("d://txt/country_补充.txt");
        int num=paymentService.changeContryForMapping(stringBuilder);
    }

    private void changeAndSortExchange() throws IOException {
        FileInputStream inputStream = new FileInputStream("d://exchange.txt");
        StringBuilder stringBuilder=parseTextFile("d://exchange.txt");
        int num=paymentService.changeAndSortExchange(stringBuilder);
    }

    private StringBuilder parseTextFile(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String str = null;
        StringBuilder stringBuilder = new StringBuilder();
        while((str = bufferedReader.readLine()) != null)
        {
            System.out.println(str);
            stringBuilder.append(str).append(",");
        }
        //close
        inputStream.close();
        bufferedReader.close();
        return stringBuilder;
    }

    private void sortCertificate() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://certificate.txt");
        int num=paymentService.sortCertificate(stringBuilder);
    }

    private void sortCountry() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://co.txt");
        int num=paymentService.sortCountry(stringBuilder);
    }

    private void changeCoin() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://rmb.txt");
        int num=paymentService.changeCoin(stringBuilder);
    }


    private void changeCertificate() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://certificate.txt");
        int num=paymentService.updateCertificate(stringBuilder);
    }

    private void changeCountry() throws IOException {
        StringBuilder stringBuilder = parseTextFile("d://co.txt");
        int num=paymentService.updateCountry(stringBuilder);
    }



}
