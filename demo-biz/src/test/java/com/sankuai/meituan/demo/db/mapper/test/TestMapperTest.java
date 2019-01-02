package com.sankuai.meituan.demo.db.mapper.test;

import com.sankuai.meituan.demo.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author shimanqiang
 * @since 2019/1/2 上午9:33
 */

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class TestMapperTest {
    @Autowired
    TestMapper testMapper;

    @Test
    public void findById() throws Exception {
        com.sankuai.meituan.demo.db.entitys.test.Test byId = testMapper.findById(1);
        System.out.println(byId.getName());
    }

    @Test
    public void insert() throws Exception {
    }

}