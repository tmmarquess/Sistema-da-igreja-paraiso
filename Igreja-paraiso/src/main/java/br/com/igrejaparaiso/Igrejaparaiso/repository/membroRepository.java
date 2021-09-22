package br.com.igrejaparaiso.Igrejaparaiso.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;

public interface membroRepository extends JpaRepository<Membro,Long> {

 //List<Membro> findByemailAndsenha(String email,String senha);
}
