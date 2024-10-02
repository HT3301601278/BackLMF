package org.example.backlmf.controller;

import org.example.backlmf.entity.Device;
import org.example.backlmf.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<Device> addDevice(@RequestBody Device device) {
        return ResponseEntity.ok(deviceService.addDevice(device));
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        return ResponseEntity.ok(deviceService.getAllDevices());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Device> updateDevice(@PathVariable Long id, @RequestBody Device device) {
        device.setId(id);
        return ResponseEntity.ok(deviceService.updateDevice(device));
    }

    // 其他设备相关端点
}