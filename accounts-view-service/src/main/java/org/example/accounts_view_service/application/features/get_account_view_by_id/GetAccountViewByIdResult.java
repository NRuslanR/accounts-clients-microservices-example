package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.example.accounts_view_service.application.features.shared.AccountView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class GetAccountViewByIdResult 
{
    private AccountView accountView;
}
