import car.advisor.Vehicule

class BootStrap {

    def init = { servletContext ->
		
		//chargement du fichier csv
		log.info('chargement des vehicules')
		def csv = new File("grails-app/conf/versions_voitures.csv")
		def vehicule
		csv.splitEachLine(',') { row ->
			log.info(row[0])
			vehicule = new Vehicule (
			   identifiant : row[0],
			   marque: row[1],
			   modele:row[2],
			   generation:row[3],
			   carrosserie:row[4],
			   declinaison:row[5],
			   urlImage:row[32]
			   ) 
			log.info(vehicule)
			vehicule.save(failOnError: true, flush: true)
			
		}
			
			
    }
    def destroy = {
    }
}
