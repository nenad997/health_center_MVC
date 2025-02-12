package health_center.services;

import health_center.models.Doctor;
import health_center.models.Examination;
import health_center.models.Nurse;
import health_center.models.Patient;
import health_center.util.ExaminationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface NurseService {
    public default <T> List<T> findAll(JpaRepository<T, Long> repository) {
        return null;
    }

    public List<Doctor> findAllDoctors();

    public List<Patient> findAllPatients();

    public List<Nurse> findAllNurses();

    Patient save(Patient patient);

    Doctor save(Doctor doctor);

    Nurse save(Nurse nurse);

    Examination createPatientExamination(
            Long patientId,
            Long doctorId,
            LocalDateTime time,
            String room,
            String description);

    Examination edit(Long examinationId, ExaminationStatus examinationStatus);

    public default <T> boolean deleteById(T entity, JpaRepository<T, Long> repository) {
        return false;
    }

    public default <T> T findOneById(T entity, JpaRepository<T, Long> repository) {
        return null;
    }

    boolean delete(Patient patient);

    boolean delete(Doctor doctor);

    boolean delete(Nurse nurse);

    Patient findOne(Patient patient);

    Doctor findOne(Doctor doctor);

    Nurse findOne(Nurse nurse);
}
