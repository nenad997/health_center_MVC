package health_center.services;

import health_center.models.Examination;
import health_center.util.ExaminationStatus;

import java.util.List;

public interface DoctorService {
    List<Examination> findAllDoctorExaminations(Long doctorId);

    Examination editDoctorExamination(Long doctorId, Long examinationId, ExaminationStatus examinationStatus);
}
