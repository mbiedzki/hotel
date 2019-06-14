package pl.biedzki.hotel.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableJpaRepositories("pl.biedzki.hotel.repository")
@EnableTransactionManagement
public class DatabaseConfig {

}
