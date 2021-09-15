import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class WrongLoginTest {

    Routes routes = new Routes();
    ErrorCode errorCode = new ErrorCode();
    Methods methods = new Methods();
    JSONObject requestBody = new JSONObject();
    RequestSpecification request = RestAssured.given();

    String wrongEmail1 = methods.generateRandomHexString(5);
    String wrongEmail2 = methods.generateRandomHexString(5) + "@";
    String wrongEmail3 = methods.generateRandomHexString(5) + "@email";
    String wrongEmail4 = methods.generateRandomHexString(5) + "@email.";
    String wrongEmail5 = methods.generateRandomHexString(5) + "@email.c";
    String correctEmail = methods.generateRandomHexString(5) + "@email.com";
    String emptyEmail = "";
    String emptyPassword = "";
    String password = methods.generateRandomHexString(6);

    @Test
    public void incorrectEmail1() {
      requestBody.put("email", wrongEmail1);
      requestBody.put("password", password);

      request.header("Content-Type", "application/json");
      request.body(requestBody.toString());

      Response response = request.post(routes.postLogin).then().
              contentType(ContentType.JSON).extract().response();

      int statusCode = response.getStatusCode();
      String success = response.jsonPath().getString("success");
      int customStatusCode = response.jsonPath().getInt("statusCode");
      List<String> codes = response.jsonPath().getList("codes");

      List<Object> expected = new ArrayList<>();
      expected.add(statusCode);
      expected.add(success);
      expected.add(customStatusCode);
      expected.add(codes.get(0));
      List<Object> actual = new ArrayList<>();
      actual.add(400);
      actual.add("true");
      actual.add(errorCode.USER_EMAIL_NOT_VALID);
      actual.add(errorCode.USER_EMAIL_NOT_VALID);
      Assert.assertEquals(expected, actual);
    }

    @Test
    public void incorrectEmail2() {
        requestBody.put("email", wrongEmail2);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_VALID);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_VALID);
    }

    @Test
    public void incorrectEmail3() {
        requestBody.put("email", wrongEmail3);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_VALID);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_VALID);
    }

    @Test
    public void incorrectEmail4() {
        requestBody.put("email", wrongEmail4);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_VALID);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_VALID);
    }

    @Test
    public void incorrectEmail5() {
        requestBody.put("email", wrongEmail5);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_VALID);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_VALID);
    }

    @Test
    public void userDoesNotExist() {
        requestBody.put("email", correctEmail);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_NOT_FOUND);
        Assert.assertEquals(codes.get(0), errorCode.USER_NOT_FOUND);
    }

    @Test
    public void emptyEmail() {
        requestBody.put("email", emptyEmail);
        requestBody.put("password", password);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_NULL);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_NULL);
    }

    @Test
    public void emptyPassword() {
        requestBody.put("email", correctEmail);
        requestBody.put("password", emptyPassword);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_PASSWORD_NULL);
        Assert.assertEquals(codes.get(0), errorCode.USER_PASSWORD_NULL);
    }

    @Test
    public void emptyEmailAndPassword() {
        requestBody.put("email", emptyEmail);
        requestBody.put("password", emptyPassword);

        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());

        Response response = request.post(routes.postLogin).then().
                contentType(ContentType.JSON).extract().response();

        int statusCode = response.getStatusCode();
        String success = response.jsonPath().getString("success");
        int customStatusCode = response.jsonPath().getInt("statusCode");
        List<String> codes = response.jsonPath().getList("codes");

        Assert.assertEquals(statusCode, 400);
        Assert.assertEquals(success, "true");
        Assert.assertEquals(customStatusCode, errorCode.USER_EMAIL_NOT_NULL);
        Assert.assertEquals(codes.get(0), errorCode.USER_EMAIL_NOT_NULL);
        Assert.assertEquals(codes.get(0), errorCode.USER_PASSWORD_NULL);
    }
}
