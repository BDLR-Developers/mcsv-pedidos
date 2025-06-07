package cl.duoc.mcsv_pedidos.model.dto.converter;

import org.junit.jupiter.api.Test;

import cl.duoc.mcsv_pedidos.model.dto.DetallePedidoDTO;
import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class DetallePedidoConverterTest {    
    @Test
    void convert_ShouldReturnEntityOk() {
        //given 
        DetallePedidoConverter converter = new DetallePedidoConverter();
        DetallePedidoEntity entity = DetallePedidoEntity.builder().cantidad(1).idProducto(2).numeroPedido(3).precio(100).build();
        //when
        DetallePedidoDTO dto = converter.convert(entity);
        //then
        assertEquals(entity.getCantidad(), dto.getCantidad());
        //probar los otros campos
    }

    @Test
    void convert_ShouldReturnEntityNull() {
        //given
        DetallePedidoConverter converter = new DetallePedidoConverter();
        DetallePedidoEntity entity = null;
        //when
        DetallePedidoDTO dto = converter.convert(entity);
        //then
        assertNull(dto);
    }

    @Test
    void convertToDTOList_ShouldReturnEntitiesOk() {
        //given
        DetallePedidoConverter converter = new DetallePedidoConverter();
        DetallePedidoEntity entity1 = DetallePedidoEntity.builder().cantidad(10).idProducto(1).numeroPedido(11).precio(111).build();
        DetallePedidoEntity entity2 = DetallePedidoEntity.builder().cantidad(20).idProducto(2).numeroPedido(22).precio(222).build();
        DetallePedidoEntity entity3 = DetallePedidoEntity.builder().cantidad(30).idProducto(3).numeroPedido(33).precio(333).build();
        List<DetallePedidoEntity> entities = Arrays.asList(entity1, entity2, entity3);
        //when
        List<DetallePedidoDTO> dtos = converter.convertToDTOList(entities);
        //then
        assertEquals(3, dtos.size());
        assertEquals(entity1.getCantidad(), dtos.get(0).getCantidad());
        assertEquals(entity2.getCantidad(), dtos.get(1).getCantidad());
        assertEquals(entity3.getCantidad(), dtos.get(2).getCantidad());
        assertEquals(entity1.getIdProducto(), dtos.get(0).getIdProducto()) ;
        assertEquals(entity2.getIdProducto(), dtos.get(1).getIdProducto()) ;
        assertEquals(entity3.getIdProducto(), dtos.get(2).getIdProducto()) ;
        assertEquals(entity1.getNumeroPedido(), dtos.get(0).getNumeroPedido());
        assertEquals(entity2.getNumeroPedido(), dtos.get(1).getNumeroPedido());
        assertEquals(entity3.getNumeroPedido(), dtos.get(2).getNumeroPedido());
        assertEquals(entity1.getPrecio(), dtos.get(0).getPrecio());
        assertEquals(entity2.getPrecio(), dtos.get(1).getPrecio());
        assertEquals(entity3.getPrecio(), dtos.get(2).getPrecio());
    }
}