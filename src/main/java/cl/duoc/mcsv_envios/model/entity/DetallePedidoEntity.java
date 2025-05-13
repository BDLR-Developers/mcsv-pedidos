package cl.duoc.mcsv_envios.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetallePedidoEntity {
    private int numeroPedido;
    private int cantidad;
    private int precio;
    private String idProd;
}
