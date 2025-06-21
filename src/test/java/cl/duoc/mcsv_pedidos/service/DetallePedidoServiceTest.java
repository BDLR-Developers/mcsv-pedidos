package cl.duoc.mcsv_pedidos.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.model.entity.clavescompuestas.DetallePedidoPrimaryKey;
import cl.duoc.mcsv_pedidos.repository.DetallePedidoRepository;

@ExtendWith(MockitoExtension.class)
public class DetallePedidoServiceTest {

    @InjectMocks
    private DetallePedidoService detallePedidoService;

    @Mock
    private DetallePedidoRepository detallePedidoRepository;
    
    @Test
    void testEliminar() {
        //given
        DetallePedidoPrimaryKey id = new DetallePedidoPrimaryKey(1, 1);
        //when
        detallePedidoService.eliminar(id);
        //then
        verify(detallePedidoRepository).deleteById(id);
    }

    @Test
    void testGetAllDetallePedidos() {
        // Given
        DetallePedidoEntity detallePedido1 = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();
        DetallePedidoEntity detallePedido2 = DetallePedidoEntity.builder()
                .numeroPedido(2)
                .idProducto(20)
                .cantidad(200)
                .precio(2000)
                .build();
        DetallePedidoEntity detallePedido3 = DetallePedidoEntity.builder()
                .numeroPedido(3)
                .idProducto(30)
                .cantidad(300)
                .precio(3000)
                .build();
        List<DetallePedidoEntity> lista = List.of(detallePedido1, detallePedido2, detallePedido3);
        when(detallePedidoRepository.findAll()).thenReturn(lista);
        // When
        List<DetallePedidoEntity> resultado = detallePedidoService.getAllDetallePedidos();
        // Then
        assert resultado.size() == 3;
        assert resultado.contains(detallePedido1);
        assert resultado.contains(detallePedido2);
        assert resultado.contains(detallePedido3);
        verify(detallePedidoRepository).findAll();
    }

    @Test
    void testGetDetallePedidoByNumeroPedido() {
        // Given
        int numeroPedido = 1;
        DetallePedidoEntity detallePedido1 = DetallePedidoEntity.builder()
                .numeroPedido(numeroPedido)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();
        DetallePedidoEntity detallePedido2 = DetallePedidoEntity.builder()
                .numeroPedido(numeroPedido)
                .idProducto(20)
                .cantidad(200)
                .precio(2000)
                .build();
        List<DetallePedidoEntity> lista = List.of(detallePedido1, detallePedido2);
        when(detallePedidoRepository.findByNumeroPedido(numeroPedido)).thenReturn(lista);
        // When
        List<DetallePedidoEntity> resultado = detallePedidoService.getDetallePedidoByNumeroPedido(numeroPedido);
        // Then
        assert resultado.size() == 2;
        assert resultado.contains(detallePedido1);
        assert resultado.contains(detallePedido2);
        verify(detallePedidoRepository).findByNumeroPedido(numeroPedido);
    }

    @Test
    void testGuardar() {
        // Given
        DetallePedidoEntity detallePedido = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();
        when(detallePedidoRepository.save(detallePedido)).thenReturn(detallePedido);
        // When
        DetallePedidoEntity resultado = detallePedidoService.guardar(detallePedido);
        // Then
        assert resultado != null;
        assert resultado.getNumeroPedido() == 1;
        assert resultado.getIdProducto() == 10;
        assert resultado.getCantidad() == 100;
        assert resultado.getPrecio() == 1000;
        verify(detallePedidoRepository).save(detallePedido);
    }
}
