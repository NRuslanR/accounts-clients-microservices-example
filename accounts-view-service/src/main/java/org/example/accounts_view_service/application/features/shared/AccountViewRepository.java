package org.example.accounts_view_service.application.features.shared;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AccountViewRepository extends ReactiveMongoRepository<AccountView, String>  
{
}
