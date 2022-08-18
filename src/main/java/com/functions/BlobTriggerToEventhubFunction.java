package com.functions;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.StorageAccount;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.functions.Settings.*;

public class BlobTriggerToEventhubFunction {

    private static final EventHubProducerClient eventhubProducer = new EventHubClientBuilder()
        .connectionString(EVENTHUB_CONNECTION_STRING, TOPIC)
        .buildProducerClient();

    @FunctionName("BlobTriggerToEventhub")
    @StorageAccount("AzureWebJobsStorage")
    public void handler(
        @BlobTrigger(name = "triggerBlob", path = BLOB_CONTAINER + "/{name}") String triggerBlob,
        @BindingName("name") String fileName, final ExecutionContext context
    ) {
        context.getLogger()
            .info("Trigger filename: " + fileName + ", size: " + triggerBlob.length() + " Bytes");

        final List<EventData> messages = Arrays.stream(triggerBlob.split(LINE_SEPARATOR))
            .map( line -> new EventData("ev: " + line) )
            .collect(Collectors.toList());
        eventhubProducer.send(messages);
    }
}
