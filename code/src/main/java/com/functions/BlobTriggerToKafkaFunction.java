package com.functions;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.StorageAccount;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

import static com.functions.Settings.*;

public class BlobTriggerToKafkaFunction {

    private Producer<String, String> kafkaProducer = new KafkaProducer<>(buildKafkaProperties());

    @FunctionName("BlobTriggerToKafka")
    @StorageAccount("BlobStorageTriggerTarget")
    public void handler(
        @BlobTrigger(name = "triggerBlob", path = BLOB_CONTAINER + "/{name}") String triggerBlob,
        @BindingName("name") String fileName, final ExecutionContext context
    ) {
        context.getLogger()
            .info("Trigger filename: " + fileName + ", size: " + triggerBlob.length() + " Bytes");

        Arrays.stream(triggerBlob.split(System.lineSeparator()))
            .map(line -> new ProducerRecord<>(TOPIC, "event: ", "kafka: " + line))
            .forEach(msg -> kafkaProducer.send(msg));
    }

    private static Properties buildKafkaProperties() {
        final Properties properties = new Properties();
        final String jaasTemplate = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        properties.setProperty("sasl.jaas.config", String.format(jaasTemplate, KAFKA_USER, KAFKA_PASS));
        properties.setProperty("security.protocol", "SASL_SSL");
        properties.setProperty("sasl.mechanism", "PLAIN");
        properties.setProperty("ssl.enabled.protocols", "TLSv1.2,TLSv1.1,TLSv1");
        properties.setProperty("ssl.protocol", "TLS");

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        return properties;
    }

}
