package org.example.accounts_view_service.application.features.shared;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(staticName = "of")
@EqualsAndHashCode
@ToString
@Document
public class AccountView 
{
    @Id
    private String accountId;

    private String name;

    private int balance;
}
