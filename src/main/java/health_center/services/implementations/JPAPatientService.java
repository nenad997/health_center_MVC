package health_center.services.implementations;

import health_center.models.Examination;
import health_center.repositories.ExaminationRepo;
import health_center.services.PatientService;
import health_center.util.ExaminationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAPatientService implements PatientService {
    private final ExaminationRepo examinationRepo;

    @Autowired
    public JPAPatientService(ExaminationRepo examinationRepo) {
        this.examinationRepo = examinationRepo;
    }

    @Override
    public List<Examination> findMyExaminations(Long patientId) {
        return examinationRepo.findByScheduledPatientId(patientId);
    }

    @Override
    public Examination createExamination(String description) {
        return examinationRepo.save(new Examination(null, null, null, null, description));
    }

    @Override
    public Examination edit(Long examinationId, ExaminationStatus examinationStatus) {
        Examination examination = examinationRepo.findById(examinationId)
                .orElseThrow(() -> new IllegalArgumentException("Examination not found!"));

        if (examination.getStatus() == ExaminationStatus.SCHEDULED) {
            throw new IllegalArgumentException("Examination is already scheduled and cannot be canceled!");
        }

        examination.setStatus(examinationStatus);
        return examinationRepo.save(examination);
    }

    @Override
    public boolean cancelExamination(Long examinationId) {
        Examination examination = examinationRepo.findById(examinationId)
                .orElseThrow(() -> new IllegalArgumentException("Examination not found!"));

        if (examination.getStatus() == ExaminationStatus.SCHEDULED) {
            throw new IllegalArgumentException("Not allowed examination is already scheduled!");
        }

        examination.setStatus(ExaminationStatus.CANCELLED);
        examinationRepo.save(examination);
        return true;
    }
}
