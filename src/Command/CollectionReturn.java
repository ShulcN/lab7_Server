package Command;

import Manager.CollectionManager;


public class CollectionReturn extends Command{
    private static final long serialVersionUID = 28L;

    public void DoCommand(CollectionManager collectionManager){
        //linkedHashSet=collectionManager.getProducts();
        message="Success ";
        linkedHashSet=CollectionManager.getProducts();
    }
}
