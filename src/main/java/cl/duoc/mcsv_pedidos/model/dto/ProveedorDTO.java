package cl.duoc.mcsv_pedidos.model.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProveedorDTO {
    private int idProveedor;
    private String nombreProv;
    private String telefonoProv;
    private String correoProv;  
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;
    private int idUsuario;
}
