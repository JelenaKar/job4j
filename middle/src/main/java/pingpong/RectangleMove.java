package pingpong;


import javafx.scene.shape.Rectangle;

/**
 * Класс, описывающий движение прямоугольника.
 *
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version 1$
 * @since 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Rectangle rect, int limitX, int limitY) {
        this.rect = rect;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    @Override
    public void run() {
        int deltaX = 3;
        while (!Thread.currentThread().isInterrupted()) {
            this.rect.setX(this.rect.getX() + deltaX);
            if (this.rect.getX() >= (this.limitX - this.rect.getWidth()) || this.rect.getX() <= 0) {
                deltaX *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
