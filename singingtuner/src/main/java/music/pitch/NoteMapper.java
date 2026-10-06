package music.pitch;

public class NoteMapper {

    // The 12 note names in order starting from A
    private static final String[] NOTE_NAMES = {
        "A", "A#", "B", "C", "C#", "D",
        "D#", "E", "F", "F#", "G", "G#"
    };

    // A4 = 440 Hz is our reference point
    private static final double REFERENCE_FREQ = 440.0;
    private static final int    REFERENCE_OCTAVE = 4;

    /**
     * Represents the result of mapping a frequency to a note.
     */
    public static class NoteResult {
        public final String name;    // e.g. "G#"
        public final int    octave;  // e.g. 4
        public final double cents;   // e.g. -12.3 means 12.3 cents flat

        public NoteResult(String name, int octave, double cents) {
            this.name   = name;
            this.octave = octave;
            this.cents  = cents;
        }

        @Override
        public String toString() {
            String direction = cents >= 0 ? "sharp" : "flat";
            return String.format("%s%d  (%.1f cents %s)",
                name, octave, Math.abs(cents), direction);
        }
    }

    /**
     * Maps a frequency in Hz to the nearest musical note.
     * Returns null if the frequency is invalid (<=0).
     */
    public static NoteResult map(double frequencyHz) {
        if (frequencyHz <= 0) return null;

        // How many semitones away from A4?
        double semitonesFromA4 = 12.0 * (Math.log(frequencyHz / REFERENCE_FREQ) / Math.log(2));

        // Round to nearest semitone
        int nearestSemitone = (int) Math.round(semitonesFromA4);

        // Cents deviation from that nearest semitone (-50 to +50)
        double cents = (semitonesFromA4 - nearestSemitone) * 100.0;

        // Note name: cycle through NOTE_NAMES using modulo
        // +12 before modulo avoids negative index issues in Java
        int noteIndex = ((nearestSemitone % 12) + 12) % 12;
        String noteName = NOTE_NAMES[noteIndex];

        // Octave: A4 is our anchor, C is the start of each octave
        // We need to account for the fact that C is 3 semitones below A
        int octave = REFERENCE_OCTAVE + (int) Math.floor((nearestSemitone + 9.0) / 12.0);

        return new NoteResult(noteName, octave, cents);
    }
}