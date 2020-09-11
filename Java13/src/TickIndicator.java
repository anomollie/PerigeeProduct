import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class TickIndicator extends Circle {
    private boolean isSelected;
    public TickIndicator(double radius, Paint color, boolean isSelected) {
        this.isSelected = isSelected;
        this.setRadius(radius);
        this.setFill(color);
        this.setStroke(Color.WHITE);
        this.setStrokeWidth(3);
    }
    public boolean checkSelected() {
        return this.isSelected;
    }
    public void expand() {
        this.setFill(Color.TRANSPARENT);
        this.setRadius(this.getRadius()*2);
        this.isSelected = true;
    }
    public void contract() {
        this.setFill(Color.WHITE);
        this.setRadius(this.getRadius()/2);
        this.isSelected = false;
    }
}
