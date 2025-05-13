package cl.duoc.mcsv_envios.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProveedorEntity {
    private String idProveedor;
    private String nombreProv;
    private int telefonoProv;
    private String correoProv;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private String idUsuario;
}
