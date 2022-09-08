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
    Optional<User> getUserByUsername(String username);
    User findUserByUsername(String username);
    // User findUserByDoti();
    Boolean existsByUsername(String username);

    @Query(value = "select u.*\n" +
            "from [db_cnc].[dbo].[user] u, center c\n" +
            "where c.id = u.centerid\n" +
            " and c.id = ?1\n" +
            "order by is_responsible desc", nativeQuery = true)
    List<User> getUsersByCenterId(long id);


}
