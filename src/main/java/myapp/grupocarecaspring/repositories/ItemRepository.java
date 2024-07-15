package myapp.grupocarecaspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import myapp.grupocarecaspring.entities.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
}
