package cl.duoc.mcsv_pedidos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.service.PedidoService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;
    
    //Endpoint para obtener todos los pedidos
    //URL: http://localhost:8083/api/v1/pedido/all
    @GetMapping("/all")
    public List<PedidoDTO> getAllPedidos() {
        List<PedidoDTO> pedidos =  pedidoService.getAllPedidos();
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos;
    }
    
    //Endpoint para obtener un pedido por id
    //URL: http://localhost:8083/api/v1/pedido/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getPedido(@PathVariable("id") Integer id) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.getPedidoById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el pedido con id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    //Endpoint para guardar un pedido
    //URL: http://localhost:8083/api/v1/pedido
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody PedidoDTO pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.guardar(pedido));
    }

    //Endpoint para actualizar un pedido
    //URL: http://localhost:8083/api/v1/pedido/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody PedidoDTO pedido) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.update(id, pedido);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el id de pedido");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    //Endpoint para eliminar un pedido
    //URL: http://localhost:8083/api/v1/pedido/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.delete(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el id de pedido");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }
}
