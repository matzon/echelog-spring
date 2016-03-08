/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016 Brian Matzon
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package dk.matzon.echelog.interfaces.rest;

import com.jayway.restassured.RestAssured;
import dk.matzon.echelog.infrastructure.spring.SpringBoot;
import dk.matzon.echelog.interfaces.dto.ChannelDTO;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.post;
import static com.jayway.restassured.RestAssured.when;

/**
 * @author Brian Matzon <brian@matzon.dk>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBoot.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@ActiveProfiles("integrationtest")
public class ChannelControllerTestIT {

    @Value("${local.server.port}")
    int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        given()
            .contentType("application/json")
            .body("{\"id\": \"0\", \"name\": \"#angularjs\", \"description\": \"description\", \"url\": \"url\", \"archived\": \"false\"}".getBytes())
        .expect()
            .statusCode(200)
        .when()
            .post("/api/channels");
    }

    @Test
    public void testListAllChannels() throws Exception {
        when().
            get("/api/channels").
        then().
            statusCode(HttpStatus.OK.value()).
            body("name", Matchers.hasItems("#angularjs"));
    }
}