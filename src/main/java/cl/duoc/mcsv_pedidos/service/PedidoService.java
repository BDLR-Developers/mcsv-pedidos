package cl.duoc.mcsv_pedidos.service;

import java.util.List;

import cl.duoc.mcsv_pedidos.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.model.dto.converter.PedidoConverter;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoConverter pedidoConverter;
    @Autowired
    private DetallePedidoService detallePedidoService;
    
    public List<PedidoDTO> getAllPedidos() {
        List<PedidoDTO> pedidos =  pedidoConverter.convertToDTOList(pedidoRepository.findAll());

        for (PedidoDTO pedido : pedidos) {
            pedido.setDetallePedidoEntitys(detallePedidoService.getDetallePedidoByNumeroPedido(pedido.getNumeroPedido()));
        }
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos;
    }
    
}
