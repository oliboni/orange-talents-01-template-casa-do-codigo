package br.com.zup.casadocodigo.estado;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado, Long> {

    List<Estado> findByPaisId(Long idPais);
}
