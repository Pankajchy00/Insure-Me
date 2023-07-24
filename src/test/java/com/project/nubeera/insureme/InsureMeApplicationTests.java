package com.project.nubeera.insureme;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class InsureMeApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void testCreatePolicy() {
		Policy policy = new Policy(1, "Pankaj", "Individual" , 10000, "01-jun-2023", "01-Sep-2023");
		PolicyService pService = new PolicyService();
		//Policy outputPolicy = pService.CreatePolicy();
		assertEquals(policy.getPolicyId(), pService.generateDummyPolicy().getPolicyId());
		
	}
	
	@Test
	void testSearchPolicy() {
		PolicyService pService = new PolicyService();
		assertEquals(null,pService.searchPolicy());
	}
	
	
}
