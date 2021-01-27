package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.pagamento.endereco.Endereco;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.validators.ExistValue;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class NovoFluxoPagamentoRequest {
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String documento;
    @NotBlank
    private String telefone;
    @NotBlank
    private String rua;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    @ExistValue(domainClass = Pais.class, fieldName = "id", message = "País não encontrado")
    private Long idPais;
    @ExistValue(domainClass = Estado.class, fieldName = "id", message = "Estado não encontrado")
    private Long idEstado;
    @NotBlank
    private String cep;

    public NovoFluxoPagamentoRequest(@NotBlank String nome, @NotBlank String sobrenome, @NotBlank @Email String email, @NotBlank String documento, @NotBlank String telefone, @NotBlank String rua, @NotBlank String complemento, @NotBlank String cidade, @NotBlank Long idPais, Long idEstado, @NotBlank String cep) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.telefone = telefone;
        this.rua = rua;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.cep = cep;
    }

    public String getDocumento() {
        return documento;
    }

    public NovoFluxoPagamento toNovoFluxoPagamento(Pais pais, Estado estado) {

        Endereco endereco = new Endereco(rua,complemento,cidade,pais,estado,cep);

        return new NovoFluxoPagamento(nome,sobrenome,email,documento,endereco,telefone);
    }

    public boolean documentoValido() {
        Assert.hasLength(documento,"Você não deveria validar um documetno se ele não tiver sido preenchido");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento,null);
    }

    public Long getIdPais() {
        return this.idPais;
    }

    public Long getIdEstado() {
        return this.idEstado;
    }
}
