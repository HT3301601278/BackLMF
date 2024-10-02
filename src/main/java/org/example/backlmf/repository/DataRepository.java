package org.example.backlmf.repository;

import org.example.backlmf.entity.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByDeviceIdAndTimestampBetween(Long deviceId, LocalDateTime start, LocalDateTime end);
}