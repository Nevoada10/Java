package music;

import music.pitch.NoteMapper;

public class App {
    public static void main(String[] args) {
        // A4 should be exactly 0 cents
        System.out.println(NoteMapper.map(440.0));
        // C4 (middle C) = 261.63 Hz
        System.out.println(NoteMapper.map(261.63));
        // Slightly sharp A4
        System.out.println(NoteMapper.map(445.0));
        // E2 = 82.41 Hz
        System.out.println(NoteMapper.map(82.41));

        // G2 = 98.00 Hz
        System.out.println(NoteMapper.map(98.00));

        // G3 = 196.00 Hz
        System.out.println(NoteMapper.map(196.00));
        
        // G4 = 392.00 Hz
        System.out.println(NoteMapper.map(392.00));
    }
}