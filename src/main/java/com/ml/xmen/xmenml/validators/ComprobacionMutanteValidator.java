package com.ml.xmen.xmenml.validators;

import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;

@Component
public class ComprobacionMutanteValidator implements Validator {

    private ParametrosADN parametrosADN;
    private Pattern patternADN;

    @Autowired
    public ComprobacionMutanteValidator(ParametrosADN parametrosADN) {
        this.parametrosADN = parametrosADN;
        this.patternADN = Pattern.compile(parametrosADN.getRegexSecuenciaPermitida());
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ComprobacionMutanteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComprobacionMutanteRequest request = (ComprobacionMutanteRequest) target;

        int tamanio = request.getDna().length;

        for (String cadena : request.getDna()) {
            if (cadena.length() != tamanio) {
                errors.reject("adn.errorLongitudCadena", "Longitud de cadena no corresponde con Matriz de ADN");
                break;
            }

            if (!this.secuenciaValida(cadena)) {
                errors.reject("adn.secuenciaInvalida", "Secuencia de caracteres invalidos en cadena de ADN");
                break;
            }
        }
    }


    private Boolean secuenciaValida(String secuenciaAdn) {
        return this.patternADN.matcher(secuenciaAdn).matches();
    }
}
