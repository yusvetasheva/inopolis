package com.example.inopolis.service;

import com.example.inopolis.model.dto.NotificationDTO;

public interface NotificationService {
    public void notifyByEmail(NotificationDTO dto);
}
