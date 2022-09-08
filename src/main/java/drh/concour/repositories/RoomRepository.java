package drh.concour.repositories;

import drh.concour.entities.Room;
import drh.concour.entities.models.ConcourWithRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query(value="select DISTINCT r.*, rc.* from center_concour cc, concour cn, room_concour rc, room r where cn.id = cc.concourid and cn.id = rc.concourid and r.id = rc.roomid and cc.centerid = ?1", nativeQuery = true)
    List<Room> getRoomsByCenterId(long centerID);

    @Query(value="select r.*\n" +
            "from concour cn, room r\n" +
            "where cn.id = r.concourid\n" +
            "\tand r.is_done = 0\n" +
            "\tand cn.id = ?1", nativeQuery = true)
    List<Room> getRoomsByConcourId(long concourID);


}
