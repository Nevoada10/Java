package onlineShop;

public enum Visor {
    FLAT,
    CURVED;

    private Visor() {
    }

    public String getVisor() {
        return this.name();
    }
}