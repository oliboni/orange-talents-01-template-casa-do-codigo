package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.validators.ExistValue;
import br.com.zup.casadocodigo.validators.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovoEstadoRequest {
    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome", message = "Já existe um estado com este nome!")
    private String nome;

    @NotNull
    @ExistValue(domainClass = Pais.class, fieldName = "id", message = "País não encontrado!")
    private Long idPais;

    public NovoEstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toEstado(EntityManager manager){
        Pais pais = manager.find(Pais.class, idPais);
        Assert.state(pais!=null, "País não encontrado!");

        return new Estado(this.nome, pais);
    }
}
