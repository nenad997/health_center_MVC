package health_center.repositories;

import health_center.models.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepo extends JpaRepository<Nurse, Long> {
}
