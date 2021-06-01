package com.atguigu.springcloud.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 字典
 */
@TableName("code_libaray")
public class CodeLibrary implements Serializable {


    @ApiModelProperty(value = "分类编号")
    private String codeNo;

    @ApiModelProperty(value = "子项编码")
    private String itemNo;

    @ApiModelProperty(value = "子项描述")
    private String itemName;

    @ApiModelProperty(value = "排序号")
    private Integer sortNo;

    @ApiModelProperty(value = "是否启用")
    private String enableFlag;

    @ApiModelProperty(value = "关联关系1")
    private String relativeCode1;

    @ApiModelProperty(value = "关联关系2")
    private String relativeCode2;

    @ApiModelProperty(value = "关联关系3")
    private String relativeCode3;

    @ApiModelProperty(value = "关联关系4")
    private String relativeCode4;

    @ApiModelProperty(value = "关联关系5")
    private String relativeCode5;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "最后更新时间")
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "备注")
    private String remark;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CodeLibrary that = (CodeLibrary) o;
        return Objects.equals(codeNo, that.codeNo) &&
                Objects.equals(itemNo, that.itemNo) &&
                Objects.equals(itemName, that.itemName) &&
                Objects.equals(sortNo, that.sortNo) &&
                Objects.equals(enableFlag, that.enableFlag) &&
                Objects.equals(relativeCode1, that.relativeCode1) &&
                Objects.equals(relativeCode2, that.relativeCode2) &&
                Objects.equals(relativeCode3, that.relativeCode3) &&
                Objects.equals(relativeCode4, that.relativeCode4) &&
                Objects.equals(relativeCode5, that.relativeCode5) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(codeNo, itemNo, itemName, sortNo, enableFlag, relativeCode1, relativeCode2, relativeCode3, relativeCode4, relativeCode5, createTime, updateTime, remark);
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getEnableFlag() {
        return enableFlag;
    }

    public void setEnableFlag(String enableFlag) {
        this.enableFlag = enableFlag;
    }

    public String getRelativeCode1() {
        return relativeCode1;
    }

    public void setRelativeCode1(String relativeCode1) {
        this.relativeCode1 = relativeCode1;
    }

    public String getRelativeCode2() {
        return relativeCode2;
    }

    public void setRelativeCode2(String relativeCode2) {
        this.relativeCode2 = relativeCode2;
    }

    public String getRelativeCode3() {
        return relativeCode3;
    }

    public void setRelativeCode3(String relativeCode3) {
        this.relativeCode3 = relativeCode3;
    }

    public String getRelativeCode4() {
        return relativeCode4;
    }

    public void setRelativeCode4(String relativeCode4) {
        this.relativeCode4 = relativeCode4;
    }

    public String getRelativeCode5() {
        return relativeCode5;
    }

    public void setRelativeCode5(String relativeCode5) {
        this.relativeCode5 = relativeCode5;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
