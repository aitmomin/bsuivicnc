package drh.concour.repositories;

import drh.concour.entities.Center;
import drh.concour.entities.CenterConcour;
import drh.concour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Center getCenterById(long id);
    @Query(value="SELECT c.* " +
            "FROM centre c, personne p " +
            "where p.id = ?1 and c.id=p.centreid ", nativeQuery = true)
    Center getCentreByPersonne(long id);

}
