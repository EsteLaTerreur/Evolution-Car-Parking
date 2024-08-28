import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class miniSimulation {
    private int x, y;
    private int size;
    private int carSize;
    private Color carColor;
    private Color wallColor;
    private RectangleBox rectangleBox;
    private ArrayList<Car> parkedCars;

    public miniSimulation(int x, int y, int size, Color wallColor, Color carColor) {
        // dimensioning
        this.x = x;
        this.y = y;
        this.size = size;
        this.carSize = size/4;
        this.wallColor = wallColor;
        this.carColor = carColor;
        // parking
        this.parkedCars = new ArrayList<>();
        this.parkedCars.add(new Car(x + 2*4*size/3/5, y + size/16, carSize, carSize*2/3, carColor));
        this.parkedCars.add(new Car(x + 2*4*size/3/5, y + 11*size/16, carSize, carSize*2/3, carColor));
        // walls
        this.rectangleBox = new RectangleBox(x, y, size, wallColor);
    }

    public void draw(Graphics g) {
        rectangleBox.draw(g);
        for(Car c : parkedCars){
            c.draw(g);
        }
    }
}
