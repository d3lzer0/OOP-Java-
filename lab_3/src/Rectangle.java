import java.awt.*;

public class Rectangle {
	public short x1, y1, x2, y2;
		Color inColor = Color.RED, outColor = Color.WHITE;
	
	public Rectangle(short x1, short y1, short x2, short y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public Rectangle(short w, short h) {
		x1 = 0;
		y1 = 0;
		x2 = w;
		y2 = h;
	}
	
	public Rectangle() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
	}
	
	public void rect_print() {
		System.out.println("\n(x1, y1) = (" + x1 + ", " + y1 + ")\n(x2, y2) = (" + x2 + ", " + y2 + ")");
	}
	
	public void draw(Graphics g) {
		g.setColor(inColor);
		g.fillOval(x1, y1, x2, y2);
		g.setColor(outColor);
		g.drawOval(x1, y1, x2, y2);
	}
	
	public void drawColoredRect(Graphics g) {
		g.setColor(inColor);
		g.fillOval(x1, y1, x2, y2);
	}
	
	public void drawDrawableRect(Graphics g) {
		g.setColor(outColor);
		g.drawOval(x1, y1, x2, y2);
	}
	
	public void move(short dx, short dy) {
		x1 += dx;
		y1 += dy;
		x2 += dx;
		y2 += dy;
	}
	
	
	
	public Rectangle Union(Rectangle r) {
		short _x1 = this.x1, _y1 = this.y1, _x2 = this.x2, _y2 = this.y2;
		if ((r.x1 >= _x1) & (r.x1 <= _x2) & (r.y1 >= _y1) & (r.y1 <= _y2)) {
			_x1 = r.x1;
			_y1 = r.y1;
			if (r.x2 >= x2) {
				_x2 = x2;
			} else {
				_x2 = r.x2;
			}
			if (r.y2 >= y2) {
				_y2 = y2;
			} else {
				_y2 = r.y2;
			}
		} else {
			_x1 = _x2 = _y1 = _y2 = 0;
			System.out.println("Нет объединения.");
		}
		return new Rectangle(_x1, _y1, _x2, _y2);
	}
	
}

class DrawableRect extends Rectangle {
	Color outColor = Color.GREEN;
	public DrawableRect(short x1, short y1, short x2, short y2) {
		super(x1, y1, x2, y2);
	}
	
	public void draw(Graphics g) {
		g.setColor(outColor);
		g.drawRect(x1, y1, x2, y2);
	}
}

class ColoredRect extends DrawableRect {
	Color inColor = Color.RED;
	public ColoredRect(short x1, short y1, short x2, short y2) {
		super(x1, y1, x2, y2);
	}
	
	public void draw(Graphics g) {
		g.setColor(inColor);
		g.fillRect(x1, y1, x2, y2);
		g.setColor(outColor);
		g.drawRect(x1, y1, x2, y2);
	}
}