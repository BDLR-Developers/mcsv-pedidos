package cl.duoc.mcsv_pedidos.model.dto.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.model.entity.PedidoEntity;

public class PedidoConverterTest {
    @Test
    void convert_ShouldReturnEntityOk() {
        //given
        PedidoConverter converter = new PedidoConverter();
        PedidoEntity entity = PedidoEntity.builder().estadoPedido(1).
            fechaPedido(LocalDate.of(2000,12,6)).idBodega(2).idProveedor(3).
            idUsuario(4).montoPedido(5).numeroPedido(6).build();
        //when
        PedidoDTO dto = converter.convert(entity);
        //then
        assertEquals(entity.getEstadoPedido(), dto.getEstadoPedido());
        assertEquals(entity.getFechaPedido(), dto.getFechaPedido());
        assertEquals(entity.getIdBodega(), dto.getIdBodega());
        assertEquals(entity.getIdProveedor(), dto.getIdProveedor());
        assertEquals(entity.getIdUsuario(), dto.getIdUsuario());
        assertEquals(entity.getMontoPedido(), dto.getMontoPedido());
        assertEquals(entity.getNumeroPedido(), dto.getNumeroPedido());
    }

    @Test
    void convertToDTOList_ShouldReturnEntitiesOk() {
        //given
        PedidoConverter converter = new PedidoConverter();
        PedidoEntity entity1 = PedidoEntity.builder().estadoPedido(1).
            fechaPedido(LocalDate.of(2000,12,6)).idBodega(2).idProveedor(3).
            idUsuario(4).montoPedido(5).numeroPedido(6).build();
        PedidoEntity entity2 = PedidoEntity.builder().estadoPedido(10).
        fechaPedido(LocalDate.of(2010,6,3)).idBodega(20).idProveedor(30).
        idUsuario(40).montoPedido(50).numeroPedido(60).build();
        PedidoEntity entity3 = PedidoEntity.builder().estadoPedido(100).
        fechaPedido(LocalDate.of(1997,5,20)).idBodega(200).idProveedor(300).
        idUsuario(400).montoPedido(500).numeroPedido(600).build();
        List<PedidoEntity> entities = Arrays.asList(entity1,entity2,entity3);
        //when
        List<PedidoDTO> dtos = converter.convertToDTOList(entities);
        //then
        assertEquals(entities.get(0).getEstadoPedido(), dtos.get(0).getEstadoPedido());
        assertEquals(entities.get(0).getFechaPedido(), dtos.get(0).getFechaPedido());
        assertEquals(entities.get(0).getIdBodega(), dtos.get(0).getIdBodega());
        assertEquals(entities.get(0).getIdProveedor(), dtos.get(0).getIdProveedor());
        assertEquals(entities.get(0).getIdUsuario(), dtos.get(0).getIdUsuario());
        assertEquals(entities.get(0).getMontoPedido(), dtos.get(0).getMontoPedido());
        assertEquals(entities.get(0).getNumeroPedido(), dtos.get(0).getNumeroPedido());

        assertEquals(entities.get(1).getEstadoPedido(), dtos.get(1).getEstadoPedido());
        assertEquals(entities.get(1).getFechaPedido(), dtos.get(1).getFechaPedido());
        assertEquals(entities.get(1).getIdBodega(), dtos.get(1).getIdBodega());
        assertEquals(entities.get(1).getIdProveedor(), dtos.get(1).getIdProveedor());
        assertEquals(entities.get(1).getIdUsuario(), dtos.get(1).getIdUsuario());
        assertEquals(entities.get(1).getMontoPedido(), dtos.get(1).getMontoPedido());
        assertEquals(entities.get(1).getNumeroPedido(), dtos.get(1).getNumeroPedido());

        assertEquals(entities.get(2).getEstadoPedido(), dtos.get(2).getEstadoPedido());
        assertEquals(entities.get(2).getFechaPedido(), dtos.get(2).getFechaPedido());
        assertEquals(entities.get(2).getIdBodega(), dtos.get(2).getIdBodega());
        assertEquals(entities.get(2).getIdProveedor(), dtos.get(2).getIdProveedor());
        assertEquals(entities.get(2).getIdUsuario(), dtos.get(2).getIdUsuario());
        assertEquals(entities.get(2).getMontoPedido(), dtos.get(2).getMontoPedido());
        assertEquals(entities.get(2).getNumeroPedido(), dtos.get(2).getNumeroPedido());
    }

    @Test
    void convertToEntity_ShouldReturnDTOOk() {
        //given
        PedidoConverter converter = new PedidoConverter();
        PedidoDTO dto = PedidoDTO.builder().estadoPedido(1).
            fechaPedido(LocalDate.of(2000,12,6)).idBodega(2).idProveedor(3).
            idUsuario(4).montoPedido(5).numeroPedido(6).build();
        //when
        PedidoEntity entity = converter.convertToEntity(dto);
        //then
        assertEquals(dto.getEstadoPedido(), entity.getEstadoPedido());
        assertEquals(dto.getFechaPedido(), entity.getFechaPedido());
        assertEquals(dto.getIdBodega(), entity.getIdBodega());
        assertEquals(dto.getIdProveedor(), entity.getIdProveedor());
        assertEquals(dto.getIdUsuario(), entity.getIdUsuario());
        assertEquals(dto.getMontoPedido(), entity.getMontoPedido());
        assertEquals(dto.getNumeroPedido(), entity.getNumeroPedido());
    }
}
