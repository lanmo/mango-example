package com.yn.mango.test;

import com.yn.mango.test.dao.UserDAO;
import com.yn.mango.test.pojo.GeoBlockOrder;
import com.yn.mango.util.L;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yangnan on 16/10/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MangoTest {

    @Resource
    private ApplicationContext context;
    @Resource
    private UserDAO userDAO;


    @Test
    public void test() {
        String str = "11111";
        L.info(Integer.parseInt(str, 2));
        L.info("0111110000000".length());
        GeoBlockOrder a = userDAO.select(1);
        L.info("eeee", a);
    }
}
