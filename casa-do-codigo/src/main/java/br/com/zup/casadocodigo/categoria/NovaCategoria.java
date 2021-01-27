package br.com.zup.casadocodigo.categoria;

import br.com.zup.casadocodigo.validators.UniqueValue;

import javax.validation.constraints.NotBlank;

public class NovaCategoria {
    @NotBlank
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome da categoria já cadastrado!")
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria toCategoria(){
        return new Categoria(nome);
    }
}
