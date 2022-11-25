package ru.praktikum_services.qa_scooter.test.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum_services.qa_scooter.Order;
import ru.praktikum_services.qa_scooter.object_api.CommonMethod;

import java.util.List;

import static ru.praktikum_services.qa_scooter.constant.ServiceName.BASE_URI_QA_SCOOTER;
import static ru.praktikum_services.qa_scooter.constant.StatusCode.*;
import static ru.praktikum_services.qa_scooter.object_api.OrderMethod.*;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    // Test data
    private final String orderBodyFieldTrack = "track";

    public CreateOrderParameterizedTest(String firstName, String lastName, String address, int metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {

    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
            {"Jone", "Dow", "Street 1", 1, "+79987", 5, "2020-06-07", "comment1", List.of("BLACK")},
            {"Jone", "Dow", "Street 1", 1, "+79987", 5, "2020-06-07", "comment1", List.of("GREY")},
            {"Jone", "Dow", "Street 1", 1, "+79987", 5, "2020-06-07", "comment1", List.of("BLACK", "GREY")},
            {"Jone", "Dow", "Street 1", 1, "+79987", 5, "2020-06-07", "comment1", List.of()},
        };
    }

    @Test
    @DisplayName("Check create order (parameterized)")
    public void createOrderTest() {
        RestAssured.baseURI = BASE_URI_QA_SCOOTER;
        Order order = new Order();
        Response response = sendPostRequestCreateOrder(order);
        CommonMethod.checkResponseCode(response, CODE_201);
        CommonMethod.checkResponseBodyNotNullField(response, orderBodyFieldTrack);
    }

}
