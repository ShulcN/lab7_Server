package Command;

import Given.Product;
import Manager.CollectionManager;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class History extends Command{
    private static final long serialVersionUID = 11L;
    protected String name="history";
    public History(String login){
        this.login=login;
    }
    /** вывод последних введенных команд(до 15-ти) в консоль*/
    @Override
    public void DoCommand(CollectionManager collectionManager){
        if(collectionManager.getCommands().size()>0){
            Stream<Command> stream = collectionManager.getCommands().stream();
            ArrayList<Command> myComs = stream.filter(c -> c.getLogin().equals(login)).collect(Collectors.toCollection(ArrayList::new));
            stream.close();
            if (myComs.isEmpty()){
                message = "0):";
                return;
            }
            Stream<Command> commandStream = myComs.stream();
            if (myComs.size()<=15){
                message = myComs.size()+"): ";
                message+=commandStream.map(Command::toString).collect(Collectors.joining("; "));
                commandStream.close();
            } else {
                message =   "15): ";
                message+=commandStream.skip(collectionManager.getCommands().size()-15).map(Command::toString).collect(Collectors.joining("; "));
                commandStream.close();
            }
        } else {
            message = "0):";
        }

    }

}
