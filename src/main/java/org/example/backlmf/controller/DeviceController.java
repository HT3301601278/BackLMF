package org.example.backlmf.controller;

import org.example.backlmf.entity.Device;
import org.example.backlmf.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.example.backlmf.exception.DeviceIdAlreadyExistsException;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<?> addDevice(@RequestBody Device device) {
        try {
            return ResponseEntity.ok(deviceService.addDevice(device));
        } catch (DeviceIdAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
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

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        if (device != null) {
            return ResponseEntity.ok(device);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/toggle")
    public ResponseEntity<Device> toggleDeviceStatus(@PathVariable Long id) {
        Device device = deviceService.getDeviceById(id);
        if (device != null) {
            device.setStatus(device.getStatus() == null ? true : !device.getStatus());
            return ResponseEntity.ok(deviceService.updateDevice(device));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 其他设备相关端点
}