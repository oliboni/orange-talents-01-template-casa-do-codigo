package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.validators.ExistValue;
import br.com.zup.casadocodigo.validators.UniqueValue;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;


public class NovoLivroRequest {
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Titulo já cadastrado!")
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String resumo;

    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer numPaginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "ISBN já cadastrado!")
    private String isbn;

    @Future
    @NotNull
//    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataEntradaNoAr;

    @NotNull
    @ExistValue(domainClass = Categoria.class, fieldName = "id", message = "Categoria não encontrada")
    private Long idCategoria;

    @NotNull
    @ExistValue(domainClass = Autor.class, fieldName = "id", message = "Autor não encontrado")
    private Long idAutor;

    public NovoLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, String sumario, @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) Integer numPaginas, @NotBlank String isbn, @Future @NotBlank LocalDate dataEntradaNoAr, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numPaginas = numPaginas;
        this.isbn = isbn;
        this.dataEntradaNoAr = dataEntradaNoAr;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public Livro toLivro(EntityManager manager){
        @NotNull Categoria categoria = manager.find(Categoria.class, idCategoria);
        @NotNull Autor autor = manager.find(Autor.class, idAutor);

        Assert.state(autor!=null,"O autor do livro não foi encontrado");
        Assert.state(categoria!=null,"A categoria não foi encontrado");

        return new Livro(titulo,resumo,sumario,preco,numPaginas,isbn,dataEntradaNoAr,categoria,autor);
    }
}
