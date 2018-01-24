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
