package com.ml.xmen.xmenml.actors.config;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import com.ml.xmen.xmenml.actors.producers.PersistidorActorProducer;
import org.springframework.context.ApplicationContext;

public class ActorPersitidorExtension extends AbstractExtensionId<ActorPersitidorExtension.ActorExt> {

    public static final ActorPersitidorExtension ActorPersitidorExtension = new ActorPersitidorExtension();

    @Override
    public ActorExt createExtension(ExtendedActorSystem extendedActorSystem) {
        return new ActorExt();
    }

    public static class ActorExt implements Extension {
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;
        }

        public Props props(String actorBeanName) {
            return Props.create(
                    PersistidorActorProducer.class, applicationContext, actorBeanName);
        }
    }
}
