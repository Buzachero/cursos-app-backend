package com.cursos.controller;

import com.cursos.repository.CursoRepository;
import com.cursos.util.CursoConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static com.cursos.util.CursoConstants.ExceptionMessages.CURSO_EXISTENTE;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CursoControllerIntegrationTest {

    private static final String CURSO_BASE_URL_SUFFIX = "/cursos";
    private static final String CURSO_ID_EXISTENTE = "1";
    private static final String CURSO_ID_NAO_EXISTENTE = "2";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CursoRepository cursoRepository;


    @Test
    public void testGetCursoExistenteByIDShouldReturnCurso() throws Exception {
        String uri = CURSO_BASE_URL_SUFFIX + "/" + CURSO_ID_EXISTENTE;

       this.mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.descricao").value("Programação Orientada a Objetos"))
                .andExpect(jsonPath("$.dataInicio").value("01/08/2020"))
                .andExpect(jsonPath("$.dataTermino").value("01/09/2020"))
                .andExpect(jsonPath("$.quantidadeAlunos").value(30))
                .andExpect(jsonPath("$.categoria").exists())
                .andExpect(jsonPath("$.categoria.codigo").value(2))
                .andExpect(jsonPath("$.categoria.descricao").value("PROGRAMAÇÃO"));

    }

    @Test
    public void testGetCursoNaoExistenteByIDShouldReturnCursoNotFound() throws Exception {
        String uri = CURSO_BASE_URL_SUFFIX + "/" + CURSO_ID_NAO_EXISTENTE;

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
                        " \"categoria\": {" +
                        "        \"codigo\": 2, " +
                        "        \"descricao\": \"PROGRAMAÇÃO\" " +
                        "    }" +
                        "}"))
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
                        " \"categoria\": {" +
                        "        \"codigo\": 2, " +
                        "        \"descricao\": \"PROGRAMAÇÃO\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComDataInicioDentroPeriodoJaExistenteShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 3\"," +
                        "\"dataInicio\": \"15/08/2020\", " +
                        "\"dataTermino\": \"15/09/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        " \"categoria\": {" +
                        "        \"codigo\": 1, " +
                        "        \"descricao\": \"COMPORTAMENTAL\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComDataTerminoDentroPeriodoJaExistenteShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 4\"," +
                        "\"dataInicio\": \"20/07/2020\", " +
                        "\"dataTermino\": \"20/08/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        " \"categoria\": {" +
                        "        \"codigo\": 1, " +
                        "        \"descricao\": \"COMPORTAMENTAL\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComDataInicioEDataTerminoForaPeriodoJaExistenteShouldReturnBadRequest() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 5\"," +
                        "\"dataInicio\": \"01/06/2020\", " +
                        "\"dataTermino\": \"01/10/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        " \"categoria\": {" +
                        "        \"codigo\": 1, " +
                        "        \"descricao\": \"COMPORTAMENTAL\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Transactional
    public void testAddCursoComCategoriaInvalidaShouldReturnBadRequest() throws Exception {
        Integer categoriaIdInvalida = 10;
        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\":\"Descricao de exemplo 6\"," +
                        "\"dataInicio\": \"15/12/2020\", " +
                        "\"dataTermino\": \"31/12/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        " \"categoria\": {" +
                        "        \"codigo\": " + categoriaIdInvalida + "," +
                        "        \"descricao\": \"OUTRA CATEGORIA\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(String.format(CursoConstants.ExceptionMessages.CATEGORIA_INVALIDA, categoriaIdInvalida)));
    }

    @Test
    @Transactional
    public void testAddCursoComDescricaoJaExistenteShouldReturnBadRequest() throws Exception {
        final String descricaoCursoExistente = "Programação Orientada a Objetos";

        this.mockMvc.perform(MockMvcRequestBuilders.post(CURSO_BASE_URL_SUFFIX)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"descricao\": \"" + descricaoCursoExistente + "\", " +
                        "\"dataInicio\": \"15/12/2020\", " +
                        "\"dataTermino\": \"31/12/2020\", " +
                        "\"quantidadeAlunos\":15," +
                        " \"categoria\": {" +
                        "        \"codigo\": 2, " +
                        "        \"descricao\": \"PROGRAMAÇÃO\" " +
                        "    }" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(String.format(CURSO_EXISTENTE, descricaoCursoExistente)));
    }

    @Test
    @Transactional
    public void testDeleteCursoExistenteShouldReturnNoContent() throws Exception {
        String uri = CURSO_BASE_URL_SUFFIX + "/" + CURSO_ID_EXISTENTE;

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Transactional
    public void testDeleteCursoNaoExistenteShouldReturnCursoNotFound() throws Exception {
        String uri = CURSO_BASE_URL_SUFFIX + "/" + CURSO_ID_NAO_EXISTENTE;

        this.mockMvc.perform(MockMvcRequestBuilders.delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}

