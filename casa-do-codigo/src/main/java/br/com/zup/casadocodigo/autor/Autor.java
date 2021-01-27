package br.com.zup.casadocodigo.autor;

import br.com.zup.casadocodigo.validators.UniqueValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String nome;

    @NotBlank
    @Email
    @Column(unique = true, nullable = false)
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "Este Email já está sendo usado!")
    private String email;

    @NotBlank
    @Size(max = 400)
    @Column(nullable = false)
    private String descricao;

    private LocalDateTime dataRegistro = LocalDateTime.now();

    public Autor(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public Autor(){}

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
