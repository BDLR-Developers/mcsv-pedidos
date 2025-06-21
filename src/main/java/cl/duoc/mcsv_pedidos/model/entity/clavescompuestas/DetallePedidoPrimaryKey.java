package cl.duoc.mcsv_pedidos.model.entity.clavescompuestas;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetallePedidoPrimaryKey implements java.io.Serializable {
    private int numeroPedido;
    private int idProducto;
    
}
