package cl.duoc.mcsv_envios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.mcsv_envios.model.entity.ProveedorEntity;
import cl.duoc.mcsv_envios.service.ProveedorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/proveedor")
@RequiredArgsConstructor
public class ProveedorController {
    @Autowired
    private final ProveedorService proveedorService;

    // Endpoint para obtener todos los proveedores
    @GetMapping("/all")
    public List<ProveedorEntity> getAllProveedores() {
        return proveedorService.getAllProveedores();
    }
    
    @GetMapping("/{id}")
    public ProveedorEntity getProveedorById(@PathVariable int id) {
        System.out.println("ID: " + id);
        return proveedorService.getProveedorById(id);
    }
}
