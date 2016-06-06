package com.kyumon.play;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitDependencyInjectionTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:cassandra-embedded-context.xml" })
@TestExecutionListeners( { 
	CassandraUnitDependencyInjectionTestExecutionListener.class,
	DependencyInjectionTestExecutionListener.class
})
@CassandraDataSet(value = { "tracker.cql" }, keyspace = "kyumoncore")
//@EmbeddedCassandra(configuration = "test-cassandra.yaml")
@EmbeddedCassandra
public class TrackerRepositoryTest {

	@Autowired
	private TrackerRepository rep;
	
	@Test
	public void test() {
		Tracker t = new Tracker();
		t.setUserId("x");
		Map<String, Map<String, Integer>> map = new HashMap<String, Map<String,Integer>>();
		Map<String,Integer> obj = new HashMap<String, Integer>();
		obj.put("11111", 7652);
		map.put("FACEBOOK", obj);
		t.setComments(map);
		
		rep.save(t);
		
		Tracker f = rep.findOne("x");
		assertEquals(f.getComments(), t.getComments());
	}
	
}
