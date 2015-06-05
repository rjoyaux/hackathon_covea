package car.advisor

import grails.converters.JSON
import javax.servlet.http.HttpServletResponse
import car.advisor.result.SyntheseVehicule
import car.advisor.result.SyntheseAvis

class AvisApiController {

    def index() { }
	
	def nouvelAvis() {
		if(!params.identifiantUtilisateur || !params.identifiantVehicule)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		
		Integer paramValue = params.int('identifiantVehicule')
		def vehicule = Vehicule.findByIdentifiant(paramValue)
		def utilisateur = Utilisateur.findByRefPers(params.identifiantUtilisateur)
		
		if(!vehicule || !utilisateur)
		{
			log.error("Post avis, vehicule ou avis non trouve : vehicule : ${vehicule}, utilisateur : ${utilisateur}")
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		
		def avis = new Avis (
			noteConsommation : params.int('noteConsommation'),
			noteQualiteFinition : params.int('noteQualiteFinition'),
			noteSecurite : params.int('noteSecurite'),
			noteRapportQualitePrix : params.int('noteRapportQualitePrix'),
			noteConfort : params.int('noteConfort'),
			noteCoutEntretien : params.int('noteCoutEntretien'),
			description : params.description,
			vehicule : vehicule,
			utilisateur : utilisateur
			
		)
		avis.save(failOnError: true, flush: true)
		
		response.sendError(HttpServletResponse.SC_CREATED)
		return
	}
	
	def  avis() {
		log.error(params)
		if(!params.identifiant)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		Integer paramValue = params.int('identifiant')
		def vehicule = Vehicule.findByIdentifiant(paramValue)
		
		def restultat = []
		def syntheseAvis
		if (vehicule){
			vehicule.avis.each { it ->
				syntheseAvis = new SyntheseAvis(
					avis : it,
					utilisateur : it.utilisateur
					) 
				restultat << syntheseAvis
			}
			
			render restultat as JSON
		} else {
			log.error('Vehicule non trouve')
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
		}
	}
}
