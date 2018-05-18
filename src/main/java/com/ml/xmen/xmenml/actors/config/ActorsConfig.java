package com.ml.xmen.xmenml.actors.config;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActorsConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem() {
        ActorSystem system = ActorSystem.create("xmen-ml-akka");
        ActorPersitidorExtension.ActorPersitidorExtension.get(system)
                .initialize(applicationContext);
        return system;
    }
}
