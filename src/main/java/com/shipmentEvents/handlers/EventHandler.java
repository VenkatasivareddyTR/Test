package com.shipmentEvents.handlers;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;


public class EventHandler implements RequestHandler<ScheduledEvent, String> {

    /**
     * Shipment events for a carrier are uploaded to separate S3 buckets based on the source of events. E.g., events originating from
     * the hand-held scanner are stored in a separate bucket than the ones from mobile App. The Lambda processes events from multiple
     * sources and updates the latest status of the package in a summary S3 bucket every 15 minutes.
     * 
     * The events are stored in following format:
     * - Each status update is a file, where the name of the file is tracking number + random id.
     * - Each file has status and time-stamp as the first 2 lines respectively.
     * - The time at which the file is stored in S3 is not an indication of the time-stamp of the event.
     * - Once the status is marked as DELIVERED, we can stop tracking the package.
     * 
     * A Sample files looks as below:
     *  FILE-NAME-> '8787323232232332--55322798-dd29-4a04-97f4-93e18feed554'
     *   >status:IN TRANSIT
     *   >timestamp: 1573410202
     *   >Other fields like...tracking history and address
     */
    public String handleRequest(ScheduledEvent scheduledEvent, Context context) {

        final LambdaLogger logger = context.getLogger();
        try {
            //processShipmentUpdates(logger);
            AmazonS3 client = getS3Client();
            return "SUCCESS";
        } catch (final Exception ex) {
            logger.log(String.format("Failed to process shipment Updates in %s due to %s", scheduledEvent.getAccount(), ex.getMessage()));
            throw new RuntimeException("Hiding the exception");
        }
    }

    public static AmazonS3 getS3Client() {
        return AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    }
}


