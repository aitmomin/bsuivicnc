package drh.concour.repositories;

import drh.concour.entities.Center;
import drh.concour.entities.CenterConcour;
import drh.concour.entities.Room;
import drh.concour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CenterRepository extends JpaRepository<Center, Long> {
    Center getCenterById(long id);

    @Query(value="select COUNT(*) from center", nativeQuery = true)
    long countAllCenters();

    @Query(value="select COUNT(*) from center where is_ready = 1", nativeQuery = true)
    long countAllReadyCenters();

    @Query(value="select COUNT(*) from center where is_opened = 1", nativeQuery = true)
    long countAllOpenedCenters();

    @Query(value="select COUNT(*) from center where is_closed = 1", nativeQuery = true)
    long countAllClosedCenters();

    @Query(value="select COUNT(*) from center where is_distributed = 1", nativeQuery = true)
    long countAllDistributedCenters();

    @Query(value="select COUNT(*) from center where is_end = 1", nativeQuery = true)
    long countAllEndedCenters();

    @Query(value="select SUM(r.absence)\n" +
            "from center c, center_concour cc, concour cn, room r\n" +
            "where c.id = cc.centerid\n" +
            "\tand cc.concourid = cn.id\n" +
            "\tand cn.id = r.concourid\n" +
            "\tand c.id = ?1", nativeQuery = true)
    long sumAbsenceByCenter(long id);

    @Query(value="select SUM(r.presence)\n" +
            "from center c, center_concour cc, concour cn, room r\n" +
            "where c.id = cc.centerid\n" +
            "\tand cc.concourid = cn.id\n" +
            "\tand cn.id = r.concourid\n" +
            "\tand c.id = ?1", nativeQuery = true)
    long sumPresenceByCenter(long id);

    @Query(value="select SUM(candidates) from center", nativeQuery = true)
    long sumCandidatesOfAllCenters();

    @Query(value="select SUM(presence) from center", nativeQuery = true)
    long sumPresenceOfAllCenters();

    @Query(value="select SUM(absence) from center", nativeQuery = true)
    long sumAbsenceOfAllCenters();

    @Query(value="select SUM(reports) from center", nativeQuery = true)
    long sumReportsOfAllCenters();
}
