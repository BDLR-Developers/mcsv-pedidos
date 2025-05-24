package cl.duoc.mcsv_pedidos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.model.entity.clavesCompuestas.DetallePedidoPrimaryKey;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedidoEntity, DetallePedidoPrimaryKey> {
    // Aquí puedes agregar métodos personalizados si es necesario
    public List<DetallePedidoEntity> findByNumeroPedido(int numeroPedido);
    public void deleteById(DetallePedidoPrimaryKey id);
}
