import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Car {
    private int xA, yA;
    private int xB, yB;
    private Color color;
    private int carHeight, carWidth;
    public boolean arrived = false;
    public boolean blocked = false;
    public boolean atStart = true;
    public double angle ;
    public double angularSpeed;
    public int translationSpeed;
    private boolean moving;
    private boolean turning;

    public Car(int xA, int yA, int carHeight, int carWidth, Color color) {
        this.xA = xA;
        this.yA = yA;
        this.xB = xA;
        this.yB = yA + carHeight;
        this.carHeight = carHeight;
        this.carWidth = carWidth;
        this.color = color;
        this.angle = newAngle();
        this.angularSpeed = 0;
        this.translationSpeed = 0;
        this.moving = false;
        this.turning = false;
    }

    @Override
    public String toString() {
        return "Car [xA=" + xA + ", yA=" + yA + ", xB=" + xB + ", yB=" + yB + ", carHeight=" + carHeight + ", carWidth=" + carWidth + "]";
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(xA + carWidth, yA + carHeight);
        g2d.rotate(-this.angle);
        g2d.setColor(color);
        g2d.fillRect(-carWidth, -carHeight, carWidth, carHeight);
        g2d.setTransform(originalTransform);
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isTurning() {
        return turning;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setTurning(boolean turning) {
        this.turning = turning;
    }

    public void update(){
        if(turning){
            this.turn();
        }
        if(moving){
            this.translate();
        }
    }

    public void rotate(double alpha) {
        this.angle = alpha;
    }

    public int getXA() {
        return xA;
    }

    public void setXA(int x) {
        this.xA = x;
    }

    public int getYA() {
        return yA;
    }

    public void setYA(int y) {
        this.yA = y;
    }

    public double newAngle(){
        if(this.xA == this.xB){
            return 0;
        } else {
            return Math.atan((this.yB - this.yA)/(this.xB - this.xA));
        }
    }

    public void turn(){
        this.angle += this.angularSpeed;
        this.rotate(this.angle);
    }

    public void setAngularSpeed(double newAngularSpeed){
        this.angularSpeed = newAngularSpeed;
    }

    public void translate() {
        int newX = this.xA + (int)(this.translationSpeed * Math.sin(angle));
        int newY = this.yA + (int)(this.translationSpeed * Math.cos(angle));
        this.setXA(newX);
        this.setYA(newY);
    }

    public void setTranslationSpeed(int newSpeed){
        this.translationSpeed = newSpeed;
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
