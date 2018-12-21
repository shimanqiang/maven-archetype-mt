package com.sankuai.meituan.demo.api.func1;

import com.sankuai.meituan.demo.api.ThriftClientConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shimanqiang
 * @since 2018/11/28 下午7:35
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ThriftClientConfig.class, HelloServiceClient.class})
public class HelloServiceClientTest {

    @Autowired
    HelloServiceClient helloServiceClient;
    @Test
    public void hello() throws Exception {
        String zhangsan = helloServiceClient.hello("zhangsan");
        System.out.println(zhangsan);
    }

}