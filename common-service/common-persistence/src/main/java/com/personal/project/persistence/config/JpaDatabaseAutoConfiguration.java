package com.personal.project.persistence.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.personal.project.*.infrastructure.*")
@EnableTransactionManagement
public class JpaDatabaseAutoConfiguration {
}
