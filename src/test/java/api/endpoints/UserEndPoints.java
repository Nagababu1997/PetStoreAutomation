package api.endpoints;

// UserEndPoints.java
//Created for CRUD----> Create, Red, Update, and Delete requests to the user API.

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    //    User payload ----> can be used to pass datta to the request

//    static String bearerToken = "45abcb23fffec919ea6da529c39504a0e3483f10b235782e7f52a4c9a634f5a4";
    public static Response createUser(User payload) {



        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);


        return response;
    }

    public static Response readUser(String userName) {

        Response response = given()
                .pathParam("username", userName)
                .when()
                .get(Routes.get_url);


        return response;
    }

    public static Response updateUser(String userName, User payload) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username", userName)
                .body(payload)
                .when()
                .put(Routes.update_url);


        return response;
    }

    public static Response deleteUser(String userName) {

        Response response = given()
                .pathParam("username", userName)
                .when()
                .delete(Routes.delete_url);


        return response;
    }
}
