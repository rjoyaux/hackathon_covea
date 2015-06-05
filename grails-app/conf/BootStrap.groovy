import car.advisor.Vehicule
import car.advisor.Utilisateur
import car.advisor.Avis

class BootStrap {

    def init = { servletContext ->
		
		//chargement du fichier csv
		log.error('chargement des vehicules')
		def csv = new File("grails-app/conf/versions_voitures.csv")
		def vehicule
		csv.splitEachLine(',') { row ->			
			vehicule = new Vehicule (
			   identifiant : row[0],
			   marque: row[1],
			   modele:row[2],
			   generation:row[3],
			   carrosserie:row[4],
			   declinaison:row[5],
			   urlImage:row[32]
			   ) 
			vehicule.save(failOnError: true, flush: false)
		}
		
		log.error('chargement des utilisateurs')
		csv = new File("grails-app/conf/fichier_client.csv")
		def utilisateur
		csv.splitEachLine(';') { row ->
			utilisateur = new Utilisateur (
				nom : row[0],
				prenom : row[1],
				ville : row[2],
				age : row[3].toInteger(),
				nbEnfants : row[4].toInteger(),
				refPers : row[5],
				dateDebutEffet : row[7],
				urlAvatar : row[8]
			)
			utilisateur.save(failOnError: true, flush: false)
		}
		
		log.error('chargement des avis')
		csv = new File("grails-app/conf/avis.csv")
		def avis
		csv.splitEachLine(';') { row ->
			avis = new Avis (
			   noteConsommation : row[0],
			   noteQualiteFinition: row[1],
			   noteSecurite:row[2],
			   noteRapportQualitePrix:row[3],
			   noteConfort:row[4],
			   noteCoutEntretien:row[5],
			   description:row[6],
			   vehicule : Vehicule.findByIdentifiant(row[7]),
			   utilisateur : Utilisateur.findByRefPers(row[8])
			   )
			avis.save(failOnError: true, flush: false)
		}
		
		
    }
    def destroy = {
    }
}
