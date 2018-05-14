
package com.schwertz.carrentalservice.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class that contains custom web mvc configurations.
 *
 * @author mani
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {

    registry.addRedirectViewController("/api/doc", "/api/doc/");
    registry.addViewController("/api/doc/").setViewName(
	    "forward:/api/doc/index.html");
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("*").allowedOrigins("*");
  }
}
