package cl.duoc.mcsv_pedidos.service;

import java.util.List;

import cl.duoc.mcsv_pedidos.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;

@Service
public class DetallePedidoService {
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    public List<DetallePedidoEntity> getAllDetallePedidos() {
        return detallePedidoRepository.findAll();
    }
    public  List<DetallePedidoEntity> getDetallePedidoByNumeroPedido(int id) {
        return detallePedidoRepository.findByNumeroPedido(id);
    }
}
