package br.com.zup.casadocodigo.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private EntityManager manager;

    @PostMapping
    public void cadastrar(@RequestBody @Valid NovoLivroRequest novoLivroRequest){
        Livro livro = novoLivroRequest.toLivro(manager);
        livroRepository.save(livro);
    }

    @GetMapping
    public Page<LivroListaDto> listarTodos(Pageable paginacao){
        Page<Livro> livros = livroRepository.findAll(paginacao);

        return LivroListaDto.mostraLista(livros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalheLivroDto(@PathVariable("id") Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado!");
//            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DetalheLivroDto(livro.get()));
    }
}
