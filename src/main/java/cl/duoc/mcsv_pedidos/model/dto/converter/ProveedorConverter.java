package cl.duoc.mcsv_pedidos.model.dto.converter;

import java.util.List;

import cl.duoc.mcsv_pedidos.model.entity.ProveedorEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;

@Component
public class ProveedorConverter implements Converter<ProveedorEntity, ProveedorDTO>{

    @Override
    public ProveedorDTO convert(ProveedorEntity source) {
        return ProveedorDTO.builder()
                .idProveedor(source.getIdProveedor())
                .nombreProv(source.getNombreProv())
                .telefonoProv(source.getTelefonoProv())
                .correoProv(source.getCorreoProv())
                .fechaCreacion(source.getFechaCreacion())
                .fechaActualizacion(source.getFechaActualizacion())
                .idUsuario(source.getIdUsuario())
                .build();
    }

    public ProveedorEntity convertToEntity(ProveedorDTO source) {
        return ProveedorEntity.builder()
                .idProveedor(source.getIdProveedor())
                .nombreProv(source.getNombreProv())
                .telefonoProv(source.getTelefonoProv())
                .correoProv(source.getCorreoProv())
                .fechaCreacion(source.getFechaCreacion())
                .fechaActualizacion(source.getFechaActualizacion())
                .idUsuario(source.getIdUsuario())
                .build();
    }
    
    public List<ProveedorDTO> convertToDTOList(List<ProveedorEntity> source) {
        return source.stream()
                .map(this::convert)
                .toList();
    }
}
