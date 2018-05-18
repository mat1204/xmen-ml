package com.ml.xmen.xmenml.services.impl;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.util.Timeout;
import akka.pattern.Patterns;
import com.ml.xmen.xmenml.actors.PersistidorActor;
import com.ml.xmen.xmenml.actors.config.ActorPersitidorExtension;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.services.RegistroADNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@Service
public class RegistroADNServiceActor implements RegistroADNService {

    @Autowired
    ActorSystem actorSystem;

    @Override
    public void persistirSecuenciaAdn(SecuenciaADN secuenciaADN) {
        ActorRef persistidorActor = obtenerActor();

        FiniteDuration duration = FiniteDuration.create(3, TimeUnit.SECONDS);
        Timeout timeout = Timeout.durationToTimeout(duration);

        PersistidorActor.MensajeActorPersistidor msg = new PersistidorActor.MensajeActorPersistidor(secuenciaADN);

        Patterns.ask(persistidorActor, msg, timeout );
    }

    ActorRef obtenerActor() {
        ActorRef actorRef = actorSystem.actorOf(
                    ActorPersitidorExtension.ActorPersitidorExtension
                            .get(actorSystem)
                            .props(PersistidorActor.nombreActor));

        return actorRef;
    }
}
