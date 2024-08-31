import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class miniSimulation {
    private int x, y;
    private int height;
    private int width;
    private int carHeight;
    private int carWidth;
    private Color parkedCarColor;
    private Color movingCarColor;
    private Color wallColor;
    private RectangleBox rectangleBox;
    private ArrayList<Car> parkedCars;
    private Car movingCar;
    private int startX;
    private int startY;

    public miniSimulation(int x, int y, int height, int width, Color wallColor, Color parkedCarColor, int carHeight, int carWidth, Color movingCarColor) {
        // dimensioning
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.wallColor = wallColor;
        this.parkedCarColor = parkedCarColor;
        this.movingCarColor = movingCarColor;
        // parking
        this.parkedCars = new ArrayList<>();
        this.parkedCars.add(new Car(x + 2*8*height/3/9, y + width/16, carHeight, carWidth, parkedCarColor));
        this.parkedCars.add(new Car(x + 2*8*height/3/9, y + 11*height/16, carHeight, carWidth, parkedCarColor));
        // main car
        this.startX = x + height/3;
        this.startY = y + height/16;
        this.movingCar = new Car(startX, startY, carHeight, carWidth, movingCarColor);
        // walls
        this.rectangleBox = new RectangleBox(x, y, height, wallColor);
    }

    public Car getMovingCar() {
        return movingCar;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    @Override
    public String toString() {
        return "miniSimulation [parkedCars=" + parkedCars + "]";
    }

    public void draw(Graphics g) {
        rectangleBox.draw(g);
        for(Car c : parkedCars){
            c.draw(g);
        }
        movingCar.draw(g);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public Color getParkedCarColor() {
        return parkedCarColor;
    }

    public Color getWallColor() {
        return wallColor;
    }
}
