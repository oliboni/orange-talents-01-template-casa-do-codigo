package br.com.zup.casadocodigo.validators;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pagamento.NovoFluxoPagamentoRequest;
import br.com.zup.casadocodigo.pais.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Component
public class ValidaPaisSemEstadoValidator implements Validator {
    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoFluxoPagamentoRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()){
            return;
        }

        NovoFluxoPagamentoRequest request = (NovoFluxoPagamentoRequest) target;
        if (request.getIdEstado()==null){
            List<Estado> estado = estadoRepository.findByPaisId(request.getIdPais());
            if (!estado.isEmpty()) {
                errors.rejectValue("idEstado",null,"Existem estados para o país selecionado, favor selecionar um estado!");
            }
        }
    }
}
