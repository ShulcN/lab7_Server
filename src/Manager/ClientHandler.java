package Manager;

import Command.*;
import Given.Product;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;


public class ClientHandler extends Thread{
        private ArrayList<ClientHandler> clientHandlers;
        private Socket client;
private ObjectOutputStream objectOutputStream;
private ObjectInputStream objectInputStream;
private CollectionManager collectionManager;
private boolean soloCom=false;
private String message;
private ArrayList<String> commands;
public ClientHandler(Socket client, ArrayList<ClientHandler> clientHandlers, CollectionManager collectionManager) throws IOException {
        this.client=client;
        this.clientHandlers=clientHandlers;
        this.collectionManager=collectionManager;
        objectInputStream=new ObjectInputStream(client.getInputStream());
        objectOutputStream= new ObjectOutputStream(client.getOutputStream());
        commands=new ArrayList<>();
}
        @Override
        public void run() {
                try {
                        while (true) {
                                try {
                                        Command com;
                                        //Server2.clientHandlers.add(this);
                                        if (!clientHandlers.contains(this))this.clientHandlers.add(this);
                                        if(!Server2.clientHandlers.contains(this))Server2.clientHandlers.add(this);
                                        try {
                                                com = (Command) objectInputStream.readObject();
                                        }catch (EOFException | SocketException e){
                                                System.out.println("отключился");
                                                return;
                                        }
                                        com.DoCommand(collectionManager);
                                        if (com.toString() != null) {
                                                collectionManager.getCommands().add(com);
                                        }
                                        //System.out.println(com.getMessage());
                                        message = com.getMessage();
                                        soloCom=false;
                                        try {
                                                if (!(com.toString().contains("dd") || com.toString().contains("reg") || com.toString().contains("clear") || com.toString().contains("remove_by_id") || com.toString().contains("update"))) {
                                                        throw new NullPointerException();
                                                }
                                        } catch (NullPointerException e) {
                                                soloCom = true;
                                        }
                                        if (soloCom) {
                                                Product[] product=new Product[0];
                                                try {
                                                        if (message.startsWith("Это все")) {
                                                                product = ((FilterStartsWithName)com).getLinkedHashSet().toArray(new Product[0]);
                                                        } else{
                                                                product = CollectionManager.getProducts().toArray(new Product[0]);
                                                        }
                                                } catch (NullPointerException e){
                                                        e.printStackTrace();
                                                }
                                                InformationBox informationBox = new InformationBox(message, product, CollectionManager.getColorMap(), com.getProduct());
                                                informationBox.setOwner(com.getOwner());
                                                System.out.println("Для клиента:"+client+" коробака:"+informationBox.hashCode()+" "+informationBox.getColors()+" предметик "+informationBox.getProduct() +" "+ informationBox.getMessage()+" а вот сама коллекция: "+Arrays.asList(informationBox.getProductsArray()));
                                                objectOutputStream.writeObject(informationBox);
                                                objectOutputStream.flush();
                                                //clientHandlers.remove(this);
                                                //Server2.clientHandlers.remove(this);
                                        } else {
                                                InformationBox informationBox;
                                                try {
                                                        if (com.toString().contains("reg")){
                                                                Product[] product = CollectionManager.getProducts().toArray(new Product[CollectionManager.getProducts().size()]);
                                                                informationBox = new InformationBox(message,product , CollectionManager.getColorMap(), com.getProduct());
                                                                informationBox.setColor(((Registration)com).getColor());
                                                                System.out.println(informationBox.getColor());
                                                                informationBox.setLogin(com.getLogin());
                                                                System.out.println(informationBox.getLogin()+" "+informationBox.getColor());
                                                        } else
                                                        if (com.toString().equals("clear")) {
                                                                Product[] product = ((Clear)com).getLinkedHashSet().toArray(new Product[((Clear)com).getLinkedHashSet().size()]);
                                                                informationBox = new InformationBox(message, product, CollectionManager.getColorMap(), null);
                                                        } else if ((com.toString().equals("add_if_min") || com.toString().equals("add_if_max"))) {
                                                                if (message.startsWith("Suc")) {
                                                                        soloCom = true;
                                                                        informationBox = new InformationBox(message, new Product[0], CollectionManager.getColorMap(), null);
                                                                } else {
                                                                        Product[] product = CollectionManager.getProducts().toArray(new Product[CollectionManager.getProducts().size()]);
                                                                        informationBox = new InformationBox(message,product , CollectionManager.getColorMap(), com.getProduct());
                                                                }
                                                        } else {
                                                                Product[] product = CollectionManager.getProducts().toArray(new Product[CollectionManager.getProducts().size()]);
                                                                informationBox = new InformationBox(message, product , CollectionManager.getColorMap(), com.getProduct());
                                                        }
                                                } catch (NullPointerException e) {
                                                        Product[] product = CollectionManager.getProducts().toArray(new Product[CollectionManager.getProducts().size()]);

                                                        informationBox = new InformationBox(message,product , CollectionManager.getColorMap(), com.getProduct());
                                                }
                                                informationBox.setLogin(com.getLogin());
                                                informationBox.setColor(CollectionManager.getColorMap().get(com.getLogin()));
                                                System.out.println(clientHandlers.size()+" - размер клентов");
                                                for (ClientHandler handler : clientHandlers) {
                                                        if(handler.getClient().isClosed())continue;
                                                        System.out.println("Для клиента:"+handler.client+" коробака:"+informationBox.hashCode()+" "+informationBox.getColor()+" предметик "+informationBox.getProduct() +" "+ informationBox.getMessage()+" а вот сама коллекция: "+ Arrays.asList(informationBox.getProductsArray()));
                                                        handler.getObjectOutputStream().writeObject(informationBox);
                                                        handler.getObjectOutputStream().flush();
                                                }
                                                //soloCom=true;
                                                //client.close();
                                                //Server2.clientHandlers.remove(this);
                                        }

                                } catch (Exception e) {
                                        e.printStackTrace();
                                }
                        }
                }catch (Exception e) {
                                        e.printStackTrace();
                                } finally {
                                        try {
                                                Server2.clientHandlers.remove(this);
                                                objectOutputStream.close();
                                                objectInputStream.close();
                                        } catch (IOException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }

        public Socket getClient() {
                return client;
        }

        public ObjectOutputStream getObjectOutputStream() {
                return objectOutputStream;
        }

        public ObjectInputStream getObjectInputStream() {
                return objectInputStream;
        }
}

