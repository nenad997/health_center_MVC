package health_center.models;

import health_center.models.super_classes.User;
import health_center.util.InsuranceCategory;
import health_center.util.UserRole;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patient")
public class Patient extends User {
    @ManyToOne
    @JoinColumn(name = "chosen_doctor_id", nullable = false)
    private Doctor chosenDoctor;

    @Column(name = "health_card_number")
    private String healthCardNumber;

    @Column(name = "health_card_expiration")
    private LocalDateTime healthCardExpiration;

    @Enumerated(EnumType.STRING)
    @Column(name = "insurance_category")
    private InsuranceCategory insuranceCategory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Examination> examinations = new ArrayList<>();

    public Patient() {
    }

    public Patient(String firstName, String lastName, String jmbg, String sex, String address, String phoneNumber, String userName, String password, Doctor chosenDoctor,
                   String healthCardNumber, LocalDateTime healthCardExpiration, InsuranceCategory insuranceCategory) {
        super(firstName, lastName, jmbg, sex, address, phoneNumber, userName, password, UserRole.PATIENT);
        this.chosenDoctor = chosenDoctor;
        this.healthCardNumber = healthCardNumber;
        this.healthCardExpiration = healthCardExpiration;
        this.insuranceCategory = insuranceCategory;
    }

    public Doctor getChosenDoctor() {
        return chosenDoctor;
    }

    public void setChosenDoctor(Doctor chosenDoctor) {
        this.chosenDoctor = chosenDoctor;
    }

    public String getHealthCardNumber() {
        return healthCardNumber;
    }

    public void setHealthCardNumber(String healthCardNumber) {
        this.healthCardNumber = healthCardNumber;
    }

    public LocalDateTime getHealthCardExpiration() {
        return healthCardExpiration;
    }

    public void setHealthCardExpiration(LocalDateTime healthCardExpiration) {
        this.healthCardExpiration = healthCardExpiration;
    }

    public InsuranceCategory getInsuranceCategory() {
        return insuranceCategory;
    }

    public void setInsuranceCategory(InsuranceCategory insuranceCategory) {
        this.insuranceCategory = insuranceCategory;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public void addExamination(Examination examination) {
        if (!examination.getScheduledPatient().equals(this)) {
            examination.setScheduledPatient(this);
        }
        this.examinations.add(examination);
    }
}
