package com.atguigu.springcloud.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 各地区的税局和华盟的对应关系
 * @author sunhcer.shi
 * @date 2021/03/29 16:30
 **/
@ApiModel("各地区的税局和华盟的对应关系")
public class EntCodeMappingEntity implements Serializable {
    private static final long serialVersionUID = -2049841795185029689L;


    @ApiModelProperty(value = "各税局系统编码")
    private String itemNo;

    @ApiModelProperty(value = "编码名称")
    private String itemName;

    @ApiModelProperty(value = "对应的华盟分类编号")
    private String hmCodeNo;

    @ApiModelProperty(value = "对应的华盟编码")
    private String hmItemNo;

    @ApiModelProperty(value = "申报方式 0客户端 1网页")
    private String declareMethod;

    @ApiModelProperty(value = "数据新增时间")
    private String createTime;

    @ApiModelProperty(value = "省、市前4位编码")
    private String cityCode;

    @ApiModelProperty(value = "hm名称")
    private String hmItemName;

    public EntCodeMappingEntity() {
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getHmCodeNo() {
        return hmCodeNo;
    }

    public void setHmCodeNo(String hmCodeNo) {
        this.hmCodeNo = hmCodeNo;
    }

    public String getHmItemNo() {
        return hmItemNo;
    }

    public void setHmItemNo(String hmItemNo) {
        this.hmItemNo = hmItemNo;
    }

    public String getDeclareMethod() {
        return declareMethod;
    }

    public void setDeclareMethod(String declareMethod) {
        this.declareMethod = declareMethod;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getHmItemName() {
        return hmItemName;
    }

    public void setHmItemName(String hmItemName) {
        this.hmItemName = hmItemName;
    }

    @Override
    public String toString() {
        return "EntCodeMappingEntity{" +
                "itemNo='" + itemNo + '\'' +
                ", itemName='" + itemName + '\'' +
                ", hmCodeNo='" + hmCodeNo + '\'' +
                ", hmItemNo='" + hmItemNo + '\'' +
                ", declareMethod='" + declareMethod + '\'' +
                ", createTime='" + createTime + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", hmItemName='" + hmItemName + '\'' +
                '}';
    }
}
