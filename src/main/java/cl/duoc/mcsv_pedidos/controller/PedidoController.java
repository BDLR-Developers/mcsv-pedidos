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

import cl.duoc.mcsv_pedidos.assemblers.PedidoModelAssembler;
import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/pedido")
@RequiredArgsConstructor
@Tag(name = "Pedidos", description = "Controlador para gestionar pedidos")  
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoModelAssembler pedidoModelAssembler; // <-- agrega esto


    //Endpoint para obtener todos los pedidos
    //URL: http://localhost:8083/api/v1/pedido/all
    @GetMapping("/all")
    @Operation(
        summary = "Obtener todos los pedidos", 
        description = "Devuelve una lista de todos los pedidos registrados", 
        responses={@ApiResponse (responseCode = "200", 
            description = "Lista de pedidos obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class)))}
        )
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
    @Operation(
        summary = "Obtener un pedido por ID", 
        description = "Devuelve un pedido específico según su ID", 
        responses={@ApiResponse (responseCode = "200", 
            description = "Pedido obtenido exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse (responseCode = "404", 
            description = "Pedido no encontrado",
            content = @Content(
                mediaType = "application/json"))}
        )
    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID del pedido a obtener")
    public ResponseEntity<?> getPedido(@PathVariable("id") Integer id) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.getPedidoById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoModelAssembler.toModel(pedidoOptional.orElseThrow()));
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
    @Operation(
        summary = "Guardar un nuevo pedido", 
        description = "Crea un nuevo pedido y lo guarda en la base de datos", 
        responses={@ApiResponse (responseCode = "201", 
            description = "Pedido creado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class)))}
        )
    public ResponseEntity<?> guardar(@RequestBody PedidoDTO pedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoModelAssembler.toModel(pedidoService.guardar(pedido)));
    }

    //Endpoint para actualizar un pedido
    //URL: http://localhost:8083/api/v1/pedido/{id}
    @PutMapping("/{id}")
    @Operation(
        summary = "Actualizar un pedido", 
        description = "Actualiza un pedido existente según su ID", 
        responses={@ApiResponse (responseCode = "200", 
            description = "Pedido actualizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse (responseCode = "404", 
            description = "Pedido no encontrado",
            content = @Content(
                mediaType = "application/json"))}
        )
    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID del pedido a actualizar")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody PedidoDTO pedido) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.update(id, pedido);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoModelAssembler.toModel(pedidoOptional.orElseThrow()));
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
    @Operation(
        summary = "Eliminar un pedido", 
        description = "Elimina un pedido existente según su ID", 
        responses={@ApiResponse (responseCode = "200", 
            description = "Pedido eliminado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse (responseCode = "404", 
            description = "Pedido no encontrado",
            content = @Content(
                mediaType = "application/json"))},
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "ID del pedido a eliminar", 
            required = true)}
        )
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        Optional<PedidoDTO> pedidoOptional = pedidoService.delete(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoModelAssembler.toModel(pedidoOptional.orElseThrow()));
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el id de pedido");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }
}
