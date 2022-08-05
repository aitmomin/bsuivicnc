package drh.concour.repositories;

import drh.concour.entities.CenterConcour;
import drh.concour.entities.CenterConcourID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterConcourRepository extends JpaRepository<CenterConcour, Long> {
    // List<CenterConcour> getCenterConcourByCenter(long id);

    List<CenterConcour> getCenterConcoursByCenterId(long id);
}
