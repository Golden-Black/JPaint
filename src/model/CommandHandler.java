package model;

import model.interfaces.ICommand;

public class CommandHandler {
    public void process(ICommand command){
        command.execute();
    }
}
