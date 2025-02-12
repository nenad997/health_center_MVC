package health_center.services;

import health_center.models.Examination;
import health_center.util.ExaminationStatus;

import java.time.LocalDateTime;
import java.util.List;

public interface ExaminationService {
    List<Examination> findAll();

    List<Examination> getAllByPatientId(Long patientId);

    Examination save(Long patientId, Long doctorId, LocalDateTime examinationTime, String room, String description);

    Examination save(Examination examination);

    Examination update(Long examinationId, String newDescription);

    Examination update(Long examinationId, ExaminationStatus status);

    Examination findOne(Long id);
}
