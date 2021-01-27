package br.com.zup.casadocodigo.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado")
public class EstadoController {

//    @Autowired
//    private EstadoRepository estadoRepository;
    @Autowired
    private EntityManager manager;

    @Transactional
    @PostMapping
    public NovoEstadoRequest cadastrar(@RequestBody @Valid NovoEstadoRequest novoEstadoRequest){

        Estado estado = novoEstadoRequest.toEstado(manager);
        manager.persist(estado);

        return novoEstadoRequest;
    }
}
