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
@Table(name = "proveedor")
public class ProveedorEntity {
    @Id
    private int idProveedor;
    private String nombreProv;
    private String telefonoProv;
    private String correoProv;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private int idUsuario;
}
