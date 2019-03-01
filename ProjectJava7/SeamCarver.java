import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;
    // Create a seam carver object based on the given picture, making a
    // defensive copy of picture.
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture);
    }
    // Current picture.
    public Picture picture() {
        return picture;
    }
    // Width of current picture.
    public int width() {
        return picture.width();
    }
    // Height of current picture.
    public int height() {
        return picture.height();
    }
    // Energy of pixel at column x and row y.
    public double energy(int x, int y) {
        double energy = 0;
        Color N = picture.get(x, wrap(y - 1, height()));
        Color E = picture.get(wrap(x + 1, width()), y);
        Color W = picture.get(wrap(x - 1, width()), y);
        Color S = picture.get(x, wrap(y + 1, height()));

        double r = (N.getRed() - S.getRed()) * (N.getRed() - S.getRed());
        double g = (N.getGreen() - S.getGreen()) * (N.getGreen() - S.getGreen());
        double b = (N.getBlue() - S.getBlue()) * (N.getBlue() - S.getBlue());

        energy += (r + g + b);
        r = (E.getRed() - W.getRed()) * (E.getRed() - W.getRed());
        g = (E.getGreen() - W.getGreen()) * (E.getGreen() - W.getGreen());
        b = (E.getBlue() - W.getBlue()) * (E.getBlue() - W.getBlue());
        energy += (r + g + b);
        return energy;
    }
    // Sequence of indices for horizontal seam.
    public int[] findHorizontalSeam() {
        SeamCarver seamC = new SeamCarver(transpose(this.picture));
        return seamC.findVerticalSeam();
    }
    // Sequence of indices for vertical seam.
    public int[] findVerticalSeam() {
        int[] seam = new int[height()];
        double sPath = Double.POSITIVE_INFINITY;
        int shorTint = -1;
        double [][] distTo = new double[height()][width()];
        for (int i = 0; i < width(); i++) {
            distTo[0][i] = energy(i, 0);
        }
        //Calculating distTo and finding shortest
        for (int j = 1; j < height(); j++) {
            for (int i = 0; i < width(); i++) {
                double dist = Double.POSITIVE_INFINITY;
                //Corner Case (Top pixel)
                if (i - 1 >= 0
                        && distTo[j - 1][i - 1] + energy(i, j) < dist) {
                    dist = distTo[j - 1][i - 1] + energy(i, j);
                }
                //Corner Case (Body Pixel)
                if (distTo[j - 1][i] + energy(i, j) < dist) {
                    dist = distTo[j - 1][i] + energy(i, j);
                }
                // CornerCase (Bottom Pixel)
                if (i + 1 < width()
                        && distTo[j - 1][i + 1] + energy(i, j) < dist) {
                    dist = distTo[j - 1][i + 1] + energy(i, j);
                }
                distTo[j][i] = dist;
                if (j == height() - 1 && dist < sPath) {
                    sPath = dist;
                    shorTint = i;
                }
            }
        }
        seam[height() - 1] = shorTint;
        for (int j = height() - 2; j >= 0; j--) {
            double e = distTo[j + 1][shorTint] - energy(shorTint, j + 1);
            if (shorTint - 1 >= 0
                    && distTo[j][shorTint - 1] == e) {
                shorTint -= 1;
            }
            if (shorTint + 1 < width()
                    && distTo[j][shorTint + 1] == e) {
                shorTint += 1;
            }
            seam[j] = shorTint;
        }
        return seam;
    }
    // Remove horizontal seam from current picture.
    public void removeHorizontalSeam(int[] seam) {
        SeamCarver seamC = new SeamCarver(transpose(picture));
        seamC.removeVerticalSeam(seam);
        picture = transpose(seamC.picture());
    }
    // Remove vertical seam from current picture.
    public void removeVerticalSeam(int[] seam) {
        Picture temp =  new Picture(width() - 1, height());
        for (int i = 0; i < width(); i++) {
            for (int i2 = 0; i2 < height(); i2++) {
                if (i < seam[i2]) {
                    temp.set(i, i2, picture.get(i, i2));
                }
                if (i > seam[i2]) {
                    temp.set(i - 1, i2, picture.get(i, i2));
                }
            }
        }
        picture = temp;
    }
    // Return y - 1 if x < 0; 0 if x >= y; and x otherwise.
    private static int wrap(int x, int y) {
        if (x < 0) {
            return y - 1;
        } else if (x >= y) {
            return 0;
        }
        return x;
    }
    // Return a new picture that is a transpose of the given picture.
    private static Picture transpose(Picture picture) {
        Picture transpose = new Picture(picture.height(), picture.width());
        for (int i = 0; i < transpose.width(); i++) {
            for (int j = 0; j < transpose.height(); j++) {
                transpose.set(i, j, picture.get(j, i));
            }
        }
        return transpose;
    }
}
