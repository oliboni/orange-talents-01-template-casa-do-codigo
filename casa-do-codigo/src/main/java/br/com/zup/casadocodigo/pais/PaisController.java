package br.com.zup.casadocodigo.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {
    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    public NovoPaisRequest cadastrar(@RequestBody @Valid NovoPaisRequest novoPaisRequest){
        Pais pais = novoPaisRequest.toPais();
        paisRepository.save(pais);

        return novoPaisRequest;
    }

}
