package com.mitocode.service;

import com.mitocode.model.Client;

import java.util.List;

public interface IClientService extends ICRUD<Client,Integer> {
    /*
    Client save(Client Clent) throws Exception;
    Client update(Client Clent, Integer id) throws Exception;
    List<Client> readAll() throws Exception;
    Client readById(Integer id) throws Exception;
    void deleteById(Integer id) throws Exception;
     */

    List<Client> getClientsAdulst();
}
