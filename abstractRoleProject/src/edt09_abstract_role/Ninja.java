package edt09_abstract_role;
/**
 * @author Uriel Neves Silva
 * The ninja class is a subclass of Role
 */
public class Ninja extends Role {

    public Ninja(float posX, float posY) {
        super(posX, posY);
    }

    /**
     * Makes the Ninja jump up (Y axis) if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void jump() {
        if (isAvailable() && getEnergy() >= Utils.ENERGY_JUMP) {
            setPosY(getPosY() + Utils.NINJA_JUMP);
            setEnergy(getEnergy() - Utils.ENERGY_JUMP);
        }
    }

    /**
     * Makes the Ninja move right in the X-axis if he has available actions and enough energy
     * Uses the setter so It's not necessary to test it.
     */
    @Override
    public void move() {
        if (isAvailable() && getEnergy() >= Utils.EXTRA_ENERGY_MOVE) {
            setPosX(getPosX() + Utils.NINJA_MOVE);
            setEnergy(getEnergy() - Utils.ENERGY_MOVE);
        }
    }

}
