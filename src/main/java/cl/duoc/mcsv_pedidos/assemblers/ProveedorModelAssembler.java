package cl.duoc.mcsv_pedidos.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import cl.duoc.mcsv_pedidos.controller.ProveedorController;
import cl.duoc.mcsv_pedidos.model.dto.ProveedorDTO;
@Component
public class ProveedorModelAssembler implements RepresentationModelAssembler<ProveedorDTO, EntityModel<ProveedorDTO>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<ProveedorDTO> toModel(ProveedorDTO proveedor) {
        return EntityModel.of(proveedor,
            // Link a sí mismo (GET /proveedor/{id})
            linkTo(methodOn(ProveedorController.class).getProveedorById(proveedor.getIdProveedor())).withSelfRel(),
            
            // Link a la lista de proveedores (GET /proveedor)
            linkTo(methodOn(ProveedorController.class).getAllProveedores()).withRel("proveedores"),
            
            // Link para eliminar el proveedor (DELETE /proveedor/{id})
            linkTo(methodOn(ProveedorController.class).deleteProveedor(proveedor.getIdProveedor())).withRel("eliminar"),
            
            // Link para actualizar el proveedor (PUT /proveedor/{id})
            linkTo(methodOn(ProveedorController.class).updateProveedor(proveedor.getIdProveedor(), proveedor)).withRel("actualizar"),
            
            // Link para crear un nuevo proveedor (POST /proveedor)
            linkTo(methodOn(ProveedorController.class).createProveedor(proveedor)).withRel("crear")
        );
    }

}
