/**
 * Physics.java - Description
 *
 * @author Andrew McGuiness
 * @version 10/12/2017
 */
public interface Physics {
    void applyMovement();

    void setVelocity(int x, int y);

    int getVelocityX();

    int getVelocityY();
}
