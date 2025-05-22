package cl.duoc.mcsv_envios.model.dto.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.duoc.mcsv_envios.model.dto.PedidoDTO;
import cl.duoc.mcsv_envios.model.entity.PedidoEntity;

@Component
public class PedidoConverter implements Converter<PedidoEntity, PedidoDTO> {

    @Override
    public PedidoDTO convert(PedidoEntity source) {
        return PedidoDTO.builder()
                .numeroPedido(source.getNumeroPedido())
                .fechaPedido(source.getFechaPedido())
                .montoPedido(source.getMontoPedido())
                .estadoPedido(source.getEstadoPedido())
                .idProveedor(source.getIdProveedor())
                .idUsuario(source.getIdUsuario())
                .idBodega(source.getIdBodega())
                .build();
    }

    public PedidoEntity convertToEntity(PedidoDTO source) {
        return PedidoEntity.builder()
                .numeroPedido(source.getNumeroPedido())
                .fechaPedido(source.getFechaPedido())
                .montoPedido(source.getMontoPedido())
                .estadoPedido(source.getEstadoPedido())
                .idProveedor(source.getIdProveedor())
                .idUsuario(source.getIdUsuario())
                .idBodega(source.getIdBodega())
                .build();
    }
    
    public List<PedidoDTO> convertToDTOList(List<PedidoEntity> source) {
        return source.stream()
                .map(this::convert)
                .toList();
    }

}
