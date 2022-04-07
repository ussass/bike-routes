package ru.trofimov.bikeroutes.service.implement;

import org.springframework.stereotype.Service;
import ru.trofimov.bikeroutes.model.Route;
import ru.trofimov.bikeroutes.repositories.RouteRepository;
import ru.trofimov.bikeroutes.service.RouteService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    public RouteServiceImpl(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    @Override
    public Route save(Route route) {
        return routeRepository.save(route);
    }

    @Override
    public List<Route> findAll() {
        return StreamSupport.stream(routeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Route findById(Long id) {
        return routeRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}
