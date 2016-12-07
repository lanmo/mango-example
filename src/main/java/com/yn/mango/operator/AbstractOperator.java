package com.yn.mango.operator;

import com.yn.mango.jdbc.DataSourceGenerator;
import com.yn.mango.jdbc.JdbcOptions;
import com.yn.mango.parser.NodeInfo;

/**
 * Created by yangnan on 16/10/24.
 */
public abstract class AbstractOperator implements Operator {

    private NodeInfo nodeInfo;

    protected JdbcOptions jdbcOptions;

    protected Class<?> daoClass;

    protected DataSourceGenerator dataSourceGenerator;

    protected InvocationContextFactory invocationContextFactory;

    public AbstractOperator(NodeInfo nodeInfo, Class<?> daoClass) {
        this.nodeInfo = nodeInfo;
        this.daoClass = daoClass;
    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public JdbcOptions getJdbcOptions() {
        return jdbcOptions;
    }

    public void setJdbcOptions(JdbcOptions jdbcOptions) {
        this.jdbcOptions = jdbcOptions;
    }

    public Class<?> getDaoClass() {
        return daoClass;
    }

    public void setDaoClass(Class<?> daoClass) {
        this.daoClass = daoClass;
    }

    public DataSourceGenerator getDataSourceGenerator() {
        return dataSourceGenerator;
    }

    public void setDataSourceGenerator(DataSourceGenerator dataSourceGenerator) {
        this.dataSourceGenerator = dataSourceGenerator;
    }

    public void setJdbcOperations(JdbcOptions jdbcOperations) {
        this.jdbcOptions = jdbcOperations;
    }

    public void setInvocationContextFactory(InvocationContextFactory invocationContextFactory) {
        this.invocationContextFactory = invocationContextFactory;
    }
}
