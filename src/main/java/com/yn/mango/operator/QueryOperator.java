package com.yn.mango.operator;

import com.yn.mango.annotation.Mapper;
import com.yn.mango.annotation.Result;
import com.yn.mango.annotation.Results;
import com.yn.mango.binding.InvocationContext;
import com.yn.mango.descriptor.MethodDescriptor;
import com.yn.mango.descriptor.ReturnDescriptor;
import com.yn.mango.jdbc.BoundSql;
import com.yn.mango.jdbc.JdbcOptions;
import com.yn.mango.jdbc.RowMapper;
import com.yn.mango.parser.NodeInfo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangnan on 16/10/24.
 */
public class QueryOperator extends AbstractOperator {

    protected ReturnDescriptor returnDescriptor;

    protected RowMapper<?> rowMapper;

    public QueryOperator(NodeInfo nodeInfo, MethodDescriptor md) {
        super(nodeInfo, md.getDaoClass());
        init(md);
    }

    private void init(MethodDescriptor md) {
        this.returnDescriptor = md.getReturnDescriptor();
        rowMapper = getRowMapper(returnDescriptor.getMappedClass(), returnDescriptor);
    }

    private <T> RowMapper<?> getRowMapper(Class<T> mappedClass, ReturnDescriptor returnDescriptor) {
        Mapper mapper = returnDescriptor.getAnnotation(Mapper.class);
        Results rs = returnDescriptor.getAnnotation(Results.class);
        if (mapper != null) {
            //启用自定义
        }
        if (rs != null) {

        }

        Map<String, String> ptc = new HashMap<String, String>();
        if (rs != null) {
            Result[] results = rs.values();
            if (results != null) {
                for (Result r : results) {
                    ptc.put(r.property().trim(), r.column().trim());
                }
            }
        }

        return new BeanPropertyRowMapper<T>(mappedClass, ptc);
    }

    public Object execute(Object[] values) {
        InvocationContext context = invocationContextFactory.newInvocationContext(values);
        return execute(context);
    }

    private Object execute(InvocationContext context) {
        DataSource ds = dataSourceGenerator.getDataSource(context, daoClass);

        BoundSql sql = context.getBoundSql();
        return executeFromDb(ds, sql);
    }

    private Object executeFromDb(final DataSource ds, final BoundSql sql) {
        Object r;
        boolean success = false;
        r = new QueryVisitor() {
            @Override
            Object visitForList() {
                return null;
            }

            @Override
            Object visitForSet() {
                return null;
            }

            @Override
            Object visitForArray() {
                return null;
            }

            @Override
            Object visitForObject() {
                return jdbcOptions.queryForObject(ds, sql, rowMapper);
            }
        };

        return r;
    }

    abstract class QueryVisitor {
        public Object visit() {
            Object r;
            if (returnDescriptor.isCollection()
                    || returnDescriptor.isListAssignable()) {
                r = visitForList();
            } else if (returnDescriptor.isSetAssignable()) {
                r = visitForSet();
            } else if (returnDescriptor.isArray()) {
                r = visitForArray();
            } else {
                r = visitForObject();
            }
            return r;
        }

        abstract Object visitForList();

        abstract Object visitForSet();

        abstract Object visitForArray();

        abstract Object visitForObject();
    }

}
