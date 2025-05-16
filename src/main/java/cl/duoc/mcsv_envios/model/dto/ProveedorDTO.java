package cl.duoc.mcsv_envios.model.dto;

import lombok.Data;

@Data
public class ProveedorDTO {
    private int idProveedor;
    private String nombreProv;
    private String telefonoProv;
    private String correoProv;  
    private String fechaCreacion;
    private String fechaActualizacion;
    private int idUsuario;
}
