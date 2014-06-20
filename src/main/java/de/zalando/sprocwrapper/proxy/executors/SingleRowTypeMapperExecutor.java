package de.zalando.sprocwrapper.proxy.executors;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import de.zalando.sprocwrapper.proxy.InvocationContext;

import de.zalando.typemapper.core.TypeMapperFactory;

/**
 * @author  jmussler
 */
public class SingleRowTypeMapperExecutor implements Executor {
    @Override
    public Object executeSProc(final DataSource ds, final String sql, final Object[] args, final int[] types,
            final InvocationContext invocationContext, final Class<?> returnType) {
        final List<?> list = (new JdbcTemplate(ds)).query(sql, args, types,
                TypeMapperFactory.createTypeMapper(returnType));
        if (!list.isEmpty()) {
            return list.iterator().next();
        }

        return null;
    }

}
