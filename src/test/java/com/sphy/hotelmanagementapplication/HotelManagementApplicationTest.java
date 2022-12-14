package com.sphy.hotelmanagementapplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles(value = "dev")
class HotelManagementApplicationTest {

	// This test tests if the app can be run - and the spring context loads properly
	@Test
	void contextLoads() {
	}
}