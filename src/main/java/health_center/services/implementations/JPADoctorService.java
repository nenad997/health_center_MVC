package health_center.services.implementations;

import health_center.models.Doctor;
import health_center.models.Examination;
import health_center.repositories.DoctorRepo;
import health_center.repositories.ExaminationRepo;
import health_center.services.DoctorService;
import health_center.util.ExaminationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPADoctorService implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final ExaminationRepo examinationRepo;

    @Autowired
    public JPADoctorService(DoctorRepo doctorRepo, ExaminationRepo examinationRepo) {
        this.doctorRepo = doctorRepo;
        this.examinationRepo = examinationRepo;
    }

    @Override
    public List<Examination> findAllDoctorExaminations(Long doctorId) {
        Doctor foundDoctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found!"));

        return foundDoctor.getExaminations();
    }

    @Override
    public Examination editDoctorExamination(Long doctorId, Long patientId, ExaminationStatus examinationStatus) {
        Examination foundExamination = examinationRepo.findByDoctorAndPatient(doctorId, patientId);

        if (foundExamination == null) {
            throw new IllegalArgumentException("Examination not found!");
        }

        foundExamination.setStatus(examinationStatus);
        return examinationRepo.save(foundExamination);
    }
}
