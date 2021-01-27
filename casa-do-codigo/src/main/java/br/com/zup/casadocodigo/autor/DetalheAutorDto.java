package br.com.zup.casadocodigo.autor;

public class DetalheAutorDto {
    private String nome;
    private String descricao;

    public DetalheAutorDto(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();
    }

    public DetalheAutorDto() {
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
