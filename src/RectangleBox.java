import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.util.ArrayList;

public class RectangleBox {
    private int x, y;
    private int size;
    private Color color;
    private ArrayList<CustomLine> lines ;

    public RectangleBox(int x, int y, int size, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        lines = new ArrayList<>();
        this.lines.add(new CustomLine(x, y, x+4*size/5, y));
        this.lines.add(new CustomLine(x, y, x, y+size));
        this.lines.add(new CustomLine(x, y+size, x+4*size/5, y+size));
        this.lines.add(new CustomLine(x+4*size/5, y, x+4*size/5, y+size));
    }

    public void draw(Graphics g) {
        g.setColor(color);
        for(CustomLine c : lines){
            c.draw(g);
        }
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
