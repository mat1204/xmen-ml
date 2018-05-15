package com.ml.xmen.xmenml.validators;

import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ComprobacionMutanteValidator implements Validator {

    public ComprobacionMutanteValidator() {

    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ComprobacionMutanteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ComprobacionMutanteRequest comprobacionMutanteRequest = (ComprobacionMutanteRequest) target;

        int tamanio = comprobacionMutanteRequest.dna.length;
    }

}
