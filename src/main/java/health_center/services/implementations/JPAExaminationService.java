package health_center.services.implementations;

import health_center.models.Doctor;
import health_center.models.Examination;
import health_center.models.Patient;
import health_center.repositories.DoctorRepo;
import health_center.repositories.ExaminationRepo;
import health_center.repositories.PatientRepo;
import health_center.services.ExaminationService;
import health_center.util.ExaminationStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class JPAExaminationService implements ExaminationService {
    private final ExaminationRepo examinationRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;

    @Autowired
    public JPAExaminationService(ExaminationRepo examinationRepo, PatientRepo patientRepo, DoctorRepo doctorRepo) {
        this.examinationRepo = examinationRepo;
        this.patientRepo = patientRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public List<Examination> findAll() {
        return examinationRepo.findAll();
    }

    @Override
    public List<Examination> getExaminationsByPatient(Long patientId) {
        return examinationRepo.findByScheduledPatientId(patientId);
    }

    @Override
    public Examination createExamination(Examination examination) {
        Optional<Patient> foundPatient = patientRepo.findById(examination.getPatient().getId());

        if (foundPatient.isEmpty()) {
            throw new IllegalArgumentException("Patient with id " + examination.getPatient().getId() + " not found!");
        }

        Optional<Doctor> foundDoctor = doctorRepo.findById(examination.getDoctor().getId());

        if (foundDoctor.isEmpty()) {
            throw new IllegalArgumentException("Doctor with id " + examination.getDoctor().getId() + " not found!");
        }

        return examinationRepo.save(examination);
    }

    @Override
    public Examination updateExamination(Long examinationId, String newDescription) {
        Optional<Examination> foundExamination = examinationRepo.findById(examinationId);

        if (foundExamination.isEmpty()) {
            throw new IllegalArgumentException("Examination with id " + examinationId + " not found!");
        }

        Examination toUpdate = foundExamination.get();

        if (toUpdate.getStatus() == ExaminationStatus.REQUESTED) {
            toUpdate.setDescription(newDescription);
            return examinationRepo.save(toUpdate);
        } else {
            throw new IllegalArgumentException("Examination cannot be updated because it is already ACCEPTED!");
        }
    }

    @Override
    public Examination updateExamination(Long examinationId, ExaminationStatus status) {
        Optional<Examination> foundExamination = examinationRepo.findById(examinationId);

        if (foundExamination.isEmpty()) {
            throw new IllegalArgumentException("Examination with id " + examinationId + " not found!");
        }

        Examination toUpdate = foundExamination.get();

        toUpdate.setStatus(status);
        return examinationRepo.save(toUpdate);
    }

    @Override
    public Examination save(Examination examination) {
        return examinationRepo.save(examination);
    }

    @Override
    public Examination findOne(Long id) {
        Optional<Examination> foundExamination = examinationRepo.findById(id);

        if (foundExamination.isEmpty()) {
            throw new IllegalArgumentException("Examination with id " + id + " not found!");
        }

        return foundExamination.get();
    }
}
