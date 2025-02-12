package health_center.services.implementations;

import health_center.models.Doctor;
import health_center.models.Examination;
import health_center.models.Nurse;
import health_center.models.Patient;
import health_center.repositories.DoctorRepo;
import health_center.repositories.ExaminationRepo;
import health_center.repositories.NurseRepo;
import health_center.repositories.PatientRepo;
import health_center.services.NurseService;
import health_center.util.ExaminationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JPANurseService implements NurseService {
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final NurseRepo nurseRepo;

    private final ExaminationRepo examinationRepo;

    @Autowired
    public JPANurseService(
            PatientRepo patientRepo,
            DoctorRepo doctorRepo,
            NurseRepo nurseRepo,
            ExaminationRepo examinationRepo) {
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
        this.nurseRepo = nurseRepo;
        this.examinationRepo = examinationRepo;
    }

    // Default helper method
    @Override
    public <T> List<T> findAll(JpaRepository<T, Long> repository) {
        return repository.findAll();
    }

    @Override
    public List<Doctor> findAllDoctors() {
        return findAll(doctorRepo);
    }

    @Override
    public List<Patient> findAllPatients() {
        return findAll(patientRepo);
    }

    @Override
    public List<Nurse> findAllNurses() {
        return findAll(nurseRepo);
    }

    @Override
    public Patient save(Patient patient) {
        return patientRepo.save(patient);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepo.save(doctor);
    }

    @Override
    public Nurse save(Nurse nurse) {
        return nurseRepo.save(nurse);
    }

    @Transactional
    @Override
    public Examination createPatientExamination(
            Long patientId,
            Long doctorId,
            LocalDateTime time,
            String room,
            String description) {
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient with id " + patientId + " not found!"));

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor with id " + doctorId + " not found!"));

        if (!patient.getChosenDoctor().equals(doctor)) {
            throw new IllegalArgumentException("Patient can only book an appointment with their chosen doctor!");
        }

        if (time.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Appointment time must be in the future!");
        }

        Examination patientExamination = new Examination(patient, doctor, time, room, description, ExaminationStatus.SCHEDULED);

        doctor.addExamination(patientExamination);
        doctorRepo.save(doctor);

        patient.addExamination(patientExamination);
        patientRepo.save(patient);

        return examinationRepo.save(patientExamination);
    }

    @Override
    public Examination edit(Long examinationId, ExaminationStatus examinationStatus) {
        Examination examination = examinationRepo.findById(examinationId)
                .orElseThrow(() -> new IllegalArgumentException("Examination with id " + examinationId + " not found!"));

        examination.setStatus(examinationStatus);
        return examinationRepo.save(examination);
    }

    //Default helper method
    @Override
    public <T> boolean deleteById(T entity, JpaRepository<T, Long> repository) {
        Long id = null;
        try {
            id = (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException(entity.getClass().getSimpleName() + " with id " + id + " not found!");
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public boolean delete(Patient p) {
        return deleteById(p, patientRepo);
    }

    @Override
    public boolean delete(Doctor d) {
        return deleteById(d, doctorRepo);
    }

    @Override
    public boolean delete(Nurse n) {
        return deleteById(n, nurseRepo);
    }

    //Default helper method
    @Override
    public <T> T findOneById(T entity, JpaRepository<T, Long> repository) {
        Long id = null;
        try {
            id = (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Long finalId = id;
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(entity.getClass().getSimpleName() + " with id " + finalId + " not found!"));
    }

    @Override
    public Patient findOne(Patient patient) {
        return findOneById(patient, patientRepo);
    }

    @Override
    public Doctor findOne(Doctor doctor) {
        return findOneById(doctor, doctorRepo);
    }

    @Override
    public Nurse findOne(Nurse nurse) {
        return findOneById(nurse, nurseRepo);
    }
}
