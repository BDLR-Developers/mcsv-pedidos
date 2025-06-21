package cl.duoc.mcsv_pedidos.service;

import java.time.LocalDate;
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
import cl.duoc.mcsv_pedidos.model.entity.clavescompuestas.DetallePedidoPrimaryKey;
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
        DetallePedidoPrimaryKey detallePedidoPrimaryKey = new DetallePedidoPrimaryKey(detallePedido.getNumeroPedido(), detallePedido.getIdProducto());
        DetallePedidoEntity detallePedido2 = DetallePedidoEntity.builder()
        .numeroPedido(id)
        .idProducto(20)
        .cantidad(200)
        .precio(2000)
        .build();
        DetallePedidoPrimaryKey detallePedido2PrimaryKey = new DetallePedidoPrimaryKey(detallePedido2.getNumeroPedido(), detallePedido2.getIdProducto());
        pedido.get().setDetallePedidoEntitys(List.of(detallePedido, detallePedido2));


        when(pedidoService.getPedidoById(id)).thenReturn(pedido);
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(pedidoDTO);
        when(detallePedidoService.getDetallePedidoByNumeroPedido(id)).thenReturn(List.of(detallePedido, detallePedido2));
        when(pedidoConverter.convertToEntity(pedidoDTO)).thenReturn(pedidoEntity);
        // When
        Optional<PedidoDTO> result = pedidoService.delete(id);
        // Then
        verify(pedidoRepository).delete(pedidoEntity);
        verify(detallePedidoService).eliminar(detallePedidoPrimaryKey);
        verify(detallePedidoService).eliminar(detallePedido2PrimaryKey);
        assert result.isPresent();
    }

    @Test
    void testGetAllPedidos() {
        // Given
        PedidoEntity pedido1 = PedidoEntity.builder().numeroPedido(1).build();
        PedidoEntity pedido2 = PedidoEntity.builder().numeroPedido(2).build();
        PedidoEntity pedido3 = PedidoEntity.builder().numeroPedido(3).build();
        List<PedidoEntity> listaPedidos = List.of(pedido1, pedido2, pedido3);

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
        
        when(pedidoRepository.findAll()).thenReturn(listaPedidos);
        
        PedidoDTO dto1 = PedidoDTO.builder().numeroPedido(1).build();
        PedidoDTO dto2 = PedidoDTO.builder().numeroPedido(2).build();
        PedidoDTO dto3 = PedidoDTO.builder().numeroPedido(3).build();
        
        when(pedidoConverter.convertToDTOList(listaPedidos)).thenReturn(List.of(dto1, dto2, dto3));
        
        when(detallePedidoService.getDetallePedidoByNumeroPedido(1)).thenReturn(List.of(detalle1Pedido1, detalle2Pedido1));
        when(detallePedidoService.getDetallePedidoByNumeroPedido(2)).thenReturn(List.of());
        when(detallePedidoService.getDetallePedidoByNumeroPedido(3)).thenReturn(List.of(detalle1Pedido3));
        // When
        List<PedidoDTO> resultado = pedidoService.getAllPedidos();
        
        // Then
        assert resultado.size() == 3;
        assert resultado.contains(dto1);
        assert resultado.contains(dto2);
        assert resultado.contains(dto3);
        verify(pedidoRepository).findAll();
        verify(detallePedidoService).getDetallePedidoByNumeroPedido(1);
        verify(detallePedidoService).getDetallePedidoByNumeroPedido(2);
        verify(detallePedidoService).getDetallePedidoByNumeroPedido(3);
        assert resultado.get(0).getDetallePedidoEntitys().size() == 2;
        assert resultado.get(0).getDetallePedidoEntitys().contains(detalle1Pedido1);
        assert resultado.get(0).getDetallePedidoEntitys().contains(detalle2Pedido1);
        assert resultado.get(1).getDetallePedidoEntitys().isEmpty();
        assert resultado.get(2).getDetallePedidoEntitys().size() == 1;
        assert resultado.get(2).getDetallePedidoEntitys().contains(detalle1Pedido3);
    }

    @Test
    void testGetPedidoById() {
        // Given
        Integer id = 1;
        PedidoEntity pedidoEntity = PedidoEntity.builder().numeroPedido(id).build();
        DetallePedidoEntity detallePedido1 = DetallePedidoEntity.builder()
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
        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(PedidoDTO.builder().numeroPedido(id).build());
        when(detallePedidoService.getDetallePedidoByNumeroPedido(id)).thenReturn(List.of(detallePedido1, detallePedido2));
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(PedidoDTO.builder().numeroPedido(id).build());
        // When
        Optional<PedidoDTO> result = pedidoService.getPedidoById(id);

        // Then
        assert result.isPresent();
        assert Integer.valueOf(result.get().getNumeroPedido()).equals(id);
        assert result.get().getDetallePedidoEntitys().size() == 2;
        assert result.get().getDetallePedidoEntitys().contains(detallePedido1);
        assert result.get().getDetallePedidoEntitys().contains(detallePedido2); 
        verify(pedidoRepository).findById(id);
        verify(pedidoConverter).convert(pedidoEntity);
        verify(detallePedidoService).getDetallePedidoByNumeroPedido(id);
    }

    @Test
    void testGuardar() {
        // Given
        DetallePedidoEntity detallePedido1 = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(10)
                .cantidad(100)
                .precio(1000)
                .build();
        DetallePedidoEntity detallePedido2 = DetallePedidoEntity.builder()
                .numeroPedido(1)
                .idProducto(20)
                .cantidad(200)
                .precio(2000)
                .build();
        PedidoDTO pedidoDTO = PedidoDTO.builder().
            numeroPedido(1).
            estadoPedido(5).
            fechaPedido(LocalDate.of(2020, 5, 3)).
            idBodega(2).
            idProveedor(3).
            idUsuario(4).
            montoPedido(233).
            detallePedidoEntitys(List.of(detallePedido1, detallePedido2)).
            build();
        PedidoEntity pedidoEntity = PedidoEntity.builder()
            .numeroPedido(1)
            .estadoPedido(5)
            .fechaPedido(LocalDate.of(2020, 5, 3))
            .idBodega(2)
            .idProveedor(3)
            .idUsuario(4)
            .montoPedido(233)
            .build();
        
        when(detallePedidoService.guardar(detallePedido1)).thenReturn(detallePedido1);
        when(detallePedidoService.guardar(detallePedido2)).thenReturn(detallePedido2);
        when(pedidoConverter.convertToEntity(pedidoDTO)).thenReturn(pedidoEntity);
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(pedidoDTO);
        // When
        PedidoDTO result = pedidoService.guardar(pedidoDTO);
        // Then
        assert result != null;
        assert result.getNumeroPedido() == 1;
        assert result.getEstadoPedido() == 5;
        assert result.getFechaPedido().equals(LocalDate.of(2020, 5, 3));
        assert result.getIdBodega() == 2;
        assert result.getIdProveedor() == 3;
        assert result.getIdUsuario() == 4;
        assert result.getMontoPedido() == 233;
        assert result.getDetallePedidoEntitys().size() == 2;
        assert result.getDetallePedidoEntitys().contains(detallePedido1);
        assert result.getDetallePedidoEntitys().contains(detallePedido2);
        verify(pedidoConverter).convertToEntity(pedidoDTO);
        verify(pedidoRepository).save(pedidoEntity);
        verify(pedidoConverter).convert(pedidoEntity);
        verify(detallePedidoService).guardar(detallePedido1);
        verify(detallePedidoService).guardar(detallePedido2);
    }

    @Test
    void testUpdate() {
        // Given
        Integer id = 1;
        DetallePedidoEntity detallePedido1 = DetallePedidoEntity.builder()
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
        PedidoDTO pedidoDTOUpdate = PedidoDTO.builder().
            numeroPedido(id).
            estadoPedido(5).
            fechaPedido(LocalDate.of(2020, 5, 3)).
            idBodega(2).
            idProveedor(3).
            idUsuario(4).
            montoPedido(233).
            detallePedidoEntitys(List.of(
                detallePedido1,
                detallePedido2
            )).
            build();
            
        PedidoEntity pedidoEntity = PedidoEntity.builder()
            .numeroPedido(id)
            .estadoPedido(5)
            .fechaPedido(LocalDate.of(2020, 5, 3))
            .idBodega(2)
            .idProveedor(3)
            .idUsuario(4)
            .montoPedido(233)
            .build();

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoEntity));
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(pedidoDTOUpdate);
        when(detallePedidoService.getDetallePedidoByNumeroPedido(id)).thenReturn(List.of(detallePedido1, detallePedido2));
        when(detallePedidoService.guardar(detallePedido1)).thenReturn(detallePedido1);
        when(detallePedidoService.guardar(detallePedido2)).thenReturn(detallePedido2);
        when(pedidoConverter.convertToEntity(pedidoDTOUpdate)).thenReturn(pedidoEntity);
        when(pedidoRepository.save(pedidoEntity)).thenReturn(pedidoEntity);
        when(pedidoConverter.convert(pedidoEntity)).thenReturn(pedidoDTOUpdate);
      
        // When
        Optional<PedidoDTO> result = pedidoService.update(id, pedidoDTOUpdate);

        // Then
        assert result.isPresent();
        assert result.get().getNumeroPedido() == id;
        verify(pedidoRepository).findById(id);
        verify(detallePedidoService).guardar(detallePedido1);
        verify(detallePedidoService).guardar(detallePedido2);
        verify(pedidoConverter).convertToEntity(pedidoDTOUpdate);
        verify(pedidoRepository).save(pedidoEntity);
        

    }
}
