package cliente.domain.service;

import cliente.domain.entity.Cliente;

public interface ClienteService {
    Cliente consultarCliente(Long id_cliente);
    Boolean updateCliente(Cliente cliente);
}
