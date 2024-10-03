package org.example.backlmf.service;

import org.example.backlmf.entity.Alarm;
import org.example.backlmf.entity.Device;
import org.example.backlmf.repository.AlarmRepository;
import org.example.backlmf.repository.DeviceRepository;
import org.example.backlmf.websocket.AlarmWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmService {
    @Autowired
    private AlarmRepository alarmRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private AlarmWebSocketHandler alarmWebSocketHandler;

    public Alarm createAlarm(Alarm alarm) {
        if (alarm.getDevice() != null && alarm.getDevice().getId() != null) {
            Device device = deviceRepository.findById(alarm.getDevice().getId())
                    .orElseThrow(() -> new RuntimeException("设备不存在"));
            alarm.setDevice(device);
        }
        Alarm savedAlarm = alarmRepository.save(alarm);
        alarmWebSocketHandler.sendAlarmNotification(savedAlarm);
        return savedAlarm;
    }

    public List<Alarm> getAlarmsByDevice(Long deviceId) {
        return alarmRepository.findByDeviceId(deviceId);
    }

    public Alarm handleAlarm(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new RuntimeException("报警不存在"));
        alarm.setHandled(true);
        return alarmRepository.save(alarm);
    }

    public List<Alarm> getUnhandledAlarms() {
        return alarmRepository.findByHandled(false);
    }

    // 其他报警相关方法
}