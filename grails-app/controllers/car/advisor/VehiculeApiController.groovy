package car.advisor

import grails.converters.JSON

import javax.servlet.http.HttpServletResponse

import car.advisor.result.SyntheseVehicule

class VehiculeApiController {

    def index() { 
		
		
	}
	
	def search() {
		if(!params.q)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		
		def recherche =  "%" + params.q + "%"
		
		log.error(recherche)
		
		def query = Vehicule.where {
			marque =~ recherche ||
			modele =~ recherche ||
			generation =~ recherche ||
			carrosserie =~ recherche ||
			declinaison =~ recherche			
		}
		
		def res = query.list()
		
		render res as JSON
			
			
		
	}
	
	def vehicule() {
		if(!params.identifiant)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		Integer paramValue = params.int('identifiant')
		def vehicule = Vehicule.findByIdentifiant(paramValue)
		
		
		
		if (vehicule){
			// les avis liés au véhicule
			def noteConsommation = 0
			def noteQualiteFinition = 0
			def noteSecurite = 0
			def noteRapportQualitePrix = 0
			def noteConfort = 0
			def noteCoutEntretien = 0
			def noteGlobale = 0
			
			def moyenneConsommation = 0
			def moyenneQualiteFinition = 0
			def moyenneSecurite = 0
			def moyenneRapportQualitePrix = 0
			def moyenneConfort = 0
			def moyenneCoutEntretien = 0
			
			def nbAvis = 0
			
			vehicule.avis.each { it ->
				noteConsommation += it.noteConsommation
				noteQualiteFinition += it.noteQualiteFinition
				noteSecurite += it.noteSecurite
				noteRapportQualitePrix += it.noteRapportQualitePrix
				noteConfort += it.noteConfort
				noteCoutEntretien += it.noteCoutEntretien
				nbAvis++
			}
			
			if (nbAvis>0)
			{
				moyenneConsommation = noteConsommation / nbAvis
				moyenneQualiteFinition = noteQualiteFinition/ nbAvis
				moyenneSecurite = noteSecurite/ nbAvis
				moyenneRapportQualitePrix = noteRapportQualitePrix/ nbAvis
				moyenneConfort = noteConfort/ nbAvis
				moyenneCoutEntretien = noteCoutEntretien/ nbAvis
				
				noteGlobale = (moyenneConsommation +
					moyenneQualiteFinition + moyenneSecurite + moyenneRapportQualitePrix +
					moyenneConfort + moyenneCoutEntretien) / 6
			}
			
			def result = new SyntheseVehicule(
				vehicule : vehicule,
				noteConsommation : moyenneConsommation,
				noteQualiteFinition : moyenneQualiteFinition,
				noteSecurite : moyenneSecurite,
				noteRapportQualitePrix : moyenneRapportQualitePrix,
				noteConfort : moyenneConfort,
				noteCoutEntretien : moyenneCoutEntretien,
				nbAvis:nbAvis,
				noteGlobale : noteGlobale			
			)
			
			render result as JSON
		} else {
			log.error('Vehicule non trouve')
			response.sendError(HttpServletResponse.SC_NOT_FOUND) 
		}
		
		
	}
}
