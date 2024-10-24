package fr.bxcchus.api.services;

import fr.bxcchus.api.HttpMethod;
import fr.bxcchus.api.IClient;
import fr.bxcchus.objects.Session;

import java.io.IOException;

public interface SessionService extends IClient {

    // Méthode pour récupérer la session utilisateur
    default Session getSession() {
        String sessionUrl = "lol-login/v1/session";
        return request(sessionUrl, HttpMethod.GET, null, Session.class);
    }

    // Méthode utilitaire pour obtenir le PUUID de l'utilisateur courant
    default String getCurrentPlayerPuuid() throws IOException {
        return getSession().getPuuid();
    }
}
