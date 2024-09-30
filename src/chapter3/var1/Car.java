package chapter3.var1;

public class Car {
    private int id;
    private String marka;
    private String model;
    private int releaseYear;
    private String color;
    private double price;
    private String regNomer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRegNomer() {
        return regNomer;
    }

    public void setRegNomer(String regNomer) {
        this.regNomer = regNomer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", marka='" + marka + '\'' +
                ", model='" + model + '\'' +
                ", releaseYear=" + releaseYear +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", regNomer='" + regNomer + '\'' +
                '}';
    }

}
