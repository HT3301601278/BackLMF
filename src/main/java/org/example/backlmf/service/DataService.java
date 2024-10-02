package org.example.backlmf.service;

import org.example.backlmf.entity.Data;
import org.example.backlmf.repository.DataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataService {
    @Autowired
    private DataRepository dataRepository;

    public Data saveData(Data data) {
        return dataRepository.save(data);
    }

    public List<Data> getDataByDeviceAndTimeRange(Long deviceId, LocalDateTime start, LocalDateTime end) {
        return dataRepository.findByDeviceIdAndTimestampBetween(deviceId, start, end);
    }

    // 其他数据相关方法
}