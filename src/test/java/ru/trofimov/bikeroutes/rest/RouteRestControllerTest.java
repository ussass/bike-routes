package ru.trofimov.bikeroutes.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.bikeroutes.model.Route;
import ru.trofimov.bikeroutes.security.jwt.JwtProvider;
import ru.trofimov.bikeroutes.service.RouteService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RouteRestController.class)
class RouteRestControllerTest {

    @MockBean
    private RouteService routeService;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    @WithMockUser(roles = {"ADMIN", "USER"})
    @Test
    void whenGetAllRoute_returnOkStatus() throws Exception {
        String url = "/api/v1/routes";
        Route route = new Route();
        route.setId(1L);
        route.setTitle("test title");
        route.setDescription("test description");

        when(routeService.findAll()).thenReturn(Arrays.asList(route));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"title\":\"test title\"}]"));
    }

    @Test
    void whenGetAllRoute_returnForbiddenStatus() throws Exception {
        String url = "/api/v1/routes";
        Route route = new Route();
        route.setId(1L);
        route.setTitle("test title");
        route.setDescription("test description");

        when(routeService.findAll()).thenReturn(Arrays.asList(route));
        mockMvc.perform(get(url))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void whenAddRoute_returnCreatedStatus() throws Exception {
        String url = "/api/v1/routes";
        Route route = new Route();
        route.setTitle("test title");
        route.setDescription("test description");
        route.setId(10L);

        when(routeService.save(route)).thenReturn(route);
        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"10\",\"title\": \"test title\",\"description\": \"test description\"}")
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "http://localhost/api/v1/routes/10")).andDo(print());
    }

    @WithMockUser(roles = {"USER"})
    @Test
    void whenAddRoute_returnFobStatus() throws Exception {
        String url = "/api/v1/routes";
        Route route = new Route();
        route.setTitle("test title");
        route.setDescription("test description");
        route.setId(10L);

        when(routeService.save(route)).thenReturn(route);
        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": \"10\",\"title\": \"test title\",\"description\": \"test description\"}")
                )
                .andExpect(status().isForbidden());
    }
}