package cl.duoc.mcsv_pedidos.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.duoc.mcsv_pedidos.model.dto.PedidoDTO;
import cl.duoc.mcsv_pedidos.model.dto.converter.PedidoConverter;
import cl.duoc.mcsv_pedidos.model.entity.DetallePedidoEntity;
import cl.duoc.mcsv_pedidos.model.entity.clavesCompuestas.DetallePedidoPrimaryKey;
import cl.duoc.mcsv_pedidos.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PedidoService {

    
    private final PedidoRepository pedidoRepository;
    
    private final PedidoConverter pedidoConverter;
    
    private final DetallePedidoService detallePedidoService;
    
    public List<PedidoDTO> getAllPedidos() {
        List<PedidoDTO> pedidos =  pedidoConverter.convertToDTOList(pedidoRepository.findAll());

        for (PedidoDTO pedido : pedidos) {
            pedido.setDetallePedidoEntitys(detallePedidoService.getDetallePedidoByNumeroPedido(pedido.getNumeroPedido()));
        }
        if (pedidos.isEmpty()) {
            return null;
        }
        return pedidos;
    }
    
    public Optional<PedidoDTO> getPedidoById(Integer id) {
        return pedidoRepository.findById(id)
                .map(pedido -> {
                    PedidoDTO dto = pedidoConverter.convert(pedido);
                    dto.setDetallePedidoEntitys(detallePedidoService.getDetallePedidoByNumeroPedido(dto.getNumeroPedido()));
                    return dto;
                });    
    }

    public PedidoDTO guardar(PedidoDTO pedido) {
        for(DetallePedidoEntity detalle : pedido.getDetallePedidoEntitys()) {
            detallePedidoService.guardar(detalle);
        }
        return pedidoConverter.convert(pedidoRepository.save(pedidoConverter.convertToEntity(pedido)));
    }

    public Optional<PedidoDTO> update(Integer id, PedidoDTO pedido) {
        Optional<PedidoDTO> pedidoOptional = getPedidoById(id);
        if (pedidoOptional.isPresent()) {
            for(DetallePedidoEntity detalle : pedido.getDetallePedidoEntitys()) {
                detallePedidoService.guardar(detalle);
            }
            return Optional.of(guardar(pedido));
        }
        return pedidoOptional;
    }

    public Optional<PedidoDTO> delete(Integer id) {
        Optional<PedidoDTO> pedidoOptional = getPedidoById(id);
        if (pedidoOptional.isPresent()) {
            PedidoDTO pedidoDb = pedidoOptional.orElseThrow();
            for(DetallePedidoEntity detalle : pedidoDb.getDetallePedidoEntitys()) {
                DetallePedidoPrimaryKey detalleId = new DetallePedidoPrimaryKey();
                detalleId.setNumeroPedido(detalle.getNumeroPedido());
                detalleId.setIdProducto(detalle.getIdProducto());
                detallePedidoService.eliminar(detalleId);
            }
            pedidoRepository.delete(pedidoConverter.convertToEntity(pedidoDb));
            return Optional.of(pedidoDb);
        }
        return pedidoOptional;
    }

    
}
