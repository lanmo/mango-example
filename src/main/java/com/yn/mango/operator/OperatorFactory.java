package com.yn.mango.operator;

import com.yn.mango.binding.DefaultParameterContext;
import com.yn.mango.binding.ParameterContext;
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
    private DataSourceFactory dataSourceFactory;


    public OperatorFactory(DataSourceFactory dataSourceFactory, JdbcOptions jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
        this.dataSourceGeneratorFactory = new DataSourceGeneratorFactory(dataSourceFactory);
        this.dataSourceFactory = dataSourceFactory;

    }

    public Operator getOperator(MethodDescriptor md) {

        NodeInfo nodeInfo = SqlParser.parse(md.getSql());
        SQLType sqlType = nodeInfo.getSqlType();

        OperatorType operatorType = getOperatorType(sqlType);
        DataSourceType dataSourceType = getDataSourceType(operatorType);

        ParameterContext parameterContext = DefaultParameterContext.create(md.getParameterDescriptors());

        DataSourceGenerator dataSourceGenerator = dataSourceGeneratorFactory.getDataSourceGenerator(dataSourceType,
                md.getDatabase(), parameterContext);

        Operator operator = null;
        switch (operatorType) {
            case QUERY:
                operator = new QueryOperator(nodeInfo, md);
                break;
        }

        operator.setDataSourceGenerator(dataSourceGenerator);
        operator.setJdbcOperations(jdbcOperations);
        operator.setInvocationContextFactory(InvocationContextFactory.create(parameterContext));

        return operator;
    }

    private OperatorType getOperatorType(SQLType sqlType) {
        OperatorType operatorType = null;
        if (sqlType == SQLType.QUERY) {
            operatorType = OperatorType.QUERY;
        }

        return operatorType;
    }

    private DataSourceType getDataSourceType(OperatorType operatorType) {
        DataSourceType dataSourceType = DataSourceType.MASTER;
        if (operatorType == OperatorType.QUERY) {
            dataSourceType = DataSourceType.SLAVE;
        }

        return dataSourceType;
    }
}
