package com.atguigu.springcloud.dao;
import org.apache.ibatis.annotations.Param;
import java.util.Collection;

import com.atguigu.springcloud.vo.Popups;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Entity generator.domain.Popups
 */
@Repository
public interface PopupsMapper extends BaseMapper<Popups> {

    int insertBatch(@Param("popupsCollection") Collection<Popups> popupsCollection);
}




