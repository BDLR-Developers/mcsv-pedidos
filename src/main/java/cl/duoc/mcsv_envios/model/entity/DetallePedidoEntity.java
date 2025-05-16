package cl.duoc.mcsv_envios.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
public class DetallePedidoEntity {
    @Id
    private int numeroPedido;
    private int cantidad;
    private int precio;
    private String idProd;
}
