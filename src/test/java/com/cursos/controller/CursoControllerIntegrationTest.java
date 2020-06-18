package com.cursos.controller;

import com.cursos.repository.CursoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CursoControllerIntegrationTest {

    private static final String CURSO_BASE_URL_SUFFIX = "/cursos";
    private static final String CURSO_ID_SUFFIX = CURSO_BASE_URL_SUFFIX + "/id";
    private static final String CURSO_ID_EXISTENTE = "1";
    private static final String CURSO_ID_NAO_EXISTENTE = "2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepository cursoRepository;


    @Test
    public void testGetCursoExistenteByIDShouldReturnCurso() throws Exception {
        String uri = CURSO_ID_SUFFIX + "/" + CURSO_ID_EXISTENTE;

       this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Programação Orientada a Objetos"))
                .andExpect(jsonPath("$.dataInicio").value("01/08/2020"))
                .andExpect(jsonPath("$.dataTermino").value("01/09/2020"))
                .andExpect(jsonPath("$.quantidadeAlunos").value(30))
                .andExpect(jsonPath("$.categoria").value(2));
    }

    @Test
    public void testGetCursoNaoExistenteByIDShouldReturnCursoNotFound() throws Exception {
        String uri = CURSO_ID_SUFFIX + "/" + CURSO_ID_NAO_EXISTENTE;

        this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void testAddCursoShouldReturnCursoCreated() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 1\"," +
                        "\"dataInicio\": \"01/01/2021\", " +
                        "\"dataTermino\": \"01/02/2021\", " +
                        "\"quantidadeAlunos\":10," +
                        "\"categoria\":1}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Transactional
    public void testAddCursoComDataInicioPosteriorDataTerminoShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 2\"," +
                        "\"dataInicio\": \"01/10/2021\", " +
                        "\"dataTermino\": \"01/09/2021\", " +
                        "\"quantidadeAlunos\":8," +
                        "\"categoria\":1}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComPeriodoJaExistenteShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 3\"," +
                        "\"dataInicio\": \"15/08/2020\", " +
                        "\"dataTermino\": \"15/09/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        "\"categoria\":1}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComCategoriaInvalidaShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 4\"," +
                        "\"dataInicio\": \"15/12/2020\", " +
                        "\"dataTermino\": \"31/12/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        "\"categoria\":10}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComDescricaoJaExistenteShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Programação Orientada a Objetos\"," +
                        "\"dataInicio\": \"15/12/2020\", " +
                        "\"dataTermino\": \"31/12/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        "\"categoria\":10}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @Transactional
    public void testDeleteCursoExistenteShouldReturnNoContent() throws Exception {
        String uri = CURSO_ID_SUFFIX + "/" + CURSO_ID_EXISTENTE;

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void testDeleteCursoNaoExistenteShouldReturnCursoNotFound() throws Exception {
        String uri = CURSO_ID_SUFFIX + "/" + CURSO_ID_NAO_EXISTENTE;

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

