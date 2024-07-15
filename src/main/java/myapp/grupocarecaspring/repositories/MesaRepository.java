package myapp.grupocarecaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.grupocarecaspring.entities.Mesa;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
}
