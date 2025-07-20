package api.endpoints;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class StudentEndPoints1 {

//    Creating additional method for getting URLs from the Properties file
    static ResourceBundle getURL()
    {

        ResourceBundle routes = ResourceBundle.getBundle("routes"); //load properties file
        return routes;
    }
    public static Response createStudent(Student payload) {

        String post_url=getURL().getString("post_url");

        Response response = given()
                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + bearerToken)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);


        return response;
    }

    public static Response getStudent(String userId) {

        String get_url=getURL().getString("get_url");
        Response response = given()
                .pathParam("id", userId)
                .when()
                .get(get_url);


        return response;
    }

    public static Response updateStudent(String userId, Student payload) {

        String update_url=getURL().getString("update_url");

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", userId)
                .body(payload)
                .when()
                .put(update_url);


        return response;
    }

    public static Response deleteStudent(String userId) {
        String delete_url=getURL().getString("delete_url");
        Response response = given()
                .pathParam("id", userId)
                .when()
                .delete(delete_url);


        return response;
    }
}
