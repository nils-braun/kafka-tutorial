package kstreams.example.utils;

import org.apache.kafka.streams.StreamsConfig;

import java.util.Properties;

public class StreamApp {
    protected static Properties getStreamProperties(String applicationName) {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, applicationName);
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        return props;
    }
}
