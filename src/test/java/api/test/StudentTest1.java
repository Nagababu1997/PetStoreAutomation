package api.test;

import api.endpoints.StudentEndPoints1;
import api.payload.Student;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StudentTest1 {
    Faker faker;
    static Student studentPayload;
    public Logger logger;


    @BeforeClass
    void setUpData() {
        faker = new Faker();
        studentPayload = new Student();

        studentPayload.setId(faker.idNumber().valid());
        studentPayload.setName(faker.name().fullName());
        studentPayload.setLocation(faker.country().name());
        studentPayload.setPhone(faker.phoneNumber().phoneNumber());
        List<String> softwareCourses = Arrays.asList(
                "Java",
                "Python",
                "C++",
                "Web Development",
                "Software Engineering",
                "Machine Learning",
                "Data Structures",
                "DevOps",
                "Mobile App Development"
        );


// Pick 2 random ones
        Collections.shuffle(softwareCourses);
        List<String> selectedCourses = softwareCourses.subList(0, 2);

        studentPayload.setCourses(selectedCourses);

//        logs
        logger = (Logger) LogManager.getLogger(this.getClass());


    }

    @Test(priority = 1)
    public void testPostStudent() {


        logger.info("***** Post Student Details *****");

        Response response = StudentEndPoints1.createStudent(studentPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 201);
        logger.info("***** Student is created *****");
    }

    @Test(priority = 2)
    public void testGetStudent() {
        logger.info("***** Reading Student Info *****");
        Response response = StudentEndPoints1.getStudent(this.studentPayload.getId());
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("***** Student Details is displayed *****");
    }

    @Test(priority = 3)
    public void testUpdateStudent() {

        logger.info("***** Update Student Details *****");
//        updating the data by payload
        studentPayload.setName(faker.name().fullName());
        studentPayload.setLocation(faker.country().name());
        studentPayload.setPhone(faker.phoneNumber().phoneNumber());
        Response response = StudentEndPoints1.updateStudent(this.studentPayload.getId(), studentPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

//        checking student details after updating
        Response responseUpdatedStudent = StudentEndPoints1.getStudent(this.studentPayload.getId());
        response.then().log().all();

        Assert.assertEquals(responseUpdatedStudent.getStatusCode(), 200);

        logger.info("***** Student Details Updated *****");


    }


    @Test(priority = 4)
    public void testDeleteStudent() {
        logger.info("***** Delete Student Details *****");
        Response response = StudentEndPoints1.deleteStudent(this.studentPayload.getId());
        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("***** Student Details Delete *****");

    }
}


