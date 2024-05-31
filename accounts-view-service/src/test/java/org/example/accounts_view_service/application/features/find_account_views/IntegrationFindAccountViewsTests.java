package org.example.accounts_view_service.application.features.find_account_views;

import org.example.accounts_view_service.application.shared.config.IntegrationApplicationTest;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationApplicationTest
public class IntegrationFindAccountViewsTests extends FindAccountViewsTests 
{
    @Autowired
    public IntegrationFindAccountViewsTests(
        FindAccountViews getAccountViews,
        TestCreateAccountView testCreateAccountView
    ) 
    {
        super(getAccountViews, testCreateAccountView);
    }
}
