package org.example.accounts_service.application.shared.infrastructure.persistence;

import java.util.UUID;

import org.example.accounts_service.application.shared.domain.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainEntityRepository<Entity extends BaseEntity> extends JpaRepository<Entity, UUID> 
{
    
}
