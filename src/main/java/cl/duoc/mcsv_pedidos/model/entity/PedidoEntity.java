package cl.duoc.mcsv_pedidos.model.entity;

import java.time.LocalDate;

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
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    private int numeroPedido;
    private LocalDate fechaPedido;
    private int montoPedido;
    private int estadoPedido;
    private int idProveedor;
    private int idUsuario;
    private int idBodega;
}
