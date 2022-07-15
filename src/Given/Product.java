package Given;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Product implements Comparable<Product>, Serializable {
    private static final long serialVersionUID = 101L;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null
    private String login="";
    private ProductType type=ProductType.DEFAULT;
    //private int ID=1;

//    public Product(Long id, String name, Coordinates coordinates, double price, UnitOfMeasure unitOfMeasure, Person owner) throws Exception{
//        if(id==null || name==null || name.equals("") || coordinates==null || price<=0 || unitOfMeasure==null || owner ==null || id<1)
//            throw new Exception();
//        this.coordinates = coordinates;
//        this.name = name;
//        this.price = price;
//        this.unitOfMeasure = unitOfMeasure;
//        this.id = id;
//        this.creationDate = new Date();
//    }
    public Product(Long id, String name, Coordinates coordinates, double price, Date date, UnitOfMeasure unitOfMeasure, ProductType type,String login){
        this.coordinates = coordinates;
        this.name = name;
        this.price = price;
        this.type=type;
        this.unitOfMeasure = unitOfMeasure;
        this.id = id;
        this.creationDate = date;
        this.login =login;
    }
    public Product(Long id, String name, Coordinates coordinates, double price, Date date, UnitOfMeasure unitOfMeasure, String login){
        this.coordinates = coordinates;
        this.name = name;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.id = id;
        this.creationDate = date;
        this.login =login;
    }
//    public Product(String[] fields, String login){
//        try{
//            Float weight=null;
//            try{
//                weight=Float.valueOf(fields[6]);}
//            catch (NullPointerException e){
//            }
//
//            id=0l;
//            name = fields[0];
//            coordinates = new Coordinates(Integer.parseInt(fields[1]), Float.valueOf(fields[2]));
//            price = Double.parseDouble(fields[3]);
//            creationDate = new Date();
//            unitOfMeasure=  UnitOfMeasure.valueOf(fields[4]);
//            owner = new Person(login, fields[5], weight, Country.valueOf(fields[9]));
//            this.login =login;
//
//        } catch (Exception e){
//            System.out.println("Криворукий даун");
//        }
//    }
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }


    public String getName() {
        return name;
    }

    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }
    @Override
    public String toString(){
        return "ID: "+this.getId()+"; Название продукта "+this.getName()+
                "; логин владельца "+login+"; Цена "+this.getPrice()+"; Дата создания "+this.creationDate+"; Еденица Измерения "+this.getUnitOfMeasure()+"; координаты "+this.getCoordinates();
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Product))return false;
        Product product=(Product) obj;
        if (Objects.equals(this.id, product.getId())) return true;
        else return false;
    }
    @Override
    public int compareTo(Product p){
        return (int) (this.id - p.id);
    }
//    public String ForInsert(){
//        return "'"+name+"', ('"+coordinates.getX()+"', '"+coordinates.getY()+"'),'"+creationDate+"' ,'"+price+"', '"+unitOfMeasure+"', ('"+owner.getName()+"', '"+owner.getWeight()+"', '"+owner.EyeColorToStr()+"', '"+owner.getHairColor()+"', '"+owner.getNationality()+"', ('"+owner.getLocation().getX()+"', '"+owner.getLocation().getY()+"', '"+owner.getLocation().getZ()+"')), '"+login+"'";
//    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getStringCoordinates(){
        return coordinates.getX()+","+coordinates.getY();
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public ProductType getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }
}