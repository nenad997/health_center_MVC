package health_center.models;

import health_center.models.super_classes.User;
import health_center.util.UserRole;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor extends User {
    @Column(name = "salary", nullable = false)
    private double salary;

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private _Service service;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Patient> patients = new ArrayList<>();

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Examination> examinations = new ArrayList<>();

    public Doctor() {
    }

    public Doctor(String firstName, String lastName, String jmbg, String sex, String address, String phoneNumber, String userName, String password, double salary, String specialization, _Service service) {
        super(firstName, lastName, jmbg, sex, address, phoneNumber, userName, password, UserRole.DOCTOR);
        this.salary = salary;
        this.specialization = specialization;
        this.service = service;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public _Service getService() {
        return service;
    }

    public void setService(_Service service) {
        if (!service.getDoctors().contains(this)) {
            service.getDoctors().add(this);
        }
        this.service = service;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Examination> getExaminations() {
        return examinations;
    }

    public void setExaminations(List<Examination> examinations) {
        this.examinations = examinations;
    }

    public void addPatient(Patient patient) {
        if (patient.getChosenDoctor() != null && !patient.getChosenDoctor().equals(this)) {
            patient.setChosenDoctor(this);
        }
        this.patients.add(patient);
    }

    public void addExamination(Examination examination) {
        if (examination.getExaminationDoctor() != null && !examination.getExaminationDoctor().equals(this)) {
            examination.setExaminationDoctor(this);
        }
        this.examinations.add(examination);
    }
}
