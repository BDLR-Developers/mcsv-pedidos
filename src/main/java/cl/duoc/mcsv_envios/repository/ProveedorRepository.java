package cl.duoc.mcsv_envios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mcsv_envios.model.entity.ProveedorEntity;

@Repository
public interface ProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

}
