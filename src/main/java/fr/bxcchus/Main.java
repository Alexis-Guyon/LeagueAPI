package fr.bxcchus;

import fr.bxcchus.methods.MatchMethod;
import fr.bxcchus.methods.tasks.AcceptMatchTask;
import fr.bxcchus.subjects.GameFlowSubject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MatchMethod matchMethod = new MatchMethod();
        GameFlowSubject gameFlowSubject = new GameFlowSubject();
        AcceptMatchTask matchTask = new AcceptMatchTask(matchMethod);
        gameFlowSubject.addObserver(matchTask);
        gameFlowSubject.startPhaseWatcher(matchMethod);

        try {
            matchMethod.findMatch();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}