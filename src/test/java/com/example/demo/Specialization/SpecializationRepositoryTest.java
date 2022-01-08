package com.example.demo.Specialization;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Specialization;
import com.example.demo.repo.SpecializationRepository;

@DataJpaTest(showSql = true)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class SpecializationRepositoryTest {
    @Autowired
	private SpecializationRepository repo;
/**
 * 1.test save operation
 */	@Test
	@Order(1)
	public void spectestCreate() {
		
		Specialization spec=new Specialization(null,"CRDLS","Cardiologist","they are experties in blood vessels");
	       spec=repo.save(spec);
	       assertNotNull(spec.getId(),"Spec is not Created!");
	}
	/**
	 * 2.to display all operation
	 */
	@Test
	@Order(2)
	public void testSpecFeatchAll() {
		List<Specialization> list= repo.findAll();
		assertNotNull(list);
		if(list.isEmpty()) {
			fail("no data exist in database");
		}
		
	}
	}
