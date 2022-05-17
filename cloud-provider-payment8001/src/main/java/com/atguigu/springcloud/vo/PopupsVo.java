package com.atguigu.springcloud.vo;

import java.io.Serializable;

/**
 * 弹幕读取实体
 * @author sunhcer
 * @date 2021/10/08 01:01
 **/
public class PopupsVo implements Serializable {
    //"id": 54248228426300930,
    //"progress": 927,
    //"mode": 1,
    //"fontsize": 25,
    //"color": 15138834,
    //"midHash": "3409c16a",
    //"content": "想不到吧我囤了170话",
    //"ctime": 1630561484,
    //"weight": 7,
    //"idStr": "54248228426300928"
    private Long id;
    private Long progress;
    private Long mode;
    private Long fontsize;
    private Long color;
    private String midHash;
    private String content;
    private Long ctime;
    private Long weight;
    private String idStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProgress() {
        return progress;
    }

    public void setProgress(Long progress) {
        this.progress = progress;
    }

    public Long getMode() {
        return mode;
    }

    public void setMode(Long mode) {
        this.mode = mode;
    }

    public Long getFontsize() {
        return fontsize;
    }

    public void setFontsize(Long fontsize) {
        this.fontsize = fontsize;
    }

    public Long getColor() {
        return color;
    }

    public void setColor(Long color) {
        this.color = color;
    }

    public String getMidHash() {
        return midHash;
    }

    public void setMidHash(String midHash) {
        this.midHash = midHash;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    @Override
    public String toString() {
        return "PopupsVo{" +
                "id=" + id +
                ", progress=" + progress +
                ", mode=" + mode +
                ", fontsize=" + fontsize +
                ", color=" + color +
                ", midHash='" + midHash + '\'' +
                ", content='" + content + '\'' +
                ", ctime=" + ctime +
                ", weight=" + weight +
                ", idStr='" + idStr + '\'' +
                '}';
    }
}
