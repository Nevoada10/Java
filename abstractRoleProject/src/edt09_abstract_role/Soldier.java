package edt09_abstract_role;

/**
 * @author Uriel Neves Silva
 * The soldier class is a subclass of Role
 */
public class Soldier extends Role {

    public Soldier(float posX, float posY) {
        super(posX, posY);
    }

    /**
     * Makes the Soldier jump up (Y axis) if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void jump() {
        if (isAvailable() && getEnergy() >= Utils.ENERGY_JUMP) {
            setPosY(getPosY() + Utils.SOLDIER_JUMP);
            setEnergy(getEnergy() - Utils.ENERGY_JUMP);
        }
    }

    /**
     * Makes the Soldier move right in the X-axis if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void move() {
        if (isAvailable() && getEnergy() >= Utils.EXTRA_ENERGY_MOVE) {
            setPosX(getPosX() + Utils.SOLDIER_MOVE);
            setEnergy(getEnergy() - Utils.ENERGY_MOVE);
        }
    }
}
