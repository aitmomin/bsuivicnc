package drh.concour.repositories;

import drh.concour.entities.Room;
import drh.concour.entities.models.Feeds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedsRepository extends JpaRepository<Feeds, Long> {
    @Query(value="select * from feeds order by feed_date DESC", nativeQuery = true)
    List<Feeds> findAllSortedByfeedDate();
}
