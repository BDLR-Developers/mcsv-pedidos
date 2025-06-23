package cl.duoc.mcsv_pedidos.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_pedidos.assemblers.ProveedorModelAssembler;
import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.service.ProveedorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/proveedor")
@RequiredArgsConstructor
@Tag(name = "Proveedores", description = "Controlador para gestionar proveedores")
public class ProveedorController {
    private final ProveedorService proveedorService;
    private final ProveedorModelAssembler proveedorModelAssembler;
    // Endpoint para ver estado de la API
    //URL: http://localhost:8083/api/v1/proveedor/status
    @GetMapping("/status")
    @Operation(
        summary = "Ver estado de la API", 
        description = "Devuelve el estado actual de la API", 
        responses = {
            @ApiResponse(responseCode = "200", 
                description = "Estado de la API obtenido exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation= String.class)))
        }
    )
    public String getStatus() {
        return "API is running";
    }

    // Endpoint para obtener todos los proveedores
    //URL: http://localhost:8083/api/v1/proveedor/all
    @GetMapping("/all")
    @Operation(
        summary = "Obtener todos los proveedores", 
        description = "Devuelve una lista de todos los proveedores registrados", 
        responses = {
            @ApiResponse(responseCode = "200", 
                description = "Lista de proveedores obtenida exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)))
        }
    )
    public ResponseEntity<CollectionModel<EntityModel<ProveedorDTO>>> getAllProveedores() {
        List<ProveedorDTO> proveedores = proveedorService.getAllProveedores();
        if (proveedores.isEmpty()) {
            return ResponseEntity.ok(CollectionModel.empty());
        }
        List<EntityModel<ProveedorDTO>> proveedorModels = proveedores.stream()
            .map(proveedorModelAssembler::toModel)
            .toList();
        return ResponseEntity.ok(CollectionModel.of(proveedorModels));
    }
    
    //Endpoint para obtener un proveedor por ID
    //URL: http://localhost:8083/api/v1/proveedor/{id}
    @GetMapping("/{id}")
    @Operation(
        summary = "Obtener un proveedor por ID", 
        description = "Devuelve un proveedor específico por su ID", 
        responses = {
            @ApiResponse(responseCode = "200", 
                description = "Proveedor obtenido exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class))),
            @ApiResponse(responseCode = "404", 
                description = "Proveedor no encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
        }
    )
    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID del proveedor a obtener")
    public ResponseEntity<?> getProveedorById(@PathVariable("id") int id) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.getProveedorById(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorModelAssembler.toModel(proveedorOptional.orElseThrow()));
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el proveedor");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    //Endpoint para crear un proveedor
    //URL: http://localhost:8083/api/v1/proveedor
    @PostMapping
    @Operation(
        summary = "Crear un nuevo proveedor", 
        description = "Registra un nuevo proveedor en el sistema", 
        responses = {
            @ApiResponse(responseCode = "201", 
                description = "Proveedor creado exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)))
        }
    )
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorModelAssembler.toModel(proveedorService.createProveedor(proveedorDTO)));
    }


    //Endpoint para actualizar un proveedor
    //URL: http://localhost:8083/api/v1/proveedor/update/{id}
    @PutMapping("/update/{id}")
    @Operation(
        summary = "Actualizar un proveedor", 
        description = "Actualiza la información de un proveedor existente", 
        responses = {
            @ApiResponse(responseCode = "201", 
                description = "Proveedor actualizado exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class))),
            @ApiResponse(responseCode = "404", 
                description = "Proveedor no encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
        }
    )
    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID del proveedor a actualizar")
    public ResponseEntity<?> updateProveedor(@PathVariable int id, @RequestBody ProveedorDTO proveedorDTO) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.updateProveedor(proveedorDTO, id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedorModelAssembler.toModel(proveedorOptional.orElseThrow()));
        }
            Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el proveedor con id: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    //Endpoint para eliminar un proveedor por ID
    //URL: http://localhost:8083/api/v1/proveedor/{id}
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Eliminar un proveedor", 
        description = "Elimina un proveedor del sistema por su ID", 
        responses = {
            @ApiResponse(responseCode = "200", 
                description = "Proveedor eliminado exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class))),
            @ApiResponse(responseCode = "404", 
                description = "Proveedor no encontrado",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Map.class)))
        }
    )
    @Parameter(in = ParameterIn.PATH, name = "id", description = "ID del proveedor a eliminar")
    public ResponseEntity<?> deleteProveedor(@PathVariable int id) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.deleteProveedor(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorModelAssembler.toModel(proveedorOptional.orElseThrow()));
        }
        Map<String, Object> errorBody = new HashMap<>();
            errorBody.put("error", "Solicitud inválida");
            errorBody.put("codigo", 404);
            errorBody.put("detalle", "No existe el id de proveedor con id: " + id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorBody);
    }

    //Endpoint para obtener un proveedor por nombre
    //URL: http://localhost:8083/api/v1/proveedor/nombre?nombre={nombre}
    @GetMapping("/nombre")
    @Operation(
        summary = "Obtener proveedores por nombre", 
        description = "Devuelve una lista de proveedores que coinciden con el nombre proporcionado", 
        responses = {
            @ApiResponse(responseCode = "200", 
                description = "Lista de proveedores obtenida exitosamente",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ProveedorDTO.class)))
        }
    )
    @Parameter(in = ParameterIn.QUERY, name = "nombre", description = "Nombre del proveedor a buscar", required = true)
    public List<ProveedorDTO> getProveedorByNombre(@RequestParam String nombre) {
        return proveedorService.getProveedorByNombre(nombre);
    }

}
