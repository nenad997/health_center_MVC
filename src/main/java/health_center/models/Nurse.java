package health_center.models;

import health_center.util.UserRole;
import jakarta.persistence.*;

@Entity
@Table(name = "nurse")
public class Nurse extends User {
    @Column(name = "salary")
    private double salary;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private Servicing service;

    public Nurse() {
    }

    public Nurse(String firstName, String lastName, String jmbg, String sex, String address, String phoneNumber, String userName, String password, double salary, Servicing service) {
        super(firstName, lastName, jmbg, sex, address, phoneNumber, userName, password, UserRole.NURSE);
        this.salary = salary;
        this.service = service;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Servicing getService() {
        return service;
    }

    public void setService(Servicing service) {
        if (service.getNurses() != null && !service.getNurses().contains(this)) {
            service.getNurses().add(this);
        }
        this.service = service;
    }
}
