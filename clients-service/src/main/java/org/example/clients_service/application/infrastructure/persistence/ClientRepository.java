package org.example.clients_service.application.infrastructure.persistence;

import java.util.UUID;

import org.example.clients_service.application.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, UUID>
{

}
