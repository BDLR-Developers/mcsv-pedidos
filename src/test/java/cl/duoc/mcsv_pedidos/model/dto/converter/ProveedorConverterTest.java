package cl.duoc.mcsv_pedidos.model.dto.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.model.entity.ProveedorEntity;

public class ProveedorConverterTest {
    @Test
    void convert_ShouldReturnEntityOk() {
        //given
        ProveedorConverter converter = new ProveedorConverter();
        ProveedorEntity entity = ProveedorEntity.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("prov@gmail.com")
                .fechaCreacion(LocalDate.of(2023, 1, 1))
                .fechaActualizacion(LocalDate.of(2023, 1, 2))
                .idUsuario(1)
                .build();

        //when
        ProveedorDTO dto = converter.convert(entity);

        //then
        assert dto != null;
        assertEquals(entity.getCorreoProv(), dto.getCorreoProv());
        assertEquals(entity.getFechaCreacion(), dto.getFechaCreacion());
        assertEquals(entity.getFechaActualizacion(), dto.getFechaActualizacion());
        assertEquals(entity.getIdProveedor(), dto.getIdProveedor());
        assertEquals(entity.getIdUsuario(), dto.getIdUsuario());
        assertEquals(entity.getNombreProv(), dto.getNombreProv());
        assertEquals(entity.getTelefonoProv(), dto.getTelefonoProv());
    }

    @Test
    void testConvertToDTOList() {
        // given
        ProveedorConverter converter = new ProveedorConverter();
        ProveedorEntity entity1 = ProveedorEntity.builder()
            .idProveedor(1)
            .nombreProv("Proveedor Test1")
            .telefonoProv("123456789")
            .correoProv("prov1@gmail.com")
            .fechaCreacion(LocalDate.of(2021, 1, 1))
            .fechaActualizacion(LocalDate.of(2023, 2, 2))
            .idUsuario(10)
            .build();
        ProveedorEntity entity2 = ProveedorEntity.builder()
            .idProveedor(2)
            .nombreProv("Proveedor Test2")
            .telefonoProv("987654321")
            .correoProv("prov2@gmail.com")
            .fechaCreacion(LocalDate.of(2022, 2, 20))
            .fechaActualizacion(LocalDate.of(2024, 3, 21))
            .idUsuario(20)
            .build();
        ProveedorEntity entity3 = ProveedorEntity.builder()
            .idProveedor(3)
            .nombreProv("Proveedor Test")
            .telefonoProv("543212345")
            .correoProv("prov3@gmail.com")
            .fechaCreacion(LocalDate.of(2020, 3, 30))
            .fechaActualizacion(LocalDate.of(2025, 4, 13))
            .idUsuario(30)
            .build();
        List<ProveedorEntity> entities = List.of(entity1, entity2, entity3);
        // when
        List<ProveedorDTO> dtos = converter.convertToDTOList(entities);
        // then
        assertEquals(entities.get(0).getCorreoProv(), dtos.get(0).getCorreoProv());
        assertEquals(entities.get(0).getFechaCreacion(), dtos.get(0).getFechaCreacion());
        assertEquals(entities.get(0).getFechaActualizacion(), dtos.get(0).getFechaActualizacion());
        assertEquals(entities.get(0).getIdProveedor(), dtos.get(0).getIdProveedor());
        assertEquals(entities.get(0).getIdUsuario(), dtos.get(0).getIdUsuario());
        assertEquals(entities.get(0).getNombreProv(), dtos.get(0).getNombreProv());
        assertEquals(entities.get(0).getTelefonoProv(), dtos.get(0).getTelefonoProv());

        assertEquals(entities.get(1).getCorreoProv(), dtos.get(1).getCorreoProv());
        assertEquals(entities.get(1).getFechaCreacion(), dtos.get(1).getFechaCreacion());
        assertEquals(entities.get(1).getFechaActualizacion(), dtos.get(1).getFechaActualizacion()); 
        assertEquals(entities.get(1).getIdProveedor(), dtos.get(1).getIdProveedor());
        assertEquals(entities.get(1).getIdUsuario(), dtos.get(1).getIdUsuario());
        assertEquals(entities.get(1).getNombreProv(), dtos.get(1).getNombreProv());
        assertEquals(entities.get(1).getTelefonoProv(), dtos.get(1).getTelefonoProv());

        assertEquals(entities.get(2).getCorreoProv(), dtos.get(2).getCorreoProv());
        assertEquals(entities.get(2).getFechaCreacion(), dtos.get(2).getFechaCreacion());
        assertEquals(entities.get(2).getFechaActualizacion(), dtos.get(2).getFechaActualizacion());
        assertEquals(entities.get(2).getIdProveedor(), dtos.get(2).getIdProveedor());
        assertEquals(entities.get(2).getIdUsuario(), dtos.get(2).getIdUsuario());
        assertEquals(entities.get(2).getNombreProv(), dtos.get(2).getNombreProv());
        assertEquals(entities.get(2).getTelefonoProv(), dtos.get(2).getTelefonoProv());

    }

    @Test
    void testConvertToEntity() {
        // given
        ProveedorConverter converter = new ProveedorConverter();
        ProveedorDTO dto = ProveedorDTO.builder()
                .correoProv("correoProv@gmail.com")
                .fechaCreacion(LocalDate.of(2023, 1, 13))
                .fechaActualizacion(LocalDate.of(2024, 2, 5))
                .idProveedor(1)
                .idUsuario(2)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .build();
        // when
        ProveedorEntity entity = converter.convertToEntity(dto);
        // then
        assert entity != null;
        assertEquals(dto.getCorreoProv(), entity.getCorreoProv());
        assertEquals(dto.getFechaCreacion(), entity.getFechaCreacion());
        assertEquals(dto.getFechaActualizacion(), entity.getFechaActualizacion());
        assertEquals(dto.getIdProveedor(), entity.getIdProveedor());
        assertEquals(dto.getIdUsuario(), entity.getIdUsuario());
        assertEquals(dto.getNombreProv(), entity.getNombreProv());
        assertEquals(dto.getTelefonoProv(), entity.getTelefonoProv());
        
    }
}
