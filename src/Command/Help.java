package Command;

import Manager.CollectionManager;

public class Help extends Command{
    private static final long serialVersionUID = 6L;
    protected String name="help";
    public Help(String login){

    }
    /**вывод справки по доступным командам */
    public void DoCommand(CollectionManager collectionManager){
        message = "listOfCommands";
    }
}
