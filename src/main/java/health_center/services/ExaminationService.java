package health_center.services;

import health_center.models.Examination;
import health_center.util.ExaminationStatus;

import java.util.List;

public interface ExaminationService {
    List<Examination> findAll();

    List<Examination> getExaminationsByPatient(Long patientId);

    Examination createExamination(Examination examination);

    Examination updateExamination(Long examinationId, String newDescription);

    Examination updateExamination(Long examinationId, ExaminationStatus status);

    Examination save(Examination examination);

    Examination findOne(Long id);
}
