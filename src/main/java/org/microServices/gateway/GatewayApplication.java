package org.microServices.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean //dynamic routing, obligatoire de l'ajouter
	/*A chaque fois qu'elle  reçoit une requête, elle regarde le prermier élement qui suit le port,c'est le nom
	* du micro service, lui elle donne ce nom au serveur d'application "Discovery service" qui lui retourne
	* le path du microService(son port et son adresse ip),elle passe la requete au microservice qui lui retourne
	* le resultat, et finalement le resultat retourné sera passé à la UI/
	* si on a un problème de montée en  charge et que la gateway decouvre qu'il en a plusieurs instances du microservice
	* elle fait un loadbalancing pour repartir les charges entre les instances */
	DiscoveryClientRouteDefinitionLocator discoveryClientRouteDefinitionLocator(
			ReactiveDiscoveryClient reactiveDiscoveryClient, DiscoveryLocatorProperties discoveryLocatorProperties){
		return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient,discoveryLocatorProperties);
	}
}
