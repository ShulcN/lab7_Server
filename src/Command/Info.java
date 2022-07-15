package Command;

import Manager.CollectionManager;

public class Info extends Command{
    private static final long serialVersionUID = 7L;
    protected String name="info";
    /**Метод вывода информации о коллекции*/
    public Info(String login){

    }
    @Override
    public void DoCommand(CollectionManager linkedHashSet){
        message = " " + CollectionManager.getProducts().size();
    }
}
