package org.example.backlmf.repository;

import org.example.backlmf.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {
    Device findByDeviceId(String deviceId);
}