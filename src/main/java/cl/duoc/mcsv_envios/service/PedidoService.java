package cl.duoc.mcsv_envios.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.duoc.mcsv_envios.model.entity.PedidoEntity;
import cl.duoc.mcsv_envios.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    public List<PedidoEntity> getAllPedidos() {
        return pedidoRepository.findAll();
    }
}
