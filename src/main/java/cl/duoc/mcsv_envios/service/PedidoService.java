package cl.duoc.mcsv_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_envios.model.dto.PedidoDTO;
import cl.duoc.mcsv_envios.model.dto.converter.PedidoConverter;
import cl.duoc.mcsv_envios.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PedidoConverter pedidoConverter;
    
    public List<PedidoDTO> getAllPedidos() {
        return pedidoConverter.convertToDTOList(pedidoRepository.findAll());
    }
    
}
