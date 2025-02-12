package health_center.services;

import health_center.models.Examination;
import health_center.util.ExaminationStatus;

import java.util.List;

public interface PatientService {
    List<Examination> findMyExaminations(Long patientId);

    Examination createExamination(String description);

    Examination edit(Long examinationId, ExaminationStatus examinationStatus);

    boolean cancelExamination(Long examinationId);
}
