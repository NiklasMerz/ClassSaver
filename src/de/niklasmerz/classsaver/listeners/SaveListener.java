package de.niklasmerz.classsaver.listeners;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;

import de.niklasmerz.classsaver.CSLog;

public class SaveListener implements IExecutionListener  {

    @Override
    public void notHandled(String arg0, NotHandledException arg1) {

    }

    @Override
    public void postExecuteFailure(String arg0, ExecutionException arg1) {

    }

    @Override
    public void postExecuteSuccess(String arg0, Object arg1) {
        System.out.println("test");
        CSLog.logInfo("Post Save?");
    }

    @Override
    public void preExecute(String arg0, ExecutionEvent arg1) {

    }

}