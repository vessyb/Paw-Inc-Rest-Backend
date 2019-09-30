package com.pawinc.pawinc.shared;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {
    public long generateCenterId(){
        return UUID.randomUUID().variant();
    }

    public long generateAnimalId() {
        return UUID.randomUUID().variant();
    }
}
