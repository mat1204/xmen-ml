package com.ml.xmen.xmenml.validator;

import com.ml.xmen.xmenml.config.ParametrosADN;
import com.ml.xmen.xmenml.controllers.requestbody.ComprobacionMutanteRequest;
import com.ml.xmen.xmenml.validators.ComprobacionMutanteValidator;
import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

public class ComprobacionMutanteValidatorTest {

    @Test
    public void validacionLongitudCadenasConErrorLongitudTest() {

        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(new ParametrosADN());
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AAB", "AA"));
        Errors errors = new BeanPropertyBindingResult(request, "request");


        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertTrue(errors.hasErrors());
    }

    @Test
    public void validacionLongitudCadenasTest() {

        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(new ParametrosADN());
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AABT", "AATT", "AAWR", "AWEW"));
        Errors errors = new BeanPropertyBindingResult(request, "request");

        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertFalse("No debe haber errores de longitud", errors.hasErrors());
    }

    @Test
    public void validacionLongitudCadenasConUnaCadenaIncorrectaTest() {

        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(new ParametrosADN());
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AABT", "AATT", "AA", "AWWR"));
        Errors errors = new BeanPropertyBindingResult(request, "request");

        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertTrue("Debe haber errores de longitud en la tercer cadena", errors.hasErrors());
        Assert.assertEquals("Debe tener el mismo codigo de error", "adn.errorLongitudCadena", errors.getAllErrors().get(0).getCode());

    }

    @Test
    public void validacionSecuenciaADNCorrectaTest() {

        ParametrosADN parametrosADN = new ParametrosADN();
        parametrosADN.setSecuenciaPermitida("ABC");
        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(parametrosADN);
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AAA", "AAB", "BCC"));
        Errors errors = new BeanPropertyBindingResult(request, "request");

        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertFalse("No Debe haber errores de secuencias no permitidas", errors.hasErrors());
    }

    @Test
    public void validacionSecuenciaADNIncorrectaTest() {

        ParametrosADN parametrosADN = new ParametrosADN();
        parametrosADN.setSecuenciaPermitida("ABC");
        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(parametrosADN);
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AAB", "AAB", "TCC"));
        Errors errors = new BeanPropertyBindingResult(request, "request");

        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertTrue("Debe haber errores de secuencia en la tercer cadena", errors.hasErrors());
        Assert.assertEquals("Debe tener el mismo codigo de error", "adn.secuenciaInvalida", errors.getAllErrors().get(0).getCode());
    }

    @Test
    public void validacionSecuenciaADNIncorrectaConCadenasLargasTest() {

        ParametrosADN parametrosADN = new ParametrosADN();
        parametrosADN.setSecuenciaPermitida("ABC");
        ComprobacionMutanteValidator comprobacionMutanteValidator = new ComprobacionMutanteValidator(parametrosADN);
        ComprobacionMutanteRequest request = new ComprobacionMutanteRequest();
        request.setDna(Arrays.array("AABBBACC", "AABBBACC","AABBBACC","AABBBACC","AABBBACC","AABBBACC","AABBBATC", "AABBBACC"));
        Errors errors = new BeanPropertyBindingResult(request, "request");

        comprobacionMutanteValidator.validate(request, errors);

        Assert.assertTrue("Debe haber errores de secuencia en la septima cadena cadena", errors.hasErrors());
        Assert.assertEquals("Debe tener el mismo codigo de error", "adn.secuenciaInvalida", errors.getAllErrors().get(0).getCode());
    }


    @Test
    public void pruebaRegex() {
        Pattern pattern = Pattern.compile("^[ATBC]*$");

        Assert.assertTrue(pattern.matcher("ABC").matches());
        Assert.assertFalse(pattern.matcher("ABCM").matches());
        Assert.assertFalse(pattern.matcher("DDDDDDDDDDDDDDDDDDDD").matches());
        Assert.assertTrue(pattern.matcher("ATTTTTBBBBBCCCCCC").matches());

    }

}
