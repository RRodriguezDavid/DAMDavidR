package proyectosextra;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SimpleMarioGame extends JPanel implements ActionListener, KeyListener {
    // Configuración inicial del jugador
    private int playerX = 50;               // Posición X inicial del jugador
    private int playerY = 250;              // Posición Y inicial del jugador
    private int playerWidth = 30;           // Ancho del jugador
    private int playerHeight = 40;          // Altura del jugador
    private int groundLevel = 300;          // Nivel del suelo
    private boolean isJumping = false;      // Bandera de salto
    private int jumpVelocity = 15;          // Velocidad inicial de salto
    private int currentJumpVelocity = 0;    // Velocidad de salto actual
    private int gravity = 1;                // Gravedad
    private boolean goingLeft = false;
    private boolean goingRight = false;

    // Configuración de plataformas
    private ArrayList<Rectangle> platforms;

    private Timer timer;

    public SimpleMarioGame() {
        timer = new Timer(10, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);

        // Inicialización de plataformas
        platforms = new ArrayList<>();
        platforms.add(new Rectangle(150, 220, 100, 10));  // Plataforma 1
        platforms.add(new Rectangle(300, 180, 100, 10));  // Plataforma 2
        platforms.add(new Rectangle(500, 250, 100, 10));  // Plataforma 3
        platforms.add(new Rectangle(650, 150, 100, 10));  // Plataforma 4
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Fondo
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Suelo
        g.setColor(Color.GREEN);
        g.fillRect(0, groundLevel, getWidth(), getHeight() - groundLevel);

        // Jugador (representado por un rectángulo)
        g.setColor(Color.RED);
        g.fillRect(playerX, playerY, playerWidth, playerHeight);

        // Plataformas
        g.setColor(Color.GRAY);
        for (Rectangle platform : platforms) {
            g.fillRect(platform.x, platform.y, platform.width, platform.height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Movimiento horizontal
        if (goingLeft && playerX > 0) {
            playerX -= 5;
        }
        if (goingRight && playerX < getWidth() - playerWidth) {
            playerX += 5;
        }

        // Lógica de salto con gravedad
        if (isJumping) {
            playerY -= currentJumpVelocity;
            currentJumpVelocity -= gravity;

            // Chequeo de colisión con la parte inferior de las plataformas
            for (Rectangle platform : platforms) {
                if (playerX + playerWidth > platform.x && playerX < platform.x + platform.width &&
                        playerY < platform.y + platform.height && playerY + playerHeight > platform.y) {
                    // Detiene el salto al colisionar con la parte inferior de una plataforma
                    playerY = platform.y + platform.height;
                    currentJumpVelocity = 0;
                    isJumping = false;
                }
            }

            // Si la velocidad de salto se agota, el personaje empieza a caer
            if (currentJumpVelocity <= 0) {
                isJumping = false;
            }
        } else {
            // Caída por gravedad
            playerY += currentJumpVelocity;
            currentJumpVelocity += gravity;

            // Chequeo de colisión con plataformas desde arriba
            boolean onPlatform = false;
            for (Rectangle platform : platforms) {
                if (playerY + playerHeight >= platform.y && playerY + playerHeight <= platform.y + platform.height &&
                        playerX + playerWidth > platform.x && playerX < platform.x + platform.width) {
                    playerY = platform.y - playerHeight;
                    onPlatform = true;
                    currentJumpVelocity = 0;
                    break;
                }
            }

            // Si no está sobre ninguna plataforma, cae al suelo
            if (!onPlatform && playerY + playerHeight >= groundLevel) {
                playerY = groundLevel - playerHeight;
                currentJumpVelocity = 0;
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goingLeft = true;
        }
        if (key == KeyEvent.VK_RIGHT) {
            goingRight = true;
        }
        // Permitir saltar desde el suelo o desde plataformas
        if (key == KeyEvent.VK_UP && !isJumping && (playerY + playerHeight >= groundLevel || isOnPlatform())) {
            isJumping = true;
            currentJumpVelocity = jumpVelocity;
        }
    }

    private boolean isOnPlatform() {
        for (Rectangle platform : platforms) {
            if (playerY + playerHeight == platform.y && playerX + playerWidth > platform.x && playerX < platform.x + platform.width) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            goingLeft = false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            goingRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Mario Game");
        SimpleMarioGame game = new SimpleMarioGame();
        frame.add(game);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}