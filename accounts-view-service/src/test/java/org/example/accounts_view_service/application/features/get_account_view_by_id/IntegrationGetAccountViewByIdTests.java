package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.example.accounts_view_service.application.shared.config.IntegrationApplicationTest;
import org.example.accounts_view_service.application.shared.config.IntegrationTestsConfig;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

@IntegrationApplicationTest
@Import(IntegrationTestsConfig.class)
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
