package ru.trofimov.bikeroutes.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.trofimov.bikeroutes.model.User;
import ru.trofimov.bikeroutes.security.jwt.JwtProvider;
import ru.trofimov.bikeroutes.service.UserService;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserRestController.class)
class UserRestControllerTest {

    @MockBean
    private UserService userService;

    @MockBean
    private JwtProvider jwtProvider;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void whenLogin_returnToken() throws Exception {
        String url = "/api/v1/users/login";
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");

        when(userService.findByLoginAndPassword(user.getLogin(), user.getPassword())).thenReturn(user);
        mockMvc
                .perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"login\": \"admin\",\"password\": \"admin\"}")
                )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"token\":null}"));
    }

    @WithMockUser(roles = {"ADMIN"})
    @Test
    void whenGetAllUsers_returnOkStatus() throws Exception {
        String url = "/api/v1/users";
        User user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setStringRole("USER");

        when(userService.findAll()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"login\":\"user\",\"role\":\"USER\"}]"));
    }

    @WithMockUser(roles = {"USER"})
    @Test
    void whenGetAllUsers_returnForbiddenStatus() throws Exception {
        String url = "/api/v1/users";
        User user = new User();
        user.setLogin("user");
        user.setPassword("password");
        user.setStringRole("USER");

        when(userService.findAll()).thenReturn(Arrays.asList(user));
        mockMvc.perform(get(url))
                .andExpect(status().isForbidden());
    }
}