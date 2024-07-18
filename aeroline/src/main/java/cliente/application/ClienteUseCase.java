package cliente.application;

import cliente.domain.entity.Cliente;
import cliente.domain.service.ClienteService;

public class ClienteUseCase {
    private final ClienteService clienteService;

    public ClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    public Cliente consultarCliente(Long id_cliente){
        Cliente cliente = clienteService.consultarCliente(id_cliente);
        return cliente;
    }

}
