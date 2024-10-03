package org.example.backlmf.service;

import org.example.backlmf.entity.Data;
import org.example.backlmf.entity.Device;
import org.example.backlmf.repository.DataRepository;
import org.example.backlmf.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    public Data saveData(Data data) {
        if (data.getDevice() != null && data.getDevice().getId() != null) {
            Device device = deviceRepository.findById(data.getDevice().getId())
                    .orElseThrow(() -> new RuntimeException("设备不存在"));
            data.setDevice(device);
        }
        return dataRepository.save(data);
    }

    public List<Data> getDataByDeviceAndTimeRange(Long deviceId, LocalDateTime start, LocalDateTime end) {
        return dataRepository.findByDeviceIdAndTimestampBetween(deviceId, start, end);
    }

    // 其他数据相关方法
}