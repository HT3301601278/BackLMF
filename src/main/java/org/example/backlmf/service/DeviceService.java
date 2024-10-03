package org.example.backlmf.service;

import org.example.backlmf.entity.Device;
import org.example.backlmf.entity.User;
import org.example.backlmf.repository.DeviceRepository;
import org.example.backlmf.repository.UserRepository;
import org.example.backlmf.exception.DeviceIdAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private UserRepository userRepository;

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

    public Device associateDeviceWithUser(Long deviceId, Long userId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("设备不存在"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        
        device.setUser(user);
        return deviceRepository.save(device);
    }

    // 其他设备相关方法
}