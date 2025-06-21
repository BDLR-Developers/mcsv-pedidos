package cl.duoc.mcsv_pedidos.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.model.dto.converter.ProveedorConverter;
import cl.duoc.mcsv_pedidos.model.entity.ProveedorEntity;
import cl.duoc.mcsv_pedidos.repository.ProveedorRepository;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {
    @InjectMocks
    private ProveedorService proveedorService;

    @Mock
    private ProveedorRepository proveedorRepository;
    
    @Mock
    private ProveedorConverter proveedorConverter;
    
    @Test
    void testCreateProveedor() {
        // Given
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        when(proveedorConverter.convertToEntity(proveedorDTO)).thenReturn(proveedorEntity);
        when(proveedorRepository.save(proveedorEntity)).thenReturn(proveedorEntity);
        when(proveedorConverter.convert(proveedorEntity)).thenReturn(proveedorDTO);
        // When
        ProveedorDTO result = proveedorService.createProveedor(proveedorDTO);
        // Then
        assertEquals(proveedorDTO, result);
        verify(proveedorRepository).save(proveedorEntity);
        verify(proveedorConverter).convertToEntity(proveedorDTO);
        verify(proveedorConverter).convert(proveedorEntity);
    }

    @Test
    void testDeleteProveedor() {
        // Given
        Integer id = 1;
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        when(proveedorRepository.findById(id)).thenReturn(Optional.of(proveedorEntity));
        when(proveedorConverter.convert(proveedorEntity)).thenReturn(proveedorDTO);
        when(proveedorConverter.convertToEntity(proveedorDTO)).thenReturn(proveedorEntity);
        // When
        Optional<ProveedorDTO> result = proveedorService.deleteProveedor(id);
        // Then
        assertEquals(proveedorDTO, result.orElse(null));
        verify(proveedorRepository).findById(id);
        verify(proveedorConverter).convert(proveedorEntity);
        verify(proveedorConverter).convertToEntity(proveedorDTO);
        verify(proveedorRepository).delete(proveedorEntity);
    }

    @Test
    void testGetAllProveedores() {
        // Given
        ProveedorDTO proveedorDTO1 = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorDTO proveedorDTO2 = ProveedorDTO.builder()
                .idProveedor(2)
                .nombreProv("Proveedor Test 2")
                .telefonoProv("987654321")
                .correoProv("proveedor2@test.com")
                .fechaCreacion(LocalDate.of(2020, 5, 3))
                .fechaActualizacion(LocalDate.of(2021, 7, 6))
                .idUsuario(11)
                .build();
        ProveedorEntity proveedorEntity1 = ProveedorEntity.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorEntity proveedorEntity2 = ProveedorEntity.builder()
                .idProveedor(2)
                .nombreProv("Proveedor Test 2")
                .telefonoProv("987654321")
                .correoProv("proveedor2@test.com")
                .fechaCreacion(LocalDate.of(2020, 5, 3))
                .fechaActualizacion(LocalDate.of(2021, 7, 6))
                .idUsuario(11)
                .build();
        List<ProveedorDTO> proveedores = List.of(proveedorDTO1, proveedorDTO2);
        List<ProveedorEntity> proveedorEntities = List.of(proveedorEntity1, proveedorEntity2);
        when(proveedorRepository.findAll()).thenReturn(proveedorEntities);
        when(proveedorConverter.convertToDTOList(proveedorEntities)).thenReturn(proveedores);
        // When
        List<ProveedorDTO> result = proveedorService.getAllProveedores();
        // Then
        assertEquals(proveedores, result);
        verify(proveedorRepository).findAll();
        verify(proveedorConverter).convertToDTOList(proveedorEntities);

    }

    @Test
    void testGetProveedorById() {
        // Given
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorEntity proveedorEntity1 = ProveedorEntity.builder()
                .idProveedor(1)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedorEntity1));
        when(proveedorConverter.convert(proveedorEntity1)).thenReturn(proveedorDTO);
        // When
        Optional<ProveedorDTO> result = proveedorService.getProveedorById(1);
        // Then
        assertEquals(proveedorDTO, result.orElse(null));
        verify(proveedorRepository).findById(1);
        verify(proveedorConverter).convert(proveedorEntity1);

    }

    @Test
    void testGetProveedorByNombre() {
        // Given
        String nombreProv = "Proveedor Test";
        ProveedorDTO proveedorDTO1 = ProveedorDTO.builder()
                .idProveedor(1)
                .nombreProv(nombreProv)
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        
        ProveedorEntity proveedorEntity1 = ProveedorEntity.builder()
                .idProveedor(1)
                .nombreProv(nombreProv)
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        
        when(proveedorRepository.findByNombreProv(nombreProv)).thenReturn(List.of(proveedorEntity1));
        when(proveedorConverter.convertToDTOList(List.of(proveedorEntity1))).thenReturn(List.of(proveedorDTO1));
        // When
        List<ProveedorDTO> result = proveedorService.getProveedorByNombre(nombreProv);
        // Then
        assertEquals(proveedorDTO1, result.get(0));
        verify(proveedorRepository).findByNombreProv(nombreProv);
        verify(proveedorConverter).convertToDTOList(List.of(proveedorEntity1));

    }

    @Test
    void testUpdateProveedor() {
        // Given
        Integer id = 1;
        ProveedorDTO proveedorDTO = ProveedorDTO.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        ProveedorEntity proveedorEntity = ProveedorEntity.builder()
                .idProveedor(id)
                .nombreProv("Proveedor Test")
                .telefonoProv("123456789")
                .correoProv("proveedor@test.com")
                .fechaCreacion(LocalDate.of(2020, 3, 2))
                .fechaActualizacion(LocalDate.of(2021, 4, 5))
                .idUsuario(10)
                .build();
        when(proveedorConverter.convertToEntity(proveedorDTO)).thenReturn(proveedorEntity);
        when(proveedorRepository.existsById(id)).thenReturn(true);
        when(proveedorRepository.save(proveedorEntity)).thenReturn(proveedorEntity);
        when(proveedorConverter.convert(proveedorEntity)).thenReturn(proveedorDTO);
        // When
        Optional<ProveedorDTO> result = proveedorService.updateProveedor(proveedorDTO, id);
        // Then
        assertEquals(proveedorDTO, result.orElse(null));
        verify(proveedorConverter).convertToEntity(proveedorDTO);
        verify(proveedorRepository).existsById(id);
        verify(proveedorRepository).save(proveedorEntity);
        verify(proveedorConverter).convert(proveedorEntity);
    }
}
