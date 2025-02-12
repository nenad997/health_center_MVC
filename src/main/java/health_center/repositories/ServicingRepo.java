package health_center.repositories;

import health_center.models.Servicing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicingRepo extends JpaRepository<Servicing, Long> {
}
