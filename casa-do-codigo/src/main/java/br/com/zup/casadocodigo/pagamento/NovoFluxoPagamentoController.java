package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisRepository;
import br.com.zup.casadocodigo.validators.ValidaEstadoPaisValidator;
import br.com.zup.casadocodigo.validators.ValidaPaisSemEstadoValidator;
import br.com.zup.casadocodigo.validators.VerifciaDocumentoCpfCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("pagamento")
public class NovoFluxoPagamentoController {

    @Autowired
    private PaisRepository paisRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ValidaEstadoPaisValidator validaEstadoPaisValidator;
    @Autowired
    private ValidaPaisSemEstadoValidator validaPaisSemEstadoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(new VerifciaDocumentoCpfCnpjValidator()
                            , validaEstadoPaisValidator
                            , validaPaisSemEstadoValidator);
    }

    @PostMapping
    public NovoFluxoPagamento novoPagamento(@RequestBody @Valid NovoFluxoPagamentoRequest novoFluxoPagamentoRequest){
        NovoFluxoPagamento novoFluxoPagamento = null;

        Optional<Pais> pais = paisRepository.findById(novoFluxoPagamentoRequest.getIdPais());

        if (novoFluxoPagamentoRequest.getIdEstado()!=null){
            Optional<Estado> estado = estadoRepository.findById(novoFluxoPagamentoRequest.getIdEstado());
            novoFluxoPagamento = novoFluxoPagamentoRequest.toNovoFluxoPagamento(pais.get(),estado.get());
        } else {
            novoFluxoPagamento = novoFluxoPagamentoRequest.toNovoFluxoPagamento(pais.get(),null);
        }

        return novoFluxoPagamento;
    }
}
