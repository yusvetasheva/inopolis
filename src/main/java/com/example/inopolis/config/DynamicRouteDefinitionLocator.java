package com.example.inopolis.config;

import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DynamicRouteDefinitionLocator implements RouteDefinitionLocator {
    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routes = new ArrayList<>();

        RouteDefinition route = new RouteDefinition();
        route.setId("course-route");

        // 1. URI куда маршрут будет вести (Gateway сам проксирует метод, тело и параметры)
        route.setUri(URI.create("http://localhost:8081"));

        // 2. Predicate: по какому пути
        PredicateDefinition predicate = new PredicateDefinition();
        predicate.setName("Path");
        predicate.addArg("pattern", "/api/student/add-comment");

        // 3. Filter: переписать путь
        FilterDefinition filter = new FilterDefinition();
        filter.setName("RewritePath");
        filter.addArg("regexp", "/api/student/(?<segment>.*)");
        filter.addArg("replacement", "/api/course/${segment}");

        route.setPredicates(List.of(predicate));
        route.setFilters(List.of(filter));

        routes.add(route);

        return Flux.fromIterable(routes);
    }
}
