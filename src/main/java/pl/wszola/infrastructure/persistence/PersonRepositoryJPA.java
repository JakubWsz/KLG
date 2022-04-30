package pl.wszola.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wszola.infrastructure.entity.Person;

@Repository
public interface PersonRepositoryJPA extends JpaRepository<Person,Long> {
}
