package org.example.backlmf.websocket;

import org.example.backlmf.entity.Alarm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlarmWebSocketHandler {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public void sendAlarmNotification(Alarm alarm) {
        messagingTemplate.convertAndSend("/topic/alarms", alarm);
    }
}