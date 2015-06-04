package car.advisor

import grails.converters.JSON
import javax.servlet.http.HttpServletResponse
import car.advisor.result.SyntheseVehicule

class AvisApiController {

    def index() { }
	
	def  avis() {
		log.error(params)
		if(!params.identifiant)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
		}
		Integer paramValue = params.int('identifiant')
		def vehicule = Vehicule.findByIdentifiant(paramValue)
			
		
		if (vehicule){
			render vehicule.avis as JSON
		} else {
			log.error('Vehicule non trouve')
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
		}
	}
}
