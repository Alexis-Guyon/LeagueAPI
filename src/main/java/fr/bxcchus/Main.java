package fr.bxcchus;

import fr.bxcchus.api.services.GameService;
import fr.bxcchus.api.services.impl.GameServiceImpl;
import fr.bxcchus.methods.tasks.AcceptMatchTask;
import fr.bxcchus.subjects.GameFlowSubject;

public class Main  {
    public static void main(String[] args) {
        GameService gameService = new GameServiceImpl();
        GameFlowSubject subject = new GameFlowSubject();

        AcceptMatchTask acceptMatchTask = new AcceptMatchTask(gameService);

        subject.addObserver(acceptMatchTask);

        subject.startPhaseWatcher(gameService);
    }

}