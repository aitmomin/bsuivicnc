package drh.concour.repositories;

import drh.concour.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByIdentifier(String identifier);
    //Personne getPersonneByIdentifiant2(String id);
    User findUserByIdentifier(String identifier);
    Boolean existsByIdentifier(String identifier);
    List<User> getUsersByCenter(long doti);
}
