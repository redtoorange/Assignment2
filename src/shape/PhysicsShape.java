package shape;

/**
 * PhysicsShape.java - A Shape that should have a X and Y velocity that can be applied to it each update.
 *
 * @author Andrew McGuiness
 * @version 10/12/2017
 */
public interface PhysicsShape {
    /** Apply the shape's velocity to it's position.  This applies a single time step. */
    void applyMovement();

    /**
     * Set the Shape's current X and Y Velocity.
     *
     * @param velocityX new X Velocity
     * @param velocityY new Y Velocity
     */
    void setVelocity( int velocityX, int velocityY );

    /**
     * Get the shape's current X velocity.
     *
     * @return X velocity
     */
    int getVelocityX();

    /**
     * Get the shape's current Y velocity.
     *
     * @return Y velocity
     */
    int getVelocityY();
}
