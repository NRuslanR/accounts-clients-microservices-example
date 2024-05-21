import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "Should AccountDebited event be raised"

    input {

        triggeredBy('accountDebited()')
    }

    label('triggerAccountDebitedEvent')

    outputMessage {

        sentTo('org.example.event_publisher.application.accounts.domain.Account')
        body([
            id: $(uuid()),
            aggregateId: $(uuid()),
            balance: $(anInteger()),
            withdrawAmount: $(positiveInt())
        ])
        headers {
            header('event-aggregate-type', 'org.example.event_publisher.application.accounts.domain.Account')
            header('event-aggregate-id', $(uuid()))
        }
    }
}