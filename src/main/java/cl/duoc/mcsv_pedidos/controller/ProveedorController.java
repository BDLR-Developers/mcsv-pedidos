package cl.duoc.mcsv_pedidos.controller;

import java.util.List;

import cl.duoc.mcsv_pedidos.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
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
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/v1/proveedor")
@RequiredArgsConstructor
public class ProveedorController {
    @Autowired
    private final ProveedorService proveedorService;

    // Endpoint para ver estado de la API
    @GetMapping("/status")
    public String getStatus() {
        return "API is running";
    }

    // Endpoint para obtener todos los proveedores
    @GetMapping("/all")
    public List<ProveedorDTO> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }
    
    //Endpoint para obtener un proveedor por ID
    @GetMapping("/{id}")
    public ProveedorDTO getProveedorById(@PathVariable int id) {
        return proveedorService.getProveedorById(id);
    }

    //Endpoint para crear un proveedor
    @PostMapping
    public ProveedorDTO createProveedor(@RequestBody ProveedorDTO proveedorDTO) {
        return proveedorService.createProveedor(proveedorDTO);
    }
    
    //Endpoint para actualizar un proveedor
    @PutMapping("/update/{id}")
    public ProveedorDTO updateProveedor(@PathVariable int id, @RequestBody ProveedorDTO proveedorDTO) {
        return proveedorService.updateProveedor(proveedorDTO,id);
    }
    //Endpoint para eliminar un proveedor por ID
    @DeleteMapping("/{id}")
    public void deleteProveedor(@PathVariable int id) {
        proveedorService.deleteProveedor(id);
    }

    @GetMapping("/nombre")
    public List<ProveedorDTO> getProveedorByNombre(@RequestParam String nombre) {
        return proveedorService.getProveedorByNombre(nombre);
    }

}
