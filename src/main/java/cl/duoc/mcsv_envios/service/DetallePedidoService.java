package cl.duoc.mcsv_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_envios.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_envios.repository.DetallePedidoRepository;

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
