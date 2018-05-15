package com.ml.xmen.xmenml.comprobadores;

public interface ComprobadorADN {

    public Boolean contieneSecuenciaMutante(String cadenaADN);

    public Boolean poseeGenMutante(Integer numeroCoincidencias);

    public Boolean esADNComprobable(String cadenaADN);
}
