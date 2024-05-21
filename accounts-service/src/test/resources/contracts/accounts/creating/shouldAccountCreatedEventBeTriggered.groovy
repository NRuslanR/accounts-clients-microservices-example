import org.springframework.cloud.contract.spec.Contract

Contract.make {

    description "Should AccountCreated event be raised"

    input {

        triggeredBy('accountCreated()')
    }

    label('triggerAccountCreatedEvent')

    outputMessage {

        sentTo('org.example.event_publisher.application.accounts.domain.Account')
        body([
            id: $(uuid()),
            aggregateId: $(uuid()),
            name: $(nonBlank()),
            amount: $(positiveInt())
        ])
        headers {
            header('event-aggregate-type', 'org.example.event_publisher.application.accounts.domain.Account')
            header('event-aggregate-id', $(uuid()))
        }
    }
}