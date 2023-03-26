package baseUrl;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class OmbdBaseUrl {

    public static RequestSpecification spec;

    public static void ombdSetup(){
        spec=new RequestSpecBuilder().setBaseUri("https://www.omdbapi.com/").build();
    }


}
