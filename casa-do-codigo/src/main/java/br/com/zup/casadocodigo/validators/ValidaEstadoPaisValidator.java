package br.com.zup.casadocodigo.validators;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pagamento.NovoFluxoPagamentoRequest;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
@Component
public class ValidaEstadoPaisValidator implements Validator {
    @Autowired
    private PaisRepository paisRepository;
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
        NovoFluxoPagamentoRequest pagamentoRequest = (NovoFluxoPagamentoRequest) target;

        if (pagamentoRequest.getIdEstado()==null){
            return;
        }

        Optional<Pais> pais = paisRepository.findById(pagamentoRequest.getIdPais());
        Optional<Estado> estado = estadoRepository.findById(pagamentoRequest.getIdEstado());

        if(!estado.get().pertenceAPais(pais.get())){
            errors.rejectValue("idEstado", null, "Este estado não pertence ao país selecionado!");
        }
    }
}
