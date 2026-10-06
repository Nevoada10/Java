package A010Player.PlayerEnum;

/**
 * Sex.java
 * Enum representing the sex of a player
 * Can only have three possible values: MAN, WOMAN, or NOTDEFINED
 * @author  Uriel Neves Silva (https://gitlab.com/a253119un)
 * 
 * An enum is a custom data type (not a String) that defines a fixed and limited
 * set of allowed values. It works like a dropdown menu in code: you can only
 * choose one of the predefined options, which prevents invalid data, typos,
 * and logical errors. Enums make code safer, clearer, and easier to maintain
 * by ensuring that variables can never hold unexpected values.
 */
public enum Sex {
    MAN,
    WOMAN,
    NOTDEFINED;
}