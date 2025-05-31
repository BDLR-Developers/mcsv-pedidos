package cl.duoc.mcsv_pedidos.model.dto.converter;

import java.util.List;

import cl.duoc.mcsv_pedidos.model.dto.DetallePedidoDTO;
import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DetallePedidoConverter implements Converter<DetallePedidoEntity, DetallePedidoDTO> {

    @Override
    public DetallePedidoDTO convert(DetallePedidoEntity source) {
        if(source == null) {
            return null;
        }
        return DetallePedidoDTO.builder()
                .numeroPedido(source.getNumeroPedido())
                .idProducto(source.getIdProducto())
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
