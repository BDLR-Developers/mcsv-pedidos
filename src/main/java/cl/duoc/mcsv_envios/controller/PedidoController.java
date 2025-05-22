package cl.duoc.mcsv_envios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_envios.model.dto.PedidoDTO;
import cl.duoc.mcsv_envios.service.DetallePedidoService;
import cl.duoc.mcsv_envios.service.PedidoService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;
    @Autowired
    private final DetallePedidoService detallePedidoService;

    @GetMapping("/all")
    public List<PedidoDTO> getAllPedidos() {
        List<PedidoDTO> pedidos =  pedidoService.getAllPedidos();
        for (PedidoDTO pedido : pedidos) {
            System.out.println(pedido);
            pedido.setDetallePedidoEntitys(detallePedidoService.getDetallePedidoByNumeroPedido(pedido.getNumeroPedido()));
        }

        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos;
    }
    
}
