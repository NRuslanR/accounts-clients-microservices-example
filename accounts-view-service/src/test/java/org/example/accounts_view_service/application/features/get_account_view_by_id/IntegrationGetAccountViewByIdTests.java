package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.example.accounts_view_service.application.shared.config.IntegrationApplicationTest;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationApplicationTest
public class IntegrationGetAccountViewByIdTests extends GetAccountViewByIdTests 
{
    @Autowired
    public IntegrationGetAccountViewByIdTests(
        GetAccountViewById getAccountViewById,
        TestCreateAccountView testCreateAccountView
    ) 
    {
        super(getAccountViewById, testCreateAccountView);
    }
}
