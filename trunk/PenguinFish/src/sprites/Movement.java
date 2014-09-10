package sprites;

/**
 * Describes all the types of movement available to enemies and movable objects.
 * 
 * @author Cameron A. Craig
 * @since September 2014
 *
 */
public enum Movement {
/**
 * A square movement in a clockwise direction.
 */
SQUARE(),
/**
 * A constant left then right movement.
 */
RECIPROCATING(),
/**
 * A complex circular movement, anti clockwise.
 */
CIRCULAR();
}
