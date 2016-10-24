package com.yn.mango.test;

import com.yn.mango.dao.UserDAO;
import com.yn.mango.operator.Mango;
import com.yn.mango.util.L;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yangnan on 16/10/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MangoTest {

    @Resource
    private ApplicationContext context;

    @Resource
    private UserDAO userDAO;

    @Test
    public void test() {
        int a = userDAO.select();
        L.info("a", a);
    }
}
