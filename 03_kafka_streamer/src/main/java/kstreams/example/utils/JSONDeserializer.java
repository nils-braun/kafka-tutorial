package kstreams.example.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import kstreams.example.utils.SensorData;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class JSONDeserializer implements Deserializer<SensorData> {
    @Override public void configure(Map<String, ?> map, boolean b) { }

    @Override
    public SensorData deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        SensorData sensorData = null;
        try {
            sensorData = mapper.readValue(arg1, SensorData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sensorData;
    }

    @Override public void close() { }
}
