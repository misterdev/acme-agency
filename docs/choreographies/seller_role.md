# Venditore
```
(
  (
    (
      askAvail#acme;                  // Riceve da Acme una richiesta di quando e' disponibile
      availReply@acme;                // dato che un acquirente e' interessato a una casa
    
      1                               // L'acquirente accetta
        +
      (
        sendMeetingProposal#acme;     // L'aquirente ha proposto nuove date
        meetingProposalReply@acme
      )
    );

    (
      meetDenied#acme                 // Skip --- incontro riufiutato
    )
      +
    (
      meetAgree#acme;                 // Incontro accettato

      (
        noOffer#acme                  // Dopo l'incontro non riceve offerte
      )
        +
      (
        offer#acme;                   // Riceve offerta

        (
          denyOffer@acme              // Rifiuta offerta
        )
          +
        (
          agreeOffer@acme;            // Accetta offerta
          sendDeposit#bank;
          signContract@nota;
          contractDone#nota;
          pay#bank
        )
      )
    )
  )
    |  
  (
    offer@acme                        // Propone casa all'agenzia
  )
)
```