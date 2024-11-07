package edu.iuh.fit.lad5.dbConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DsConfig {
    @Bean
    @Scope("singleton")
    public DataSource mariadbDataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.mariadb.jdbc.Driver");
        ds.setUrl("jdbc:mariadb://localhost:3306/lad5");
        ds.setUsername("root");
        ds.setPassword("root");
        return ds;
    }
}
