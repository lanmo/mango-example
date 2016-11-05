package com.yn.mango.parser;

import com.yn.mango.jdbc.SQLType;
import com.yn.mango.util.L;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yangnan on 16/11/5.
 */
public class SqlParser {

    /**
     * sql 格式 select * from user where id=${a} 并不做sql语法等校验
     *
     * @param sql
     * @return
     */
    public static NodeInfo parse(String sql) {

        sql = sql.trim();
        NodeInfo nodeInfo = getNodeInfo(sql);

        //设置sql类型 简单的判断,先将sql全部变成大写
        String exeSQL = nodeInfo.getExeSql().toUpperCase();
        if (exeSQL.startsWith("SELECT")) {
            nodeInfo.setSqlType(SQLType.QUERY);
        }

        return nodeInfo;
    }

    private static NodeInfo getNodeInfo(String sql) {

        Pattern pattern = Pattern.compile("\\$\\{\\w+\\}");
        Matcher matcher = pattern.matcher(sql);

        NodeInfo info = new NodeInfo();

        int count = 0;
        while(matcher.find()) {
            count++;
        }

        info.setConditionCount(count);
        if (count == 0) {
            info.setExeSql(sql);
        }

        StringBuffer buffer = new StringBuffer();
        matcher.reset();
        while(matcher.find()) {
            matcher.appendReplacement(buffer, "?");
        }

        matcher.appendTail(buffer);
        info.setExeSql(buffer.toString());

        return info;
    }
}
