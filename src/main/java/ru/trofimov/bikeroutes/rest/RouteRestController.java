package ru.trofimov.bikeroutes.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.trofimov.bikeroutes.exception.InvalidRequestBodyException;
import ru.trofimov.bikeroutes.model.Route;
import ru.trofimov.bikeroutes.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/routes")
public class RouteRestController {

    private final RouteService routeService;

    public RouteRestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping
    public ResponseEntity<List<Route>> getAllRoutes() {

        List<Route> list = routeService.findAll().stream()
                .peek(route -> route.setDescription(null))
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Route> getRoute(@PathVariable long id) {

        Route route = routeService.findById(id);
        return new ResponseEntity<>(route, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Route> saveRoute(HttpServletRequest request, @RequestBody Route route) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (route.getTitle() == null) {
            throw new InvalidRequestBodyException("invalid request body");
        }
        routeService.save(route);
        httpHeaders.add("Location", request.getRequestURL().toString() + "/" + route.getId());

        return new ResponseEntity<>(route, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Route> updateCategory(@PathVariable long id, @RequestBody Route route) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (route.getTitle() == null) {
            throw new InvalidRequestBodyException("invalid request body");
        }
        route.setId(id);
        routeService.save(route);

        return new ResponseEntity<>(route, httpHeaders, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Route> deleteCategory(@PathVariable long id) {

        routeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
