package drh.concour.repositories;

import drh.concour.entities.CenterConcour;
import drh.concour.entities.RoomConcour;
import drh.concour.entities.RoomConcourID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomConcourRepository extends JpaRepository<RoomConcour, Long> {

}
