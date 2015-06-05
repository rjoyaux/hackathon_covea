package car.advisor

import grails.converters.JSON
import javax.servlet.http.HttpServletResponse


class UtilisateurApiController {
	
	
	
	def utilisateur() {
		if(!params.identifiant)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST)
			return
		}
		def utilisateur = Utilisateur.findByRefPers(params.identifiant)
		if (utilisateur){
			render utilisateur as JSON
			return
		} else {
			log.error("Utilisateur non trouve : ${paramValue}")
			response.sendError(HttpServletResponse.SC_NOT_FOUND)
			return
		}
		
		
	}
}
