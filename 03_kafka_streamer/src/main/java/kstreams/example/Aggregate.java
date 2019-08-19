/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package kstreams.example;

import kstreams.example.utils.JSONDeserializer;
import kstreams.example.utils.JSONSerializer;
import kstreams.example.utils.SensorData;
import kstreams.example.utils.StreamApp;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;

public class Aggregate extends StreamApp {

    public static void main(String[] args) throws Exception {
        // Configure the Streams application.
        final Properties streamsConfiguration = getStreamProperties("sensor-aggregate");

        // Define the processing topology of the Streams application.
        final StreamsBuilder builder = new StreamsBuilder();
        buildStream(builder);

        // Now execute the topology
        final KafkaStreams topology = new KafkaStreams(builder.build(), streamsConfiguration);
        topology.cleanUp();
        topology.start();
        Runtime.getRuntime().addShutdownHook(new Thread(topology::close));
    }

    private static void buildStream(StreamsBuilder builder) {
        JSONSerializer sensorDataSerializer = new JSONSerializer();
        JSONDeserializer sensorDataDeserializer = new JSONDeserializer();
        final Serde<SensorData> sensorDataSerde = Serdes.serdeFrom(sensorDataSerializer, sensorDataDeserializer);

        // Consume the input stream
        KStream<String, SensorData> inputStream = builder.stream("sensor-data-input",
                Consumed.with(Serdes.String(), sensorDataSerde));

        KTable<String, Long> brokenCounts =
                inputStream
                        .filter((key, value) -> value.getSensorData1() > 0.5)
                        .groupByKey()
                        .count();

        brokenCounts.toStream().print(Printed.toSysOut());
    }
}
