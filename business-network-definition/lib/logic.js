/**
 * Aggiunge un criterio di valutazione ad una votazione.
 * @param {it.unisa.AggiungiCriterio} tx
 * @transaction
 */
function aggiungiCriterio(tx) {
	var votazione = tx.votazione;
	var criterio = tx.criterio;

	if(votazione.criteri)
    	votazione.criteri.push(criterio);
	else
    	votazione.criteri = [criterio];

	// Get the asset registry for the asset.
	return getAssetRegistry('it.unisa.Votazione')
		.then(function (assetRegistry) {
			// Update the asset in the asset registry.
			return assetRegistry.update(votazione);
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
 * Conferma la registrazione di una candidatura.
 * @param {it.unisa.ConfermaRegistrazioneCandidatura} tx
 * @transaction
 */
function confermaRegistrazioneCandidatura(tx) {
	var candidatura = tx.candidatura;

	candidatura.statoRegistrazione = true;

	// Get the asset registry for the asset.
	return getAssetRegistry('it.unisa.Candidatura')
		.then(function (assetRegistry) {
			// Update the asset in the asset registry.
			return assetRegistry.update(candidatura);
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