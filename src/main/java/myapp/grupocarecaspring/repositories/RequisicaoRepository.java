package myapp.grupocarecaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.grupocarecaspring.entities.Requisicao;

@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {
}