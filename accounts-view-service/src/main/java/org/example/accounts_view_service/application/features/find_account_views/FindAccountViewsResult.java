package org.example.accounts_view_service.application.features.find_account_views;

import java.util.List;

import org.example.accounts_view_service.application.features.shared.AccountView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FindAccountViewsResult 
{
    private List<AccountView> accountViews;
}
