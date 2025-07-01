package com.mitocode.service.Impl;

import com.mitocode.model.Client;
import com.mitocode.repo.IClientRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICRUD;
import com.mitocode.service.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor
@RequiredArgsConstructor
public class ClientServiceImpl extends CRUDImpl<Client, Integer> implements IClientService {

    private final IClientRepo clientRepo;

    @Override
    protected IGenericRepo<Client, Integer> getRepo() {
        return clientRepo;
    }

    @Override
    public List<Client> getClientsAdulst() {
        return List.of();
    }

    //@Autowired
    /*
    private final IClientRepo repo;

    @Override
    public Client save(Client Client) throws Exception {
        return repo.save(Client);
    }

    @Override
    public Client update(Client Client, Integer id) throws Exception {
        Client.setIdClient(id);
        return repo.save(Client);
    }

    @Override
    public List<Client> readAll() throws Exception {
        return repo.findAll();
    }

    @Override
    public Client readById(Integer id) throws Exception {
        return repo.findById(id).orElse(new Client());
    }

    @Override
    public void deleteById(Integer id) throws Exception {
        repo.deleteById(id);
    }

     */
}
