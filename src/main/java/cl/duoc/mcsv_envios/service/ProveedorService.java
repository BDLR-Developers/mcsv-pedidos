package cl.duoc.mcsv_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_envios.model.dto.ProveedorDTO;
import cl.duoc.mcsv_envios.model.dto.converter.ProveedorConverter;
import cl.duoc.mcsv_envios.model.entity.ProveedorEntity;
import cl.duoc.mcsv_envios.repository.ProveedorRepository;


@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ProveedorConverter proveedorConverter;
    
    // Método para obtener todos los proveedores
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorConverter.convertToDTOList(proveedorRepository.findAll());
    }

    // Método para obtener un proveedor por ID
    public ProveedorDTO getProveedorById(int id) {
        ProveedorEntity entity = proveedorRepository.findByIdProveedor(id);
        return proveedorConverter.convert(entity);
    }
    
    // Método para crear un nuevo proveedor
    public ProveedorDTO createProveedor(ProveedorDTO proveedorDTO) {
        ProveedorEntity entity = proveedorConverter.convertToEntity(proveedorDTO);
        entity = proveedorRepository.save(entity);
        return proveedorConverter.convert(entity);
    }

    // Método para actualizar un proveedor existente
    public ProveedorDTO updateProveedor(ProveedorDTO proveedorDTO, int id) {
        ProveedorEntity entity = proveedorConverter.convertToEntity(proveedorDTO);
        if (proveedorRepository.existsById(id)) {
            entity.setIdProveedor(id);
            proveedorRepository.save(entity);
        }else {
            return null;
        }
        return proveedorConverter.convert(entity);
    }

    // Método para eliminar un proveedor por ID
    public void deleteProveedor(int id) {
        ProveedorEntity entity = proveedorRepository.findByIdProveedor(id);
        if (entity != null) {
            proveedorRepository.delete(entity);
        }
    }
}
