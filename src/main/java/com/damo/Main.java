package com.damo;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        /*
         * Spark is already mostly human readable,
         * but this is just handling the payload that
         * comes from whatever alien entity that would use this API.
         */
        post("/insert", (request, response) -> {
            boolean correctFormat = ApiRequest.verifyBody(request.body());
            if (!correctFormat) {
                halt(400,
                        "Your JSON payload is expected to contain at least two fields, " +
                                "named `tree` and `value`");
            }

            response.body(ApiRequest.processBody(request.body()));
            response.type("application/json");

            if (!response.body().contains("value")) {
                response.status(400);
            } else {
                response.status(200);
            }

            return response.body();
        });
    }
}
