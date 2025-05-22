package cl.duoc.mcsv_envios.model.dto;

import java.time.LocalDate;
import java.util.List;

import cl.duoc.mcsv_envios.model.entity.DetallePedidoEntity;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PedidoDTO {
    private int numeroPedido;
    private LocalDate fechaPedido;
    private int montoPedido;
    private int estadoPedido;
    private int idProveedor;
    private int idUsuario;
    private int idBodega;
    private List<DetallePedidoEntity> detallePedidoEntitys;
}
