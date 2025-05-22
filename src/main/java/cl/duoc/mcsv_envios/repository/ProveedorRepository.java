package cl.duoc.mcsv_envios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mcsv_envios.model.entity.ProveedorEntity;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {
    // Método para obtener todos los proveedores activos
    ProveedorEntity findByIdProveedor(int id);
    // Método para buscar un proveedor por nombre
    List<ProveedorEntity> findByNombreProv(String nombreProv);
    
}
