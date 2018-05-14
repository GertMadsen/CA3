
package com.schwertz.carrentalservice.configs;

import com.schwertz.carrentalservice.utils.DatabaseSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * Configuration class that registers event listeners to application context
 * that executes on particular event.
 *
 * @author mani
 */
@Configuration
public class EventListenerConfigurations {

  @Autowired
  private DatabaseSeeder databaseSeeder;

  /**
   * Event listener that executes when context loading is completed.
   *
   * @param event
   */
  @EventListener
  public void onContextLoadCompletion(ContextRefreshedEvent event) {

    this.databaseSeeder.seed();
  }
}
