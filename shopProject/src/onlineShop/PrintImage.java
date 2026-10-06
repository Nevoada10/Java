package onlineShop;

public enum PrintImage {
    TIGER,
    BULL,
    EAGLE,
    REINDEER,
    COBRA;

    public String printImage() {
        return this.name();
    }
}