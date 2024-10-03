package org.example.backlmf.service;

import org.example.backlmf.entity.Device;
import org.example.backlmf.repository.DeviceRepository;
import org.example.backlmf.exception.DeviceIdAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    public boolean isDeviceIdExists(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId) != null;
    }

    public Device addDevice(Device device) throws DeviceIdAlreadyExistsException {
        if (isDeviceIdExists(device.getDeviceId())) {
            throw new DeviceIdAlreadyExistsException("设备ID已存在");
        }
        return deviceRepository.save(device);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device findByDeviceId(String deviceId) {
        return deviceRepository.findByDeviceId(deviceId);
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id).orElse(null);
    }

    // 其他设备相关方法
}