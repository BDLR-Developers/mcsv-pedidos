package cl.duoc.mcsv_pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.model.dto.converter.ProveedorConverter;
import cl.duoc.mcsv_pedidos.model.entity.ProveedorEntity;
import cl.duoc.mcsv_pedidos.repository.ProveedorRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;
    
    private final ProveedorConverter proveedorConverter;
    
    // Método para obtener todos los proveedores
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorConverter.convertToDTOList(proveedorRepository.findAll());
    }

    // Método para obtener un proveedor por ID
    public Optional<ProveedorDTO> getProveedorById(int id) {
        return proveedorRepository.findById(id)
                .map(proveedor -> proveedorConverter.convert(proveedor));
    }
    
    // Método para crear un nuevo proveedor
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        ProveedorEntity entity = proveedorConverter.convertToEntity(proveedorDTO);
        entity = proveedorRepository.save(entity);
        return proveedorConverter.convert(entity);
    }

    // Método para actualizar un proveedor existente
    public Optional<ProveedorDTO> updateProveedor(ProveedorDTO proveedorDTO, int id) {
        ProveedorEntity entity = proveedorConverter.convertToEntity(proveedorDTO);
        if (proveedorRepository.existsById(id)) {
            entity.setIdProveedor(id);
            proveedorRepository.save(entity);
        }else {
            return Optional.empty();
        }
        return Optional.of(proveedorConverter.convert(entity));
    }

    // Método para eliminar un proveedor por ID
    public Optional<ProveedorDTO> deleteProveedor(int id) {
        Optional<ProveedorDTO> proveedorOptional = getProveedorById(id);
        if (proveedorOptional.isPresent()) {
            ProveedorDTO proveedorDTO = proveedorOptional.get();
            ProveedorEntity entity = proveedorConverter.convertToEntity(proveedorDTO);
            proveedorRepository.delete(entity);
            return Optional.of(proveedorDTO);
        }
        return proveedorOptional;
    }
    
    // Método para buscar un proveedor por nombre
    public List<ProveedorDTO> getProveedorByNombre(String nombreProv) {
        List<ProveedorEntity> entities = proveedorRepository.findByNombreProv(nombreProv);
        return proveedorConverter.convertToDTOList(entities);
    }
}
