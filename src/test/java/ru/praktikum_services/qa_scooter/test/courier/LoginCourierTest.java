package ru.praktikum_services.qa_scooter.test.courier;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.Courier;
import ru.praktikum_services.qa_scooter.object_api.CommonMethod;
import ru.praktikum_services.qa_scooter.object_api.CourierMethod;

import static ru.praktikum_services.qa_scooter.constant.ServiceName.BASE_URI_QA_SCOOTER;
import static ru.praktikum_services.qa_scooter.constant.StatusCode.*;

public class LoginCourierTest {

    static Courier courier;

    //Login courier test
    private static final String courierLogin = "max_login_23";
    private static final String courierPassword = "password";
    private static final String courierName  = "Maxim";
    private static final String courierJson = "{\"login\": \"max_login_23\", \"password\": \"password\"}";
    private static final String courierBodyFieldId = "id";

    //Login courier with wrong password
    private static final String courierJsonWithWrongPassword = "{\"login\": \"max_login_23\", \"password\": \"wrong_password\"}";
    private static final String courierBodyFieldMessageWithWrongPassword  = "message";
    private static final String courierBodyValueMessageWithWrongPassword  = "Учетная запись не найдена";

    //Login courier without login field
    private static final String courierJsonWithoutLoginField = "{\"password\": \"password\"}";
    private static final String courierBodyFieldMessageWithoutLoginField = "message";
    private static final String courierBodyValueMessageWithoutLoginField = "Недостаточно данных для входа";

    //Login no exist courier
    private static final String courierJsonNoExistCourier = "{\"login\": \"hYnGjFrFsFSd\", \"password\": \"password\"}";
    private static final String courierBodyFieldMessageNoExistCourier = "message";
    private static final String courierBodyValueMessageNoExistCourier = "Учетная запись не найдена";

    @BeforeClass
    public static void suiteSetup() {
        RestAssured.baseURI = BASE_URI_QA_SCOOTER;
        courier = new Courier(courierLogin, courierPassword, courierName);
        Response response = CourierMethod.sendPostRequestCreateCourier(courier);
        CommonMethod.checkResponseCode(response, CODE_201);
    }

    @Test
    @DisplayName("Check successful courier login")
    public void checkLoginCourier() {
        Response response = CourierMethod.sendPostRequestLoginCourier(courierJson);
        CommonMethod.checkResponseCode(response, CODE_200);
        CommonMethod.checkResponseBodyNotNullField(response, courierBodyFieldId);
    }

    @Test
    @DisplayName("Check login courier with wrong password")
    public void checkLoginCourierWrongPassword() {
        Response response = CourierMethod.sendPostRequestLoginCourier(courierJsonWithWrongPassword);
        CommonMethod.checkResponseCode(response, CODE_404);
        CommonMethod.checkResponseBody(response, courierBodyFieldMessageWithWrongPassword, courierBodyValueMessageWithWrongPassword);
    }

    @Test
    @DisplayName("Check login courier without field 'login'")
    public void checkLoginCourierWithoutLoginField() {
        Response response = CourierMethod.sendPostRequestLoginCourier(courierJsonWithoutLoginField);
        CommonMethod.checkResponseCode(response, CODE_400);
        CommonMethod.checkResponseBody(response, courierBodyFieldMessageWithoutLoginField, courierBodyValueMessageWithoutLoginField);
    }

    @Test
    @DisplayName("Check no exist courier login")
    public void checkLoginNoExistCourier() {
        Response response = CourierMethod.sendPostRequestLoginCourier(courierJsonNoExistCourier);
        CommonMethod.checkResponseCode(response, CODE_404);
        CommonMethod.checkResponseBody(response, courierBodyFieldMessageNoExistCourier, courierBodyValueMessageNoExistCourier);
    }

    @AfterClass
    public static void suiteTeardown() {
        CourierMethod.loginAndDeleteCourier(courier);

    }

}
