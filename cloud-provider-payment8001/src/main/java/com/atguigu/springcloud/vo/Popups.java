package com.atguigu.springcloud.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 弹幕采集表
 * @TableName popups
 */
@TableName(value ="popups")
public class Popups implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 集数
     */
    @TableField(value = "episode_num")
    private Integer episode_num;

    /**
     * 弹幕内容
     */
    @TableField(value = "content")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private String create_time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private String update_time;

    /**
     * 剧名
     */
    @TableField(value = "name")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 集数
     */
    public Integer getEpisode_num() {
        return episode_num;
    }

    /**
     * 集数
     */
    public void setEpisode_num(Integer episode_num) {
        this.episode_num = episode_num;
    }

    /**
     * 弹幕内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 弹幕内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 创建时间
     */
    public String getCreate_time() {
        return create_time;
    }

    /**
     * 创建时间
     */
    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    /**
     * 更新时间
     */
    public String getUpdate_time() {
        return update_time;
    }

    /**
     * 更新时间
     */
    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    /**
     * 剧名
     */
    public String getName() {
        return name;
    }

    /**
     * 剧名
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Popups other = (Popups) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getEpisode_num() == null ? other.getEpisode_num() == null : this.getEpisode_num().equals(other.getEpisode_num()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getEpisode_num() == null) ? 0 : getEpisode_num().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", episode_num=").append(episode_num);
        sb.append(", content=").append(content);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}