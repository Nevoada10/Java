package edt09_abstract_role;
/**
 * subclass of Role
 */
public class Outlander extends Role {

    public Outlander(float posX, float posY) {
        super(posX, posY);
    }

    /**
     * Makes the outlander jump up (Y axis) if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void jump() {
        if (isAvailable() && getEnergy() >= Utils.ENERGY_JUMP) {
            setPosY(getPosY() + Utils.OUTLANDER_JUMP);
            setEnergy(getEnergy() - Utils.ENERGY_JUMP);
        }
    }

    /**
     * Makes the outlander move right in the X-axis if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void move() {
        if (isAvailable() && getEnergy() >= Utils.EXTRA_ENERGY_MOVE) {
            setPosX(getPosX() + Utils.OUTLANDER_MOVE);
            setEnergy(getEnergy() - Utils.ENERGY_MOVE);
        }
    }
}
