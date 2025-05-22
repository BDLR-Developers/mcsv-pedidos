package cl.duoc.mcsv_envios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mcsv_envios.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_envios.model.entity.clavesCompuestas.DetallePedidoPrimaryKey;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, DetallePedidoPrimaryKey> {
    // Aquí puedes agregar métodos personalizados si es necesario

}
