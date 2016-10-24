package com.yn.mango.xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by yangnan on 16/10/24.
 */
public class ProcurementSystem {
    @Element
    private String url;
    @Element(name = "databasename")
    private String dataBaseName;

    @Attribute(name = "username")
    private String userName;

    @Attribute(name = "userpassword")
    private String userPasswd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

}
