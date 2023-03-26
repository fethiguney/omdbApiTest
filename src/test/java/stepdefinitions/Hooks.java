package stepdefinitions;

import io.cucumber.java.Before;

import static baseUrl.OmbdBaseUrl.ombdSetup;


public class Hooks {

    @Before
    public void beforeApi() {
        ombdSetup();
    }


}
