package org.example.backlmf.controller;

import org.example.backlmf.entity.Alarm;
import org.example.backlmf.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {
    @Autowired
    private AlarmService alarmService;

    @PostMapping
    public ResponseEntity<Alarm> createAlarm(@RequestBody Alarm alarm) {
        return ResponseEntity.ok(alarmService.createAlarm(alarm));
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<List<Alarm>> getAlarmsByDevice(@PathVariable Long deviceId) {
        return ResponseEntity.ok(alarmService.getAlarmsByDevice(deviceId));
    }

    // 其他报警相关端点
}