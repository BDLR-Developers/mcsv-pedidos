package cl.duoc.mcsv_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_envios.model.entity.ProveedorEntity;
import cl.duoc.mcsv_envios.repository.ProveedorRepository;


@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;
    
    public List<ProveedorEntity> getAllProveedores() {
        return proveedorRepository.findAll();
    }
    public ProveedorEntity getProveedorById(int id) {
        return proveedorRepository.findByIdProveedor(id);
    }
}
