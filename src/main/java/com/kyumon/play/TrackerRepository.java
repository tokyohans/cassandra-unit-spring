package com.kyumon.play;

import org.springframework.data.cassandra.repository.TypedIdCassandraRepository;

public interface TrackerRepository extends TypedIdCassandraRepository<Tracker,String> {

}
