package org.example.backlmf.service;

import org.example.backlmf.entity.Alarm;
import org.example.backlmf.repository.AlarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {
    @Autowired
    private AlarmRepository alarmRepository;

    public Alarm createAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public List<Alarm> getAlarmsByDevice(Long deviceId) {
        return alarmRepository.findByDeviceId(deviceId);
    }

    // 其他报警相关方法
}