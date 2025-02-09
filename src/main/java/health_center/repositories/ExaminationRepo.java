package health_center.repositories;

import health_center.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationRepo extends JpaRepository<Examination, Long> {
}
