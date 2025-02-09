package health_center.models;

import health_center.util.ExaminationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "examination")
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient scheduledPatient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor examinationDoctor;

    @Column(name = "examination_time")
    private LocalDateTime examinationTime;

    @Column(name = "room")
    private String room;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ExaminationStatus status;

    public Examination() {
    }

    public Examination(Patient scheduledPatient, Doctor examinationDoctor, LocalDateTime examinationTime, String room, String description) {
        this.scheduledPatient = scheduledPatient;
        this.examinationDoctor = examinationDoctor;
        this.examinationTime = examinationTime;
        this.room = room;
        this.description = description;
        this.status = ExaminationStatus.REQUESTED;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getScheduledPatient() {
        return scheduledPatient;
    }

    public void setScheduledPatient(Patient scheduledPatient) {
        this.scheduledPatient = scheduledPatient;
    }

    public Doctor getExaminationDoctor() {
        return examinationDoctor;
    }

    public void setExaminationDoctor(Doctor examinationDoctor) {
        this.examinationDoctor = examinationDoctor;
    }

    public LocalDateTime getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(LocalDateTime examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ExaminationStatus getStatus() {
        return status;
    }

    public void setStatus(ExaminationStatus status) {
        this.status = status;
    }
}
