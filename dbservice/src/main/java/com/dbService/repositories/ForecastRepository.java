package com.dbService.repositories;

import com.dbService.entity.Forecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast,Long> {
}
