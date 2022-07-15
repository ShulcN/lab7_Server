package Given;

public enum ProductType {
    INSTRUMENTS("instruments", "com/example/lab8/icons/Instruments.png"),
    DEFAULT("default", "com/example/lab8/icons/box.png"),
    FOODSTUFF("foodstuff", "com/example/lab8/icons/products.png"),
    ELECTRONIC("electronic", "com/example/lab8/icons/box.png"),
    FURNITURE("furniture", "com/example/lab8/icons/box.png")
    ;
    String description;
    String imgPath;
    ProductType(String s, String imgPath) {
        description=s;
        this.imgPath=imgPath;
    }

    public String getDescription() {
        return description;
    }

    public String getImgPath() {
        return imgPath;
    }
}
