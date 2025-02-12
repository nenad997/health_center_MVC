package health_center.repositories;

import health_center.models.Examination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExaminationRepo extends JpaRepository<Examination, Long> {

    @Query("SELECT e FROM Examination e WHERE e.patient.id = :patientId")
    List<Examination> findByScheduledPatientId(@Param("patientId") Long patientId);

    @Query("SELECT e FROM Examination e WHERE e.doctor.id = :doctorId AND e.patient.id = :patientId")
    Examination findByDoctorAndPatient(@Param("doctorId") Long doctorId, @Param("patientId") Long patientId);
}
