package cl.duoc.mcsv_pedidos.model.entity;

import cl.duoc.mcsv_pedidos.model.entity.clavescompuestas.DetallePedidoPrimaryKey;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "detalle_pedido")
@IdClass(DetallePedidoPrimaryKey.class)
public class DetallePedidoEntity {
    @Id
    private int numeroPedido;
    @Id
    private int idProducto;

    private int cantidad;
    private int precio;
}
