package cl.duoc.mcsv_envios.model.dto.converter;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.duoc.mcsv_envios.model.dto.DetallePedidoDTO;
import cl.duoc.mcsv_envios.model.entity.DetallePedidoEntity;

@Component
public class DetallePedidoConverter implements Converter<DetallePedidoEntity, DetallePedidoDTO> {

    @Override
    public DetallePedidoDTO convert(DetallePedidoEntity source) {
        return DetallePedidoDTO.builder()
                .numeroPedido(source.getNumeroPedido())
                .idProd(source.getIdProd())
                .cantidad(source.getCantidad())
                .precio(source.getPrecio())
                .build();
    }

    public List<DetallePedidoDTO> convertToDTOList(List<DetallePedidoEntity> source) {
        return source.stream()
                .map(this::convert)
                .toList();
    }
}
