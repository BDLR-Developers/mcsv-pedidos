package cl.duoc.mcsv_envios.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PedidoEntity {
    private int numeroPedido;
    private LocalDate fechaPedido;
    private int montoPedido;
    private String estadoPedido;
    private String idProveedor;
    private String idUsuario;
    private String idBodega;
    private String idSucursal;
}
