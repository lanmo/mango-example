package com.yn.mango.operator;

import com.yn.mango.datasource.DataSourceFactory;
import com.yn.mango.descriptor.MethodDescriptor;
import com.yn.mango.jdbc.*;
import com.yn.mango.parser.NodeInfo;
import com.yn.mango.parser.SqlParser;

/**
 * Created by yangnan on 16/11/5.
 */
public class OperatorFactory {

    private JdbcOptions jdbcOperations;
    private DataSourceGeneratorFactory dataSourceGeneratorFactory;

    public OperatorFactory(DataSourceGeneratorFactory dataSourceGeneratorFactory, JdbcOptions jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.dataSourceGeneratorFactory = dataSourceGeneratorFactory;
    }

    public Operator getOperator(MethodDescriptor md) {

        NodeInfo nodeInfo = SqlParser.parse(md.getSql());
        SQLType sqlType = nodeInfo.getSqlType();
        DataSourceType dataSourceType = getDataSourceType(sqlType);

        DataSourceGenerator dataSourceGenerator = dataSourceGeneratorFactory.getDataSourceGenerator(dataSourceType, md.getDatabase());
        return null;
    }

    private DataSourceType getDataSourceType(SQLType sqlType) {
        DataSourceType dataSourceType = DataSourceType.MASTER;
        if (sqlType == SQLType.QUERY) {
            dataSourceType = DataSourceType.SLAVE;
        }

        return dataSourceType;
    }
}
