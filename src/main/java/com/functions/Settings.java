package com.functions;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public abstract class Settings {

    private static final Properties props = getProperties().orElseThrow() ;

    public static final String EVENTHUB_CONNECTION_STRING= props.getProperty("app.eventhub.conn-string");

    public static final String TOPIC= props.getProperty("app.eventhub.topic");

    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static final String BLOB_CONTAINER = "test-triggerinput-java";

    public static final String KAFKA_BOOTSTRAP_SERVERS = props.getProperty("app.kafka.bootstrap-servers");
    public static final String KAFKA_USER = props.getProperty("app.kafka.user");
    public static final String KAFKA_PASS = props.getProperty("app.kafka.pass");

    private static Optional<Properties> getProperties() {
        final String properties_file_name = "application.properties";
        try (InputStream input = Settings.class.getClassLoader()
                .getResourceAsStream(properties_file_name) ) {
            final Properties prop = new Properties();
            if (input != null) {
                prop.load(input);
                prop.forEach( (k,v) -> System.out.println(k + " " + v));
                return Optional.of(prop);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("Sorry, unable to find " + properties_file_name);
        return Optional.empty();
    }

}
