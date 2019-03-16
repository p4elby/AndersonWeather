package com.dbService.repositories;

import com.dbService.entity.Forecast;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast,Long> {
}
