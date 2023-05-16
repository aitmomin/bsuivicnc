package drh.concour.repositories;

import drh.concour.entities.Room;
import drh.concour.entities.models.ConcourWithRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConcourWithRoomsRepository extends JpaRepository<ConcourWithRooms, Long> {
    // old version ...
    /*@Query(value="select c.city, r.concourid, r.id, cn.title, cn.rank, r.name, r.candidates, r.presence, r.absence\n" +
            "    from center c, center_concour cc, concour cn, room r\n" +
            "    where c.id = cc.centerid\n" +
            "    and cc.concourid = cn.id\n" +
            "    and cn.id = r.concourid\n" +
            "    and r.is_done = 1\n" +
            "    and c.id = ?1", nativeQuery = true)*/


    @Query(value="select c.address, c.city, c.jury, r.concourid, r.id, cn.title, cn.rank, r.name, r.candidates, r.presence, r.absence, r.[from], r.[to], r.is_done, r.is_affected\n" +
            "from center c, center_concour cc, concour cn, room r\n" +
            "where c.id = cc.centerid\n" +
            "and cc.concourid = cn.id\n" +
            "and cc.concourid = r.concourid\n" +
            "and cc.centerid = r.centerid\n" +
            "and c.id = ?1", nativeQuery = true)
    List<ConcourWithRooms> AllRoomsByCenterId(long centerID);

    @Query(value="select c.address, c.city, c.jury, r.concourid, r.id, cn.title, cn.rank, r.name, r.candidates, r.presence, r.absence, r.[from], r.[to], r.is_done, r.is_affected\n" +
            "from center c, center_concour cc, concour cn, room r\n" +
            "where c.id = cc.centerid\n" +
            "and cc.concourid = cn.id\n" +
            "and cc.concourid = r.concourid\n" +
            "and cc.centerid = r.centerid", nativeQuery = true)
    List<ConcourWithRooms> AllRoomsOfAllCenters();

    @Query(value="select count(*)\n" +
            "from center c, center_concour cc, concour cn, room r\n" +
            "where c.id = cc.centerid\n" +
            "and cc.concourid = cn.id\n" +
            "and cc.concourid = r.concourid\n" +
            "and cc.centerid = r.centerid\n" +
            "and c.id = ?1\n" +
            "and r.is_done = 0", nativeQuery = true)
    long countAllRoomsByCenterId(long centerID);

}
