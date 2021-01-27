package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.DetalheAutorDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetalheLivroDto {

    private DetalheAutorDto detalheAutorDto;
    private String titulo;
    private String isbn;
    private Integer numPaginas;
    private BigDecimal preco;
    private String resumo;
    private String sumario;

    public DetalheLivroDto(Livro livro){
        this.detalheAutorDto = new DetalheAutorDto(livro.getAutor());
        this.titulo = livro.getTitulo();
        this.isbn = livro.getIsbn();
        this.numPaginas = livro.getNumPaginas();
        this.preco = livro.getPreco();
        this.resumo = livro.getResumo();
        this.sumario = livro.getSumario();
    }

    public DetalheLivroDto() {
    }

    public String getTitulo() {
        return titulo;
    }

    public DetalheAutorDto getDetalheAutorDto() {
        return detalheAutorDto;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getNumPaginas() {
        return numPaginas;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }
}
