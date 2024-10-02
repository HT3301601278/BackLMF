package org.example.backlmf.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "data")
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double ammoniaConcentration;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmmoniaConcentration() {
        return ammoniaConcentration;
    }

    public void setAmmoniaConcentration(double ammoniaConcentration) {
        this.ammoniaConcentration = ammoniaConcentration;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
}