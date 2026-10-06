
/**
 * This module contains the main class for the Notepad application.
 * It exports the notepadPackage package, which contains the Notepad class.
 */
module Notepad {
    /**
     * This line tells the module system that the java.desktop module is required
     * for the Notepad application to function correctly.
     * It is a dependency of the Notepad module.
     * So the module system will automatically load the java.desktop module
     * when the Notepad module is loaded.
     * 
     * What is the difference between a module and an import statement?
     * A module is a unit of code that can be loaded and executed by the module system.
     * An import statement is a statement that specifies a dependency on another module.
     */
    requires java.desktop; 

    /**
     * This line exports the notepadPackage package, which contains the Notepad class.
     * It is a dependency of the Notepad module.
     * So the module system will automatically load the notepadPackage package
     * when the Notepad module is loaded.
     */
    exports notepadPackage;
}