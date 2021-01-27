package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.pagamento.endereco.Endereco;

public class NovoFluxoPagamento {
    private String nome;
    private String sobrenome;
    private String email;
    private String documento;
    private Endereco endereco;
    private String telefone;

    public NovoFluxoPagamento(String nome, String sobrenome, String email, String documento, Endereco endereco, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public NovoFluxoPagamento() {
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }
}
