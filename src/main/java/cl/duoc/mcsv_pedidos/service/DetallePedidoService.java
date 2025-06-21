package cl.duoc.mcsv_pedidos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.model.entity.clavescompuestas.DetallePedidoPrimaryKey;
import cl.duoc.mcsv_pedidos.repository.DetallePedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetallePedidoService {
    
    private final DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedidoEntity> getAllDetallePedidos() {
        return detallePedidoRepository.findAll();
    }
    public  List<DetallePedidoEntity> getDetallePedidoByNumeroPedido(int id) {
        return detallePedidoRepository.findByNumeroPedido(id);
    }
    public DetallePedidoEntity guardar(DetallePedidoEntity detallePedido) {
        return detallePedidoRepository.save(detallePedido);
    }
    public void eliminar(DetallePedidoPrimaryKey id) {
        detallePedidoRepository.deleteById(id);
    }

}
