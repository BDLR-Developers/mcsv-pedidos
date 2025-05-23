package cl.duoc.mcsv_pedidos.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DetallePedidoDTO {
    private int numeroPedido;
    private int cantidad;
    private int precio;
    private int idProducto;
}
