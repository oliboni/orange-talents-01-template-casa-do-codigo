package br.com.zup.casadocodigo.pagamento.endereco;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.pais.Pais;

public class Endereco {
    private String rua;
    private String complemento;
    private String cidade;
    private Pais pais;
    private Estado estado;
    private String cep;

    public Endereco(String rua, String complemento, String cidade, Pais pais, Estado estado, String cep) {
        this.rua = rua;
        this.complemento = complemento;
        this.cidade = cidade;
        this.pais = pais;
        this.estado = estado;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cidade='" + cidade + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", cep='" + cep + '\'' +
                '}';
    }

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getCep() {
        return cep;
    }
}
