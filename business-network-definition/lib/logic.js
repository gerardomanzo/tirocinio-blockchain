/**
 * Aggiunge un criterio di valutazione ad un evento.
 * @param {it.unisa.AggiungiCriterio} tx
 * @transaction
 */
function aggiungiCriterio(tx) {
	var evento = tx.evento;
	var criterio = tx.criterio;

	if(evento.criteri)
    	evento.criteri.push(criterio);
	else
    	evento.criteri = [criterio];

	// Get the asset registry for the asset.
	return getAssetRegistry('it.unisa.Evento')
		.then(function (assetRegistry) {
			// Update the asset in the asset registry.
			return assetRegistry.update(evento);
		});
}

/**
 * Conferma la registrazione di un utente.
 * @param {it.unisa.ConfermaRegistrazioneUtente} tx
 * @transaction
 */
function confermaRegistrazioneUtente(tx) {
	var utente = tx.utente;

	utente.statoRegistrazione = true;

	// Get the participant registry for the participant.
	return getParticipantRegistry('it.unisa.Utente')
		.then(function (participantRegistry) {
			// Update the asset in the participant registry.
			return participantRegistry.update(utente);
		});
}

/**
 * Conferma la registrazione di un oggetto.
 * @param {it.unisa.ConfermaRegistrazioneOggetto} tx
 * @transaction
 */
function confermaRegistrazioneOggetto(tx) {
	var oggetto = tx.oggetto;

	oggetto.statoRegistrazione = true;

	// Get the asset registry for the asset.
	return getAssetRegistry('it.unisa.Oggetto')
		.then(function (assetRegistry) {
			// Update the asset in the asset registry.
			return assetRegistry.update(oggetto);
		});
}

/**
 * Conferma la registrazione di una partecipazione.
 * @param {it.unisa.ConfermaRegistrazionePartecipazione} tx
 * @transaction
 */
function ConfermaRegistrazionePartecipazione(tx) {
	var partecipazione = tx.partecipazione;

	partecipazione.statoRegistrazione = true;

	// Get the asset registry for the asset.
	return getAssetRegistry('it.unisa.Partecipazione')
		.then(function (assetRegistry) {
			// Update the asset in the asset registry.
			return assetRegistry.update(partecipazione);
		});
}