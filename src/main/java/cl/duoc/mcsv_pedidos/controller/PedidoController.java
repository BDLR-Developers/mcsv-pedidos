package cl.duoc.mcsv_pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {

    @Autowired
    private final PedidoService pedidoService;


    @GetMapping("/all")
    public List<PedidoDTO> getAllPedidos() {
        List<PedidoDTO> pedidos =  pedidoService.getAllPedidos();
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos;
    }
    
    
}
