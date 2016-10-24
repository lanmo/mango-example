package com.yn.mango.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by yangnan on 16/10/24.
 * XML对象解析
 */
@Root
public class Config {
    @ElementList(name = "testEnvCofig",entry = "testEnvCofig", inline = true)
    private List<TestEnvCofig> testEnvCofig;

    public List<TestEnvCofig> getTestEnvCofig() {
        return testEnvCofig;
    }

    public void setTestEnvCofig(List<TestEnvCofig> testEnvCofig) {
        this.testEnvCofig = testEnvCofig;
    }
}
