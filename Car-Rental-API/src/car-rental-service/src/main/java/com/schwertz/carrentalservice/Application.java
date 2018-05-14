
package com.schwertz.carrentalservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * Main class that bootstrap the application.
 *
 * @author mani
 */
@SpringBootApplication
public class Application {

  /**
   * Entry point of the application. Bootstrap application as spring
   * application.
   *
   * @param args
   */
  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }

  /**
   * Registering {@link MethodValidationPostProcessor} to enable method level
   * validation.
   *
   * @return
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor() {
    return new MethodValidationPostProcessor();
  }
}
