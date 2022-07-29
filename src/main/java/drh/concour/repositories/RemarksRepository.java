package drh.concour.repositories;

import drh.concour.entities.Remark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemarksRepository extends JpaRepository<Remark, Long> {
}
