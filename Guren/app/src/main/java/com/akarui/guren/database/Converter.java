package com.akarui.guren.database;

import androidx.room.TypeConverter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

public class Converter {
    @TypeConverter
    public static Long ToTimeStamp(LocalDateTime localDateTime){
        if (localDateTime == null){
            localDateTime = LocalDateTime.now();
        }

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    
    @TypeConverter
    public static LocalDateTime ToLocalDateTime(Long timestamp){
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
}
