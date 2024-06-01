import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "Should AccountCredited event be raised"

    input {

        triggeredBy('accountCredited()')
    }

    label('triggerAccountCreditedEvent')

    outputMessage {

        sentTo('org.example.accounts_service.application.accounts.domain.Account')
        body([
            id: $(uuid()),
            aggregateId: $(uuid()),
            balance: $(positiveInt()),
            depositAmount: $(positiveInt())
        ])
        headers {
            header('event-aggregate-type', 'org.example.accounts_service.application.accounts.domain.Account')
            header('event-aggregate-id', $(uuid()))
        }
    }
}