package ru.praktikum_services.qa_scooter.test.order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.praktikum_services.qa_scooter.object_api.CommonMethod;
import ru.praktikum_services.qa_scooter.object_api.OrderMethod;

import static ru.praktikum_services.qa_scooter.constant.ServiceName.*;
import static ru.praktikum_services.qa_scooter.constant.StatusCode.*;

public class OrderListTest {

    private final String orderBodyFieldOrders = "orders";

    @BeforeClass
    public static void suiteSetup() {
        RestAssured.baseURI = BASE_URI_QA_SCOOTER;
    }

    @Test
    @DisplayName("Check get order list")
    public void checkOrderList() {
        Response response = OrderMethod.sendGetRequestGetOrderList();
        CommonMethod.checkResponseCode(response, CODE_200);
        CommonMethod.checkResponseBodyNotNullField(response, orderBodyFieldOrders);
    }

}
