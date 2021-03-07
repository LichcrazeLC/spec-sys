package com.utm.specsys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.utm.specsys.repositories.*;

import java.util.ArrayList;

import com.utm.specsys.models.*;


@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(SpecRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new Spec(12345L, "1998-03-04", "2012-03-07", new ArrayList<ResourceType>(), new ArrayList<Trait>(), new ArrayList<Example>(), new ArrayList<DataType>(), new ArrayList<Endpoint>())));
    };
  }
}