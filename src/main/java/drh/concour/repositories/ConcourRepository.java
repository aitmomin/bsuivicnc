package drh.concour.repositories;

import drh.concour.entities.Concour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcourRepository  extends JpaRepository<Concour, Long> {

    List<Concour> findConcoursByCenterConcours(long id);
    // Concour getConcourByCentre
}
