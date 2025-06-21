# Distributore Automatico

Un sistema di simulazione di distributore automatico sviluppato in Java che permette agli utenti di acquistare bevande e agli amministratori di gestire l'inventario.

## Caratteristiche

### Per gli Utenti
- Visualizzazione prodotti disponibili con codici e prezzi
- Sistema di pagamento con monete virtuali (0.10€, 0.20€, 0.50€, 1.00€, 2.00€)
- Calcolo e restituzione del resto
- Gestione prodotti esauriti

### Per gli Amministratori
- Accesso protetto con codice admin (`admin123`)
- Gestione completa dell'inventario:
  - Aggiunta nuovi prodotti
  - Rimozione prodotti esistenti
  - Modifica di codice, nome e prezzo
  - Rifornimento prodotti
- Visualizzazione incassi totali
- Reset del credito del distributore

## Struttura del Progetto

```
src/
└── com/
    ├── Distributore.java                 # Classe principale
    ├── DistributoreLogic.java            # Logica del distributore
    ├── Gestore.java                      # Gestione amministrativa
    ├── Prodotto.java                     # Classe astratta prodotto
    ├── Bevanda.java                      # Implementazione bevanda
    ├── ListaProdotti.java                # Gestione lista prodotti
    ├── ProdottoNonTrovatoException.java  # Eccezione personalizzata
    └── PrezzoNonValidoException.java     # Eccezione personalizzata
```
## Flowchart del Funzionamento

![Immagine WhatsApp 2025-06-21 ore 16 12 18_7a7e8dc1](https://github.com/user-attachments/assets/e6317285-9c24-4990-9d61-8b8bc1d897e0)
Questo flowchart descrive il funzionamento del distributore automatico comprendendo il funzionamento generale e l'accesso a funzionalità esterne.

## Utilizzo

### Modalità Utente
1. All'avvio, il distributore mostra i prodotti disponibili
2. Inserisci il codice del prodotto desiderato (es. `A01` per Fanta)
3. Segui le istruzioni per inserire le monete
4. Ricevi il prodotto e l'eventuale resto

### Modalità Amministratore
1. Inserisci il codice `admin123` al prompt iniziale
2. Accedi al menu amministrativo con le seguenti opzioni:
   - **1**: Visualizza inventario completo
   - **2**: Aggiungi nuovo prodotto  
   - **3**: Rimuovi prodotto
   - **4**: Modifica prodotto esistente
   - **5**: Rifornisci prodotto
   - **6**: Visualizza incasso totale
   - **7**: Reset incasso distributore
   - **8**: Esci dalla modalità amministratore
   - **9**: Spegni distributore

## Prodotti Predefiniti

Il distributore viene inizializzato con:
- **A01** - Fanta (1.50€) - 3 pezzi
- **A02** - Coca Cola (1.70€) - 7 pezzi  
- **A03** - Acqua (1.00€) - 8 pezzi

## Architettura del Sistema

### Classi Principali

#### `DistributoreLogic`
- Gestisce la logica principale del distributore
- Controlla il processo di acquisto e pagamento
- Gestisce il credito del distributore

#### `Gestore` 
- Fornisce funzionalità amministrative
- Gestione CRUD dei prodotti
- Controllo incassi e crediti

#### `Prodotto` (Astratta)
- Classe base per tutti i prodotti
- Attributi: codice, nome, prezzo, quantità
- Metodi getter/setter e toString

#### `Bevanda`
- Implementazione concreta di Prodotto
- Rappresenta le bevande nel distributore

#### `ListaProdotti`
- Gestisce la lista centralizzata dei prodotti
- Pattern Singleton per mantenere una sola istanza
- Metodi per la visualizzazione utente

## Gestione Errori

Il sistema gestisce vari tipi di errori:
- **ProdottoNonTrovatoException**: Prodotto inesistente o esaurito
- **PrezzoNonValidoException**: Prezzo non valido (≤ 0)
- **InputMismatchException**: Input non validi dell'utente

---

⭐ Se il progetto ti è piaciuto, lascia una stella!
