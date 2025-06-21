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
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.service.PedidoService;

@WebMvcTest(PedidoController.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;
    
    private static Logger logger = LoggerFactory.getLogger(PedidoControllerTest.class);

    @Test
    void testActualizar() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/pedido/" + id;
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .numeroPedido(1)
                .fechaPedido(LocalDate.of(2023, 3, 2))
                .montoPedido(12)
                .estadoPedido(1)
                .idProveedor(1)
                .idUsuario(1)
                .idBodega(1)
                .detallePedidoEntitys(null) 
                .build();

        // Mock service response
        when(pedidoService.update(id, pedidoDTO)).thenReturn(Optional.of(pedidoDTO));

        // When
        MvcResult result = mockMvc.perform(put(url)
                .contentType("application/json")
                .content("{ \"numeroPedido\": 1, \"fechaPedido\": \"2023-03-02\", \"montoPedido\": 12, \"estadoPedido\": 1, \"idProveedor\": 1, \"idUsuario\": 1, \"idBodega\": 1, \"detallePedidoEntitys\": null }"))
                .andExpect(status().isOk())
                .andReturn();
        logger.info("Response: {}", result.getResponse().getContentAsString());
        logger.info("Status: {}", result.getResponse().getStatus());

        // Then
        String responseBody = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
    }

    @Test
    void testDelete() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/pedido/" + id;
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .numeroPedido(1)
                .fechaPedido(LocalDate.of(2023, 3, 2))
                .montoPedido(12)
                .estadoPedido(1)
                .idProveedor(1)
                .idUsuario(1)
                .idBodega(1)
                .detallePedidoEntitys(null) 
                .build();

        // Mock service response
        when(pedidoService.delete(id)).thenReturn(Optional.of(pedidoDTO));

        // When
        MvcResult result = mockMvc.perform(delete(url))
                .andExpect(status().isOk())
                .andReturn();
        logger.info("Response: {}", result.getResponse().getContentAsString());
        logger.info("Status: {}", result.getResponse().getStatus());

        // Then
        String responseBody = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");

    }

    @Test
    void testGetAllPedidos() throws Exception {
        //Given
        String url = "/api/v1/pedido/all";
        
        //Mock Data
        
        DetallePedidoEntity detalle1Pedido1 = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();

        DetallePedidoEntity detalle2Pedido1 = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(20)
                .cantidad(200)
                .precio(2000)
                .build();
        DetallePedidoEntity detalle1Pedido3 = DetallePedidoEntity.builder()
                .numeroPedido(3)
                .idProducto(30)
                .cantidad(300)
                .precio(3000)
                .build();
        PedidoDTO pedidoDTO1 = PedidoDTO.builder()
                .numeroPedido(1)
                .fechaPedido(LocalDate.of(2023, 3, 2))
                .montoPedido(12)
                .estadoPedido(1)
                .idProveedor(1)
                .idUsuario(1)
                .idBodega(1)
                .detallePedidoEntitys(List.of(detalle1Pedido1, detalle2Pedido1)) 
                .build();
        PedidoDTO pedidoDTO2 = PedidoDTO.builder()
                .numeroPedido(2)
                .fechaPedido(LocalDate.of(2023, 3, 3))
                .montoPedido(34)
                .estadoPedido(1)
                .idProveedor(2)
                .idUsuario(2)
                .idBodega(2)
                .detallePedidoEntitys(null) 
                .build();
        PedidoDTO pedidoDTO3 = PedidoDTO.builder()
                .numeroPedido(3)
                .fechaPedido(LocalDate.of(2023, 3, 4))
                .montoPedido(56)
                .estadoPedido(1)
                .idProveedor(3)
                .idUsuario(3)
                .idBodega(3)
                .detallePedidoEntitys(List.of(detalle1Pedido3)) 
                .build();
        List<PedidoDTO> pedidos = List.of(pedidoDTO1, pedidoDTO2, pedidoDTO3);

        //Mock service responses
        when(pedidoService.getAllPedidos()).thenReturn(pedidos);

        //When
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();
        logger.info("Response: {}", result.getResponse().getContentAsString());
        logger.info("Status: {}", result.getResponse().getStatus());

        //Then
        String responseBody = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        assertEquals("[{\"numeroPedido\":1,\"fechaPedido\":\"2023-03-02\",\"montoPedido\":12,\"estadoPedido\":1,\"idProveedor\":1,\"idUsuario\":1,\"idBodega\":1,\"detallePedidoEntitys\":[{\"numeroPedido\":1,\"idProducto\":10,\"cantidad\":100,\"precio\":1000},{\"numeroPedido\":1,\"idProducto\":20,\"cantidad\":200,\"precio\":2000}]},{\"numeroPedido\":2,\"fechaPedido\":\"2023-03-03\",\"montoPedido\":34,\"estadoPedido\":1,\"idProveedor\":2,\"idUsuario\":2,\"idBodega\":2,\"detallePedidoEntitys\":null},{\"numeroPedido\":3,\"fechaPedido\":\"2023-03-04\",\"montoPedido\":56,\"estadoPedido\":1,\"idProveedor\":3,\"idUsuario\":3,\"idBodega\":3,\"detallePedidoEntitys\":[{\"numeroPedido\":3,\"idProducto\":30,\"cantidad\":300,\"precio\":3000}]}]", responseBody);
    }

    @Test
    void testGetPedido() throws Exception {
        // Given
        int id = 1;
        String url = "/api/v1/pedido/" + id;
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .numeroPedido(1)
                .fechaPedido(LocalDate.of(2023, 3, 2))
                .montoPedido(12)
                .estadoPedido(1)
                .idProveedor(1)
                .idUsuario(1)
                .idBodega(1)
                .detallePedidoEntitys(null) 
                .build();
        // Mock service response
        when(pedidoService.getPedidoById(id)).thenReturn(java.util.Optional.of(pedidoDTO));
        // When
        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();
        logger.info("Response: {}", result.getResponse().getContentAsString());
        logger.info("Status: {}", result.getResponse().getStatus());
        // Then
        String responseBody = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        assertEquals("{\"numeroPedido\":1,\"fechaPedido\":\"2023-03-02\",\"montoPedido\":12,\"estadoPedido\":1,\"idProveedor\":1,\"idUsuario\":1,\"idBodega\":1,\"detallePedidoEntitys\":null}", responseBody);
    }

    @Test
    void testGuardar() throws Exception {
        //Given
        String url = "/api/v1/pedido";
        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .numeroPedido(1)
                .fechaPedido(LocalDate.of(2023, 3, 2))
                .montoPedido(12)
                .estadoPedido(1)
                .idProveedor(1)
                .idUsuario(1)
                .idBodega(1)
                .detallePedidoEntitys(null) 
                .build();
        //Convert DTO to JSON
        String pedidoJson = "{ \"numeroPedido\": 1, \"fechaPedido\": \"2023-03-02\", \"montoPedido\": 12, \"estadoPedido\": 1, \"idProveedor\": 1, \"idUsuario\": 1, \"idBodega\": 1, \"detallePedidoEntitys\": null }";
        //Mock service response
        when(pedidoService.guardar(pedidoDTO)).thenReturn(pedidoDTO);
        //When
        MvcResult result = mockMvc.perform(post(url)
                .contentType("application/json")
                .content(pedidoJson))
                .andExpect(status().isCreated())
                .andReturn();
        logger.info("Response: {}", result.getResponse().getContentAsString());
        logger.info("Status: {}", result.getResponse().getStatus());
        //Then
        String responseBody = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();

        assertEquals(HttpStatus.CREATED.value(), status);
        assertFalse(responseBody.isEmpty(), "Response body should not be empty");
        assertEquals("{\"numeroPedido\":1,\"fechaPedido\":\"2023-03-02\",\"montoPedido\":12,\"estadoPedido\":1,\"idProveedor\":1,\"idUsuario\":1,\"idBodega\":1,\"detallePedidoEntitys\":null}", responseBody);
    }
}
