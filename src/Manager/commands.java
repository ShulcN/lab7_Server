//package Manager;
//import java.io.*;
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//public class commands{
//    /** Менеджер коллекции*/
//    private CollectionManager collManager;
//    /** команда введенная в консоль*/
//    private String command;
//    /** сканнер консоли */
//    private BufferedInputStream input;
//    /** список всех введенных команд */
//    private ArrayList<String> commands = new ArrayList<>();
//
//    public commands(CollectionManager collManager){
//        this.collManager = collManager;
//    }
//    /** включение  считывания ввода из консоли*/
//    public void TurnOn() {
//        System.out.println("Здравствуйте, напишите \"help\", чтобы узнать все команды");
//        input = new BufferedInputStream(System.in);
//        BufferedReader br = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
//         {
//            while (true) {
//                try {
//                command = br.readLine();
//                SwitchCommand.switchCommand(command, collManager, commands);}
//                catch (IOException e){
//                    System.out.println("Что-то случилось с вводом!");
//                }
//            }
//        }
//    }
//    /**Метод возвращающий список команд*/
//    public ArrayList<String> getCommands() {
//        return commands;
//    }
//}
