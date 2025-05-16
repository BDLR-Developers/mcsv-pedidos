package cl.duoc.mcsv_envios.model.dto;

import lombok.Data;

@Data
public class DetallePedidoDTO {
    private int numeroPedido;
    private int cantidad;
    private int precio;
    private String idProd;
}
