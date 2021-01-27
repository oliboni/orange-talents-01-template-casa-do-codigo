package br.com.zup.casadocodigo.validators;

import br.com.zup.casadocodigo.pagamento.NovoFluxoPagamentoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class VerifciaDocumentoCpfCnpjValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoFluxoPagamentoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovoFluxoPagamentoRequest request = (NovoFluxoPagamentoRequest) target;

        if(!request.documentoValido()){
            errors.rejectValue("documento", null, "Documento precisa ser um CPF ou CNPJ válido");
        }
    }
}
