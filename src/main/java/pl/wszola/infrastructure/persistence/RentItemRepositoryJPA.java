package pl.wszola.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wszola.infrastructure.entity.RentItem;

@Repository
public interface RentItemRepositoryJPA extends JpaRepository<RentItem,Long> {
}
