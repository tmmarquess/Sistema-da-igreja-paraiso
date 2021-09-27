package br.com.igrejaparaiso.Igrejaparaiso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;

public interface eventoRepository extends JpaRepository<Evento,Long>{
    
}
