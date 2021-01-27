package br.com.zup.casadocodigo.pais;

import br.com.zup.casadocodigo.validators.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome", message = "Já existe uma país com este nome!")
    private String nome;

    public NovoPaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public NovoPaisRequest() {
    }

    public String getNome() {
        return nome;
    }


    public Pais toPais() {
        return new Pais(this.nome);
    }
}
