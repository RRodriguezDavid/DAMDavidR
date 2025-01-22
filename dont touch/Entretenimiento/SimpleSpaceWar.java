package proyectosextra;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SimpleSpaceWar extends JPanel implements ActionListener, KeyListener {
    // Parámetros de la ventana del juego
    private final int screenWidth = 800;
    private final int screenHeight = 600;
    private final Timer timer;

    // Configuración de la nave
    private final Ship ship1 = new Ship(100, 300, Color.CYAN);
    private final Ship ship2 = new Ship(700, 300, Color.RED);

    private final ArrayList<Bullet> bullets = new ArrayList<>();

    public SimpleSpaceWar() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibujar naves y balas
        ship1.draw(g);
        ship2.draw(g);
        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actualizar naves y balas
        ship1.move();
        ship2.move();

        // Actualizar y eliminar balas fuera de la pantalla
        bullets.removeIf(bullet -> {
            bullet.move();
            return bullet.x < 0 || bullet.x > screenWidth || bullet.y < 0 || bullet.y > screenHeight;
        });

        // Colisiones entre naves y balas
        for (Bullet bullet : bullets) {
            if (bullet.shooter != ship1 && ship1.getBounds().intersects(bullet.getBounds())) {
                ship1.hit();
            }
            if (bullet.shooter != ship2 && ship2.getBounds().intersects(bullet.getBounds())) {
                ship2.hit();
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        // Controles de Ship 1
        if (key == KeyEvent.VK_W) ship1.accelerate();
        if (key == KeyEvent.VK_S) ship1.decelerate();
        if (key == KeyEvent.VK_A) ship1.rotateLeft();
        if (key == KeyEvent.VK_D) ship1.rotateRight();
        if (key == KeyEvent.VK_SPACE) bullets.add(ship1.shoot());

        // Controles de Ship 2
        if (key == KeyEvent.VK_UP) ship2.accelerate();
        if (key == KeyEvent.VK_DOWN) ship2.decelerate();
        if (key == KeyEvent.VK_LEFT) ship2.rotateLeft();
        if (key == KeyEvent.VK_RIGHT) ship2.rotateRight();
        if (key == KeyEvent.VK_ENTER) bullets.add(ship2.shoot());
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple SpaceWar");
        SimpleSpaceWar game = new SimpleSpaceWar();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

// Clase para representar una nave
class Ship {
    double x, y, angle;
    double speed = 0;
    double acceleration = 0.1;
    double maxSpeed = 5;
    int width = 20, height = 20;
    Color color;

    public Ship(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int[] xPoints = {
                (int) (x + width * Math.cos(angle)),
                (int) (x + width * Math.cos(angle + 2 * Math.PI / 3)),
                (int) (x + width * Math.cos(angle - 2 * Math.PI / 3))
        };
        int[] yPoints = {
                (int) (y + height * Math.sin(angle)),
                (int) (y + height * Math.sin(angle + 2 * Math.PI / 3)),
                (int) (y + height * Math.sin(angle - 2 * Math.PI / 3))
        };
        g.fillPolygon(xPoints, yPoints, 3);
    }

    public void move() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);

        if (x < 0) x = 800;
        if (x > 800) x = 0;
        if (y < 0) y = 600;
        if (y > 600) y = 0;
    }

    public void rotateLeft() {
        angle -= 0.1;
    }

    public void rotateRight() {
        angle += 0.1;
    }

    public void accelerate() {
        if (speed < maxSpeed) speed += acceleration;
    }

    public void decelerate() {
        if (speed > -maxSpeed) speed -= acceleration;
    }

    public Bullet shoot() {
        return new Bullet(this, x + width * Math.cos(angle), y + height * Math.sin(angle), angle);
    }

    public void hit() {
        x = Math.random() * 800;
        y = Math.random() * 600;
        speed = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x - width / 2, (int) y - height / 2, width, height);
    }
}

// Clase para representar una bala
class Bullet {
    double x, y, angle;
    double speed = 8;
    int size = 4;
    Ship shooter;

    public Bullet(Ship shooter, double x, double y, double angle) {
        this.shooter = shooter;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval((int) x - size / 2, (int) y - size / 2, size, size);
    }

    public void move() {
        x += speed * Math.cos(angle);
        y += speed * Math.sin(angle);
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x - size / 2, (int) y - size / 2, size, size);
    }
}
