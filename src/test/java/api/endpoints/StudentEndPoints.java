package api.endpoints;

import api.payload.Student;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StudentEndPoints {

    public static Response createStudent(Student payload) {


        Response response = given()
                .contentType(ContentType.JSON)
//                .header("Authorization", "Bearer " + bearerToken)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);


        return response;
    }

    public static Response getStudent(String userId) {

        Response response = given()
                .pathParam("id", userId)
                .when()
                .get(Routes.get_url);


        return response;
    }

    public static Response updateStudent(String userId, Student payload) {

        Response response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("id", userId)
                .body(payload)
                .when()
                .put(Routes.update_url);


        return response;
    }

    public static Response deleteStudent(String userId) {

        Response response = given()
                .pathParam("id", userId)
                .when()
                .delete(Routes.delete_url);


        return response;
    }
}
