package cl.duoc.mcsv_pedidos.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.model.dto.converter.PedidoConverter;
import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.model.entity.PedidoEntity;
import cl.duoc.mcsv_pedidos.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private DetallePedidoService detallePedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private PedidoConverter pedidoConverter;
    
    @Test
    void testDelete() {
        // Given
        Integer id = 1;
        PedidoDTO pedidoDTO = PedidoDTO.builder().numeroPedido(id).build();
        PedidoEntity pedidoEntity = PedidoEntity.builder().numeroPedido(id).build();
        Optional<PedidoDTO> pedido = Optional.of(pedidoDTO);
        DetallePedidoEntity detallePedido = DetallePedidoEntity.builder()
                .numeroPedido(id)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();
        DetallePedidoEntity detallePedido2 = DetallePedidoEntity.builder()
                .numeroPedido(id)
                .idProducto(20)
                .cantidad(200)
                .precio(2000)
                .build();
        pedido.get().setDetallePedidoEntitys(List.of(detallePedido, detallePedido2));


        when(pedidoService.getPedidoById(id)).thenReturn(pedido);
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(pedidoDTO);
        when(detallePedidoService.getDetallePedidoByNumeroPedido(id)).thenReturn(List.of(detallePedido, detallePedido2));
        when(pedidoConverter.convertToEntity(pedidoDTO)).thenReturn(pedidoEntity);
        // When
        pedidoService.delete(id);
        // Then
        // Verify that the delete method in the repository was called with the correct id
        verify(pedidoRepository).deleteById(id);
        
    }

    @Test
    void testGetAllPedidos() {

    }

    @Test
    void testGetPedidoById() {

    }

    @Test
    void testGuardar() {

    }

    @Test
    void testUpdate() {

    }
}
