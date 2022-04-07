package ru.trofimov.bikeroutes.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.trofimov.bikeroutes.model.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {
}
