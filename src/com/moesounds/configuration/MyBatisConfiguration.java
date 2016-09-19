package com.moesounds.configuration;

import java.util.HashSet;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.moesounds.annotation.MoeSoundsDataSource;
import com.moesounds.annotation.MoeSoundsDatabase;
import com.moesounds.configuration.typeHandlers.EnumTypeHandler;
import com.moesounds.configuration.typeHandlers.TypeHandler;
import com.moesounds.dao.Mapper;
import com.moesounds.domain.TypeAlias;
import com.moesounds.domain.enums.MappedEnum;

/**
 * MyBatis configuration class
 * 
 * @author NYPD
 */
@Configuration
@Import({JndiConfiguration.class})
@EnableTransactionManagement
@MapperScan(basePackageClasses = Mapper.class, annotationClass = MoeSoundsDatabase.class)
public class MyBatisConfiguration {

    @Autowired
    @MoeSoundsDataSource
    private DataSource dataSource;

    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() throws NamingException {

        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }

    @Bean
    public SqlSessionFactory sessionFactory() throws Exception {

        String typeAliasesPackage = TypeAlias.class.getPackage().getName();
        String typeHandlersPackage = TypeHandler.class.getPackage().getName();

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

        Resource[] mapperLocations = patternResolver.getResources("classpath:resource/mybatis/mapper/*-mapper.xml");

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLazyLoadTriggerMethods(new HashSet<String>());
        configuration.setUseActualParamName(true);// Default is true, but to make it apparent

        sessionFactory.setTypeAliasesPackage(typeAliasesPackage);
        sessionFactory.setTypeHandlersPackage(typeHandlersPackage);
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(mapperLocations);
        sessionFactory.setConfiguration(configuration);

        this.registerMappedEnumTypeHandlers(configuration.getTypeHandlerRegistry());

        return sessionFactory.getObject();
    }

    private <E extends MappedEnum> void registerMappedEnumTypeHandlers(TypeHandlerRegistry registry) {

        MappedTypes annotation = EnumTypeHandler.class.getAnnotation(MappedTypes.class);

        for (Class<?> mappedType : annotation.value()) {

            if (!mappedType.isEnum() || !MappedEnum.class.isAssignableFrom(mappedType))
                throw new IllegalStateException("Error registering Enum type handlers: " + mappedType.getName()
                + " is either not an enum or does not implement " + MappedEnum.class.getName());

            // It is OK to suppress warnings here, since we do a check to ensure everything goes ok
            // immediately prior to this.
            @SuppressWarnings("unchecked")
            Class<E> mappedEnumType = (Class<E>) mappedType;

            EnumTypeHandler<E> handler = new EnumTypeHandler<E>(mappedEnumType);

            for (Object enumConstant : mappedEnumType.getEnumConstants()) {

                @SuppressWarnings("unchecked")
                Class<E> actualClass = (Class<E>) enumConstant.getClass();
                registry.register(actualClass, handler);
            }
        }
    }

}