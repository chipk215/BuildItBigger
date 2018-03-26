package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.keyeswest.jokelib.Joker;


/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    // using a single instance will rotate jokes
    private final static Joker sJOKER = new Joker();


    @ApiMethod(name = "getJoke")
    public MyBean getJoke() {
        String joke = sJOKER.getJoke();
        MyBean response = new MyBean();
        response.setData(joke);

        return response;
    }

}
