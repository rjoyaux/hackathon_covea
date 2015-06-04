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
				dateDebutEffet : row[7]
				// date d'assurance
			)
			utilisateur.save(failOnError: true, flush: false)
		}
		
		log.error('chargement des avis')
		def avis = new Avis (
			noteConsommation : 1,
			noteQualiteFinition : 2,
			noteSecurite : 3,
			noteRapportQualitePrix : 4,
			noteConfort : 5,
			noteCoutEntretien : 5,
			description : 'Je suis hypra content de ma caisse',
			vehicule : Vehicule.findByIdentifiant(70122)
		)
		avis.save(failOnError: true, flush: false)
		
		avis = new Avis (
			noteConsommation : 1,
			noteQualiteFinition : 2,
			noteSecurite : 3,
			noteRapportQualitePrix : 4,
			noteConfort : 5,
			noteCoutEntretien : 5,
			description : 'Super super',
			vehicule : Vehicule.findByIdentifiant(70122)
		)
		avis.save(failOnError: true, flush: false)
		
    }
    def destroy = {
    }
}
