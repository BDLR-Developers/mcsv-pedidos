package cl.duoc.mcsv_pedidos.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.mcsv_pedidos.assemblers.ProveedorModelAssembler;
import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.service.ProveedorService;

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProveedorService proveedorService;

    @MockitoBean
    private ProveedorModelAssembler proveedorModelAssembler;

    private static final Logger logger = LoggerFactory.getLogger(ProveedorControllerTest.class);

    @Test
    void testCreateProveedor() throws Exception {
        //Given
        String url = "/api/v1/proveedor";
        //Mock data
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(null)
                .fechaActualizacion(null)
                .idUsuario(10)
                .build();
        //Convert DTO to JSON string
        String requestBody = new ObjectMapper().writeValueAsString(proveedorDTO);
        //Mock services response
        when(proveedorService.createProveedor(proveedorDTO)).thenReturn(proveedorDTO);
        // Mock assembler response
        EntityModel<ProveedorDTO> entityModel = EntityModel.of(proveedorDTO);
        when(proveedorModelAssembler.toModel(proveedorDTO)).thenReturn(entityModel);

        //When
        MvcResult response = mockMvc.perform(post(url).contentType("application/json").content(requestBody)).andReturn();
        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());
        //Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();
        assertEquals(HttpStatus.CREATED.value(), responseStatus, "Status should be 201");
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testDeleteProveedor() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/proveedor/" + id;
        // Mock data
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(null)
                .fechaActualizacion(null)
                .idUsuario(10)
                .build();
        // Mock services response
        when(proveedorService.deleteProveedor(id)).thenReturn(Optional.of(proveedorDTO));
        // Mock assembler response
        EntityModel<ProveedorDTO> entityModel = EntityModel.of(proveedorDTO);
        when(proveedorModelAssembler.toModel(proveedorDTO)).thenReturn(entityModel);

        // When
        MvcResult response = mockMvc.perform(delete(url)).andReturn();

        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        // Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();
        assertEquals(HttpStatus.OK.value(), responseStatus, "Status should be 200");    
    }

    @Test
    void testGetAllProveedores() throws Exception {
        //Given
        String url = "/api/v1/proveedor/all";

        //Mock data
        ProveedorDTO proveedorDTO1 = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorDTO proveedorDTO2 = ProveedorDTO.builder()
                .idProveedor(2)
                .nombreProv("Proveedor Test 2")
                .telefonoProv("987654321")
                .correoProv("proveedor2@test.com")
                .fechaCreacion(LocalDate.of(2020, 5, 3))
                .fechaActualizacion(LocalDate.of(2021, 7, 6))
                .idUsuario(11)
                .build();
        List<ProveedorDTO> proveedores = List.of(proveedorDTO1, proveedorDTO2);
        
        //Mock services respones
        when(proveedorService.getAllProveedores()).thenReturn(proveedores);
        // Mock assembler response
        List<EntityModel<ProveedorDTO>> entityModels = List.of(
                EntityModel.of(proveedorDTO1),
                EntityModel.of(proveedorDTO2)
        );
        when(proveedorModelAssembler.toModel(proveedorDTO1)).thenReturn(entityModels.get(0));
        when(proveedorModelAssembler.toModel(proveedorDTO2)).thenReturn(entityModels.get(1));

        //When
        MvcResult response = mockMvc.perform(get(url)).andReturn();

        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        //Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();

        assertEquals(HttpStatus.OK.value(), responseStatus, "Status should be 200");
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testGetProveedorById() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/proveedor/" + id;

        // Mock data
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();

        // Mock services response
        when(proveedorService.getProveedorById(id)).thenReturn(Optional.of(proveedorDTO));
        // Mock assembler response
        EntityModel<ProveedorDTO> entityModel = EntityModel.of(proveedorDTO);
        when(proveedorModelAssembler.toModel(proveedorDTO)).thenReturn(entityModel);

        // When
        MvcResult response = mockMvc.perform(get(url)).andReturn();

        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        // Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();

        assertEquals(HttpStatus.OK.value(), responseStatus, "Status should be 200");
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testGetProveedorByNombre() throws Exception {
        // Given
        String nombre = "ProveedorTest";
        String url = "/api/v1/proveedor/nombre";

        // Mock data
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv(nombre)
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();

        // Mock services response
        when(proveedorService.getProveedorByNombre(nombre)).thenReturn(List.of(proveedorDTO));
        // Mock assembler response
        EntityModel<ProveedorDTO> entityModel = EntityModel.of(proveedorDTO);
        when(proveedorModelAssembler.toModel(proveedorDTO)).thenReturn(entityModel);

        // When
        MvcResult response = mockMvc.perform(get(url).param("nombre", nombre)).andReturn();
        
        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        // Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();

        assertEquals(HttpStatus.OK.value(), responseStatus, "Status should be 200");
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testGetStatus() throws Exception {
        // Given
        String url = "/api/v1/proveedor/status";

        // When
        MvcResult response = mockMvc.perform(get(url)).andReturn();

        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        // Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();

        assertEquals(HttpStatus.OK.value(), responseStatus, "Status should be 200");
        assertFalse(responseBody.isEmpty(), "Response body should be API is running");
    }

    @Test
    void testUpdateProveedor() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/proveedor/update/" + id;
        // Mock data
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test Updated")
                .telefonoProv("987654321")
                .correoProv("proveedor_updated@test.com")
                .fechaCreacion(null)
                .fechaActualizacion(null)
                .idUsuario(10)
                .build();
        String requestBody = new ObjectMapper().writeValueAsString(proveedorDTO);
        // Mock services response
        when(proveedorService.updateProveedor(proveedorDTO,id)).thenReturn(Optional.of(proveedorDTO));
        // Mock assembler response
        EntityModel<ProveedorDTO> entityModel = EntityModel.of(proveedorDTO);
        when(proveedorModelAssembler.toModel(proveedorDTO)).thenReturn(entityModel);

        // When
        MvcResult response = mockMvc.perform(put(url)
                .contentType("application/json")
                .content(requestBody))
                .andReturn();

        logger.info("Status: {}", response.getResponse().getStatus());
        logger.info("Response: {}", response.getResponse().getContentAsString());

        // Then
        int responseStatus = response.getResponse().getStatus();
        String responseBody = response.getResponse().getContentAsString();

        assertEquals(responseStatus, HttpStatus.CREATED.value(), "Status should be 201");
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        assertEquals("{\"idProveedor\":1,\"nombreProv\":\"Proveedor Test Updated\",\"telefonoProv\":\"987654321\",\"correoProv\":\"proveedor_updated@test.com\",\"fechaCreacion\":null,\"fechaActualizacion\":null,\"idUsuario\":10}", responseBody, "Response body should match expected");
    }
}
