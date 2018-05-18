package com.ml.xmen.xmenml.actors;

import akka.actor.UntypedActor;
import com.ml.xmen.xmenml.domain.SecuenciaADN;
import com.ml.xmen.xmenml.entity.RegistroADN;
import com.ml.xmen.xmenml.repository.RegistroADNRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PersistidorActor  extends UntypedActor {

    public static final String nombreActor = "persistidorActor";

    @Autowired
    private RegistroADNRepository registroADNRepository;

    @Override
    public void onReceive(Object msg) throws Throwable {
        if (msg instanceof MensajeActorPersistidor)
            this.persistir(MensajeActorPersistidor.class.cast(msg).getSecuenciaADN());
        else
            unhandled(msg);
    }

    public void persistir(SecuenciaADN secuenciaADN) {
        RegistroADN registroADN = new RegistroADN();

        registroADN.setSecuenciasADN(secuenciaADN.serializarADN());
        registroADN.setEsMutante(secuenciaADN.esMutante());

        this.registroADNRepository.save(registroADN);
    }

    public static class MensajeActorPersistidor {

        private SecuenciaADN secuenciaADN;

        public MensajeActorPersistidor(SecuenciaADN secuenciaADN) {
            this.secuenciaADN = secuenciaADN;
        }

        public SecuenciaADN getSecuenciaADN() {
            return secuenciaADN;
        }
    }

}
