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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
import cl.duoc.mcsv_pedidos.service.ProveedorService;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/proveedor")
@RequiredArgsConstructor
public class ProveedorController {
    private final ProveedorService proveedorService;

    // Endpoint para ver estado de la API
    //URL: http://localhost:8083/api/v1/proveedor/status
    @GetMapping("/status")
    public String getStatus() {
        return "API is running";
    }

    // Endpoint para obtener todos los proveedores
    //URL: http://localhost:8083/api/v1/proveedor/all
    @GetMapping("/all")
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }
    
    //Endpoint para obtener un proveedor por ID
    //URL: http://localhost:8083/api/v1/proveedor/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getProveedorById(@PathVariable("id") int id) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.getProveedorById(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.orElseThrow());
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
    public ResponseEntity<?> createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorService.createProveedor(proveedorDTO));
    }


    //Endpoint para actualizar un proveedor
    //URL: http://localhost:8083/api/v1/proveedor/update/{id}
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProveedor(@PathVariable int id, @RequestBody ProveedorDTO proveedorDTO) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.updateProveedor(proveedorDTO, id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(proveedorOptional.orElseThrow());
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
    public ResponseEntity<?> deleteProveedor(@PathVariable int id) {
        Optional<ProveedorDTO> proveedorOptional = proveedorService.deleteProveedor(id);
        if (proveedorOptional.isPresent()) {
            return ResponseEntity.ok(proveedorOptional.orElseThrow());
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
    public List<ProveedorDTO> getProveedorByNombre(@RequestParam String nombre) {
        return proveedorService.getProveedorByNombre(nombre);
    }

}
