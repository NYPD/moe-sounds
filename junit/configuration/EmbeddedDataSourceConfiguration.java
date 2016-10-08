package configuration;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.moesounds.annotation.MoeSoundsDataSource;
import com.moesounds.annotation.TestProfile;

/**
* This class is use to instantiate and configure beans defined for DataSources. Typically used for unit testing
*
* @author NYPD
*/
@Configuration
@EnableTransactionManagement
@TestProfile
public class EmbeddedDataSourceConfiguration {
    
	@Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() throws NamingException {	
        DataSource dataSource = getMoeSoundsDataSource();

        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);

        return dataSourceTransactionManager;
    }

    @Bean
    @MoeSoundsDataSource
    public DataSource getMoeSoundsDataSource() throws NamingException {
    	
        EmbeddedDatabase datasource = new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();

        return datasource;
    }
    
}
