package org.example.backlmf.controller;

import org.example.backlmf.entity.Data;
import org.example.backlmf.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired
    private DataService dataService;

    @PostMapping
    public ResponseEntity<Data> saveData(@RequestBody Data data) {
        return ResponseEntity.ok(dataService.saveData(data));
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<List<Data>> getDataByDeviceAndTimeRange(
            @PathVariable Long deviceId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(dataService.getDataByDeviceAndTimeRange(deviceId, start, end));
    }

    @PostMapping("/device/{deviceId}")
    public ResponseEntity<Data> addDataToDevice(@PathVariable Long deviceId, @RequestBody Data data) {
        return ResponseEntity.ok(dataService.addDataToDevice(deviceId, data));
    }

    // 其他数据相关端点
}