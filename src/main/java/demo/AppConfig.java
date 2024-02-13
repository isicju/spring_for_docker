package demo;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfig {

    @Value("${DB_PASSWORD}")
    private String password;
    @Value("${DB_USER}")
    private String user;
    @Value("${DB_URL}")
    private String url;

    @Value("${EXTERNAL_EMPLOYEES_URL}")
    private String externalEmployeeUrl;

    @Value("${REDIS_HOST}")
    private String redisHost;

    @Value("${REDIS_PORT}")
    private Integer redisPort;

    @Value("${REDIS_PASSWORD}")
    private String redisPassword;

    @Bean
    JdbcTemplate mariaDbJdbc(DataSource mariaDbDataSource){
        return new JdbcTemplate(mariaDbDataSource);
    }

    @Bean
    public DataSource mariaDbDataSource() throws SQLException {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    RestTemplate employeeTemplate(){
        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory(externalEmployeeUrl);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setUriTemplateHandler(uriBuilderFactory);
        return  restTemplate;
    }

    @Bean
    Jedis redis(){
        HostAndPort hostAndPort = new HostAndPort(redisHost, redisPort);
        Jedis jedis = new Jedis(hostAndPort);
        jedis.auth(redisPassword);
        return jedis;
    }



}
