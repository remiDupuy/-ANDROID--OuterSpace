package com.dupuy.remi.outerspacemanager.models.responses;

/**
 * Created by rdupuy on 16/01/2018.
 */

public class Response {

    public String token;
    public String expires;

    public Response() {

    }

    @Override
    public String toString() {
        return this.token;
    }
}
