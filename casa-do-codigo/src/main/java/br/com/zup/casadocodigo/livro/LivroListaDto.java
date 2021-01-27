package br.com.zup.casadocodigo.livro;

import org.springframework.data.domain.Page;

public class LivroListaDto {

    private Long id;
    private String titulo;

    public LivroListaDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public static Page<LivroListaDto> mostraLista(Page<Livro> livros){
        return livros.map(LivroListaDto::new);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
}
