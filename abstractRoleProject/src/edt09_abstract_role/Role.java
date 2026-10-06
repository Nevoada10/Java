package edt09_abstract_role;
/**
 * Role.java
 * Most important class in the project
 */
public abstract class Role {
        
        //Attributes
        protected float posX;
        protected float posY;
        protected boolean available;
        protected byte lifes;
        protected float energy = 100f;

        public Role(float posX, float posY) {
            this.posX = posX;
            this.posY = posY;
            this.available = true;
            this.lifes = Utils.LIFES;
            this.energy = Utils.INITIAL_ENERGY;
        }

        //Abstract methods
        public abstract void move();
        public abstract void jump();

        //Methods
        public float getPosX() {
            return posX;
        }

        public void setPosX(float posX) {
            this.posX = posX;
        }

        public float getPosY() {
            return posY;
        }

        public void setPosY(float posY) {
            this.posY = posY;
        }

        public boolean isAvailable() {
            return available;
        }

        public void setAvailable(boolean available) {
            this.available = available;
        }

        public byte getLifes() {
            return lifes;
        }

        public void setLifes(byte lifes) {
            this.lifes = lifes;
        }

        public float getEnergy() {
            return energy;
        }

        public void setEnergy(float energy) {
            this.energy = energy;
        }

        @Override
        public String toString() {
            return "Role{" + this.getClass().getSimpleName() +
                    "posX=" + getPosX() +
                    ", posY=" + getPosY() +
                    ", available=" + isAvailable() +
                    ", lifes=" + getLifes() +
                    ", energy=" + getEnergy() +
                    "}";
        }


    /**
     * Applies an extra move of 20,5 units on X.
     * Costs extra energy. Requires available and enough energy.
     */
        public void extraMove() {
            if (isAvailable() && getEnergy() >= Utils.EXTRA_ENERGY_MOVE) {
                setPosX(getPosX() + Utils.EXTRA_MOVE);
                setEnergy(getEnergy() - Utils.EXTRA_ENERGY_MOVE);
            }
        }

        /**
         * Applies an extra jump of 25,2 units on Y.
         * Costs extra energy. Requires available and enough energy.
         */
        public void extraJump() {
            if (isAvailable() && getEnergy() >= Utils.EXTRA_ENERGY_JUMP) {
                setPosY(getPosY() + Utils.EXTRA_JUMP);
                setEnergy(getEnergy() - Utils.EXTRA_ENERGY_JUMP);
            }
        }

    /**
     * Resets the position of the role to (0, 0).
     */
    public void resetPosition() {
        this.posX = 0;
        this.posY = 0;
    }


    /**
     * Resets the role to its initial state.
     * Resets position, availability, lifes, and energy.
     * Calls resetPosition() to reset the position.
     */
    public void resetRole() {
        resetPosition();
        setAvailable(true);
        setLifes(Utils.LIFES);
        setEnergy(Utils.INITIAL_ENERGY);
    }

    /**
     * Decreases the life of the role by 1.
     * Requires the role to be available and have at least 1 life.
     * @return true if the life was decreased, false otherwise
     */
    public boolean decreaseLife() {
       if (isAvailable() && getLifes() > 0) {
           setLifes((byte) (getLifes() - 1));
           return true;
       }
       return false;
    }

    /**
     * Increases the life of the role by 1.
     * Requires the role to be available and have less than 3 lives.
     * @return true if the life was increased, false otherwise
     */
    public boolean increaseLife() {
        if (isAvailable() && getLifes() < 3) {
            setLifes((byte) (getLifes() + 1));
            return true;
        }
        return false;
    }        
}
