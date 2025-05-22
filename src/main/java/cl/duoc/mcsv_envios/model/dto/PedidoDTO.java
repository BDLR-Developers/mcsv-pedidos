package cl.duoc.mcsv_envios.model.dto;

import java.time.LocalDate;

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
}
