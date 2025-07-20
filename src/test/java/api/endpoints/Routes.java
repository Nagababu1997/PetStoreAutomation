package api.endpoints;

/*
Swagger URI----> https://petstore.swagger.io/

Gorest URI-----> https://gorest.co.in/
Create User(POST)---> https://gorest.co.in/public/v2/users
Get User(GET)-------> https://gorest.co.in/public/v2/users/{id}
Update User(UPDATE)-->https://gorest.co.in/public/v2/users/{id}
Delete User(DELETE)-->https://gorest.co.in/public/v2/users/{id}


Create User(POST)---> https://petstore.swagger.io/v2/user
Get User(GET)-------> https://petstore.swagger.io/v2/user/{username}
Update User(UPDATE)-->https://petstore.swagger.io/v2/user/{username}
Delete User(DELETE)-->https://petstore.swagger.io/v2/user/{username}

 */
public class Routes {

    public static String student_url = "http://localhost:3000/students";
    public static String base_url = "https://petstore.swagger.io";
//    public static String base_url = "https://gorest.co.in";


    public static String post_url     = student_url;
    public static String get_url      = student_url+"/{id}";
    public static String update_url   = student_url+"/{id}";
    public static String delete_url   = student_url+"/{id}";


    //user module
/*
    https://petstore.swagger.io/v2/user

    public static String post_url = base_url+"/v2/user";
    public static String get_url = base_url+"/v2/user/{username}";
    public static String update_url = base_url+"/v2/user/{username}";
    public static String delete_url = base_url+"/v2/user/{username}";

    //store module
       //here we will create store module urls

    //pet module
       //here we will create pet module urls


 */
}
