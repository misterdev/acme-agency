# Catasto

```
(
  askCada#acme;               // Riceve richiesta per coordinate
  replyCada#acme;             // Risponde alla richiesta, anche richiedendo correzioni
          
  (
    1                         // Skip --- la richiesta era corretta
      +
    (      
      askCada#acme;           // Riceve la richiesta aggiustata
      replyCada#acme;         // Risponde, anche richiedendo intervento dipendente

      (
        1                     // Skip --- la correzione era valida
          +
        sendAgent#acme        // Riceve il dipendente dell'agenzia
      )      
    )
  )
)*
```