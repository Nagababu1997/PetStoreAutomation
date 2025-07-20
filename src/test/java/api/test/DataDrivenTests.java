package api.test;

import api.endpoints.StudentEndPoints;
import api.payload.Student;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

public class DataDrivenTests {
    static Student userPayload;
    @Test(priority = 1,dataProvider = "Data",dataProviderClass = DataProviders.class)
    public void testPostStudent(String stuId,String stuName,String stuLocation,String stuPhone,String stuCourses)
    {
        userPayload = new Student();
        userPayload.setId(stuId);
        userPayload.setName(stuName);
        userPayload.setLocation(stuLocation);
        userPayload.setPhone(stuPhone);
        userPayload.setCourses(Collections.singletonList(stuCourses));

        Response response = StudentEndPoints.createStudent(userPayload);

        Assert.assertEquals(response.getStatusCode(), 201);
    }
    @Test(priority = 2,dataProvider = "StudentID",dataProviderClass = DataProviders.class)
    public void testGetStudentDataById(String stuId)
    {
        Response response=StudentEndPoints.getStudent(stuId);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 3,dataProvider = "StudentID",dataProviderClass = DataProviders.class)
    public void testDeleteStudentById(String stuId)
    {
        Response response =StudentEndPoints.deleteStudent(stuId);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
