package org.example.backlmf.exception;

public class DeviceIdAlreadyExistsException extends RuntimeException {
    public DeviceIdAlreadyExistsException(String message) {
        super(message);
    }
}