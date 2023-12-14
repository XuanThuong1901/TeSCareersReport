package com.ductrung.tescareers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories( basePackages = {"com.ductrung.tescareers.repository" } )
//@EntityScan("com.ductrung.tescareers.entity" )
public class TeSCareersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeSCareersApplication.class, args);
    }

}
