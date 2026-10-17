package Service;
import Crud.api.dto.ClientDto;
import Crud.api.entities.Client;
import Service.Exception.DataBaseException;
import Service.Exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.ClientRepository;
import org.springframework.data.domain.Pageable;


@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDto findById( Long id){
        Client client =repository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Cliente não encontrado"));
        return new ClientDto(client);
    }
    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDto(x));
    }
    @Transactional
    public ClientDto insert(ClientDto dto) {
        Client entity = new  Client();
        copyDtoEntity(dto , entity);
        entity = repository.save(entity);
        return new  ClientDto(entity);
    }
    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        try {
            Client entity = repository.getReferenceById(id);
            copyDtoEntity(dto , entity);
            entity = repository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }
    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
    throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){

            throw new DataBaseException("Falha de intregidade referencial");
        }
    }

private void copyDtoEntity(ClientDto dto, Client entity) {
    entity.setName(dto.getName());
    entity.setCpf(dto.getCpf());
    entity.setIncome(dto.getIncome());
    entity.setBirthDate(dto.getBirthDate());
    entity.setChildren(dto.getChildren());

}}