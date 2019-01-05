package com.sankuai.meituan.demo.db.mapper.test;

import com.sankuai.meituan.demo.db.config.DataSourceAware;
import com.sankuai.meituan.demo.db.entitys.test.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author shimanqiang
 * @since 2019/1/1 下午8:29
 */
@DataSourceAware("dataSourceOrder")
public interface TestMapper {

    /**
     * 通过id 查看详情
     */
    @Select("SELECT * FROM test WHERE id = #{id}")
    Test findById(@Param("id") int id);

    @Insert("INSERT INTO `test` (`name`) VALUES ('#{name}')")
    int insert(Test test);
}
