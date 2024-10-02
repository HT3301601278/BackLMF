package org.example.backlmf.repository;

import org.example.backlmf.entity.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
    List<Alarm> findByDeviceId(Long deviceId);
}