package cl.duoc.mcsv_pedidos.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Component;

import cl.duoc.mcsv_pedidos.controller.PedidoController;
import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
@Component
public class PedidoModelAssembler implements RepresentationModelAssembler<PedidoDTO, EntityModel<PedidoDTO>> {

    @Override
    @org.springframework.lang.NonNull
    public EntityModel<PedidoDTO> toModel(PedidoDTO pedido) {
        return EntityModel.of(pedido,
        //Link a sí mismo (GET  /pedido/{id})
        linkTo(methodOn(PedidoController.class).getPedido(pedido.getNumeroPedido())).withSelfRel(),
        
        //Link a la lista de pedidos (GET  /pedido)
        linkTo(methodOn(PedidoController.class).getAllPedidos()).withRel("pedidos"),

        //Link para eliminar el pedido (DELETE  /pedido/{id})
        linkTo(methodOn(PedidoController.class).delete(pedido.getNumeroPedido())).withRel("eliminar"),

        //Link para actualizar el pedido (PUT  /pedido/{id})
        linkTo(methodOn(PedidoController.class).actualizar(pedido.getNumeroPedido(), pedido)).withRel("actualizar"),
        
        //Link para crear un nuevo pedido (POST  /pedido)
        linkTo(methodOn(PedidoController.class).guardar(pedido)).withRel("crear")
        );
    }

}
