package ru.trofimov.bikeroutes.service;

import ru.trofimov.bikeroutes.model.Route;

import java.util.List;

public interface RouteService {

    Route save(Route route);

    List<Route> findAll();

    Route findById(Long id);

    void delete(Long id);
}
