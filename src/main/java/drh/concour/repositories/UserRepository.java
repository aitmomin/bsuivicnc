package drh.concour.repositories;

import drh.concour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
//@Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByUsername(String identifier);
    // Personne getPersonneByIdentifiant2(String id);
    User findUserByUsername(String identifier);
    Boolean existsByUsername(String identifier);
    List<User> getUsersByCenterId(long id);


    @Query(value="select DISTINCT *\n" +
            "from [user] u, center c, center_concour cc, concour cn, room_concour rc, room r\n" +
            "where u.centerid = c.id\n" +
            "\tand c.id = cc.centerid\n" +
            "\tand c.id = cc.concourid\n" +
            "\tand c.id = rc.concourid\n" +
            "\tand r.id = rc.roomid\n" +
            "\tand u.identifier like ?1", nativeQuery = true)
    User findUserByIdentifier2(String id);
}
