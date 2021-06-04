package com.atguigu.springcloud.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author: curry.wu
 * @create: 2020/9/28 10:11
 **/
@TableName("upload_file_info")
@ApiModel(value = "上传文件信息表")
public class UploadFileEntity implements Serializable {

    private static final long serialVersionUID = 1572970482221505231L;

    @ApiModelProperty(value = "主键")
    private String id;

    @ApiModelProperty(value = "企业id")
    private String entId;

    @ApiModelProperty(value = "文件类型，01资产负债表，02利润表，03现金流量表，04所有者权益表，05.科目余额表，06明细账，07股东分红台账，" +
            "tp_01主体文档，tp_02本地文档，tp_03特殊事项文档，tp_04文档模板，tp_05税局送达书，tp_06企业提交资料，tp_07关联合同，tp_08集团财务报告，" +
            "tp_09其他资料，tp_10交易明细（不够再加）")
    private String fileType;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "上传文件时的名称")
    private String fileOldName;

    @ApiModelProperty(value = "文件本地路径")
    private String filePath;

    @ApiModelProperty(value = "文件http路径")
    private String fileHttpPath;

    @ApiModelProperty(value = "创建时间")
    private String createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * add
     */
    /*@ApiModelProperty(value = "文件")
    private MultipartFile file;*/

    @ApiModelProperty(value = "档案年份")
    private String taxYear;

    @ApiModelProperty(value = "文件大小")
    private String fileSize;

    @ApiModelProperty(value = "删除时间")
    private String deleteTime;

    @ApiModelProperty(value = "是否删除")
    private String ifDelete;

    @ApiModelProperty(value = "删除人")
    private String deleteUser;

    @ApiModelProperty(value = "最后更新时间")
    private String lastUpdateTime;

    @ApiModelProperty(value = "最后更新人")
    private String lastUpdateUser;

    @ApiModelProperty(value = "存储id")
    private String storageId;

    public String getStorageId() {
        return storageId;
    }

    public void setStorageId(String storageId) {
        this.storageId = storageId;
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getTaxYear() {
        return taxYear;
    }

    public void setTaxYear(String taxYear) {
        this.taxYear = taxYear;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getIfDelete() {
        return ifDelete;
    }

    public void setIfDelete(String ifDelete) {
        this.ifDelete = ifDelete;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEntId() {
        return entId;
    }

    public void setEntId(String entId) {
        this.entId = entId;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileOldName() {
        return fileOldName;
    }

    public void setFileOldName(String fileOldName) {
        this.fileOldName = fileOldName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileHttpPath() {
        return fileHttpPath;
    }

    public void setFileHttpPath(String fileHttpPath) {
        this.fileHttpPath = fileHttpPath;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    /*public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }*/

    @Override
    public String toString() {
        return "UploadFileEntity{" +
                "id='" + id + '\'' +
                ", entId='" + entId + '\'' +
                ", fileType='" + fileType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileOldName='" + fileOldName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileHttpPath='" + fileHttpPath + '\'' +
                ", createTime='" + createTime + '\'' +
                ", createUser='" + createUser + '\'' +
               // ", file=" + file +
                '}';
    }
}
