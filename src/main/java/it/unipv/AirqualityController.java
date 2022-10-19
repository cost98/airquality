package it.unipv;

import io.micronaut.http.annotation.*;

@Controller("/airquality")
public class AirqualityController {

    @Get(uri="/", produces="text/plain")
    public String index() {
        return "Example Response";
    }
}