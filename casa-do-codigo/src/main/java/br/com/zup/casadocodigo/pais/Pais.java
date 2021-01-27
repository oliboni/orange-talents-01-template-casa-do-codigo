package br.com.zup.casadocodigo.pais;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    public Pais() {
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pais)) return false;
        Pais pais = (Pais) o;
        return getNome().equals(pais.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }
}
