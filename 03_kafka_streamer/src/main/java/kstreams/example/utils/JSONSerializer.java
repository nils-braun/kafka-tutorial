package kstreams.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kstreams.example.utils.SensorData;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class JSONSerializer implements Serializer<SensorData> {
    @Override public void configure(Map<String, ?> map, boolean b) { }

    @Override
    public byte[] serialize(String arg0, SensorData sensorData) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(sensorData).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override public void close() { }
}
