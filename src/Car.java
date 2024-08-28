import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Car {
    private int x, y;
    private Color color;
    private int carHeight, carWidth;
    public boolean arrived = false;
    public boolean blocked = false;
    public boolean atStart = true;

    public Car(int x, int y, int carHeight, int carWidth, Color color) {
        this.x = x;
        this.y = y;
        this.carHeight = carHeight;
        this.carWidth = carWidth;
        this.color = color;
    }

    // public void update() {
    //     x += vx;
    //     y += vy;
    // }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, carWidth, carHeight);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // public int getVx() {
    //     return vx;
    // }

    // public void setVx(int vx) {
    //     this.vx = vx;
    // }

    // public int getVy() {
    //     return vy;
    // }

    // public void setVy(int vy) {
    //     this.vy = vy;
    // }

    public int getWidth() {
        return carWidth;
    }

    public void setWidth(int width) {
        this.carWidth = width;
    }
    
    public int getHeight() {
        return carHeight;
    }

    public void setHeight(int height) {
        this.carHeight = height;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    // public boolean isArrived(CustomLine endLine){
    //     if(endLine.getEndX() - 2 <= this.x && this.x <= endLine.getEndX() + 2){
    //         this.arrived = true;
    //         this.setX(endLine.getEndX());
    //         this.setVx(0);
    //     }
    //     return this.arrived;
    // }

    // @Override
    // public String toString() {
    //     return "Particule [x=" + x + ", y=" + y + ", vx=" + vx + ", arrived=" + arrived + ", blocked=" + blocked + ", atStart="
    //             + atStart + "]";
    // }

    // public boolean isBlocked(ArrayList<CustomLine> walls){
    //     for(CustomLine wall : walls){
    //         if(wall.getStartX() - 2 <= this.x && this.x <= wall.getStartX() + 2){
    //             if(wall.getStartY() <= this.y && this.y <= wall.getEndY()){
    //                 this.setX(wall.getStartX());
    //                 this.setVx(0);
    //                 this.blocked = true;
    //             }
    //         }
    //     }
    //     return this.blocked;
    // }
}
