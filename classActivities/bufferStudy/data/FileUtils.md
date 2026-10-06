// package fileutils


public class FileUtils{

	/**
	* @param fileIn: path of the file to read
	*/
	public void fileLlegir(String fileIn) {
	//TODO
		// Llegir linia a linia el fitxer
		// mostrar cada línia per stdout
		// Verificar:
		// 	java FileMain > text.out
		// 	diff ./data/text.txt text.out
	}

	/**
	* @param fileIn: path of the file to read
	* @return int number of lines
	*/
	public int fileNumLinies(String fileIn) {
	//TODO
		// llegir línia a línia el fitxer
		// usant un comptador comptar quantes línies té.
		// retornr el número total de línia
		// Verificar:
			javac FileMain 
			wc -l ./data/file.txt
	}

        /**
	* @param patro: string of pattern to search
        * @param fileIn: path of the file to read
        * @return int number of lines
        */
        public int fileLiniaConte(String patro, String fileIn) {
        //TODO
		// recórerr el fitxer de text línia a línia
		// si la línia conté la cadena (usar OBLIGATÒRIAMENT Sring.indexOf
			// mostrar la línia per staout
			// incrementar el comptador d'ocurrències
		// retornar el número d'ocurrències del patró al fitxer
		// Verificació:
			java FileMain > ./data/a.out
			grep patro ./data/file.txt > ./data/b.out
			diff ./data/a.out ./data/b.out
		// Verificació: 
			java FileMain 
			grep -c patro ./data/file.txt
	}

       /**
        * @param patro: string of pattern to search
        * @param fileIn: path of the file to read
        * @return int number of lines
        */
        public int fileConte(String patro, String fileIn) {
        //TODO
		// ATENCIÓ: no es permet l'ús de exit
		// recórrer el fitxer de text línia a línia
		// si existeix el patro en una línia (la primera que el contingui)
			//és OBLIGATORI usar String.indexOf
			deixar de recórrer
		// retorna: el número de línia que conté el patro o 0 si no el conté
		// Verificar:
			java FileMain 
			grep -n patro fileIn | head -n5 



      /**
        * @param fileIn: path of the file to read
		* @param fileOut: path to file to write
        * @return int number of lines
        */
        public int fileDuplica(String fileIn, String fileOut) {
        //TODO
		// Recórrer el fitxer de text linia a línia
		// desar cada línia al fitxer destí
		// retorna el número de línies processades
		// Verificar:
			java FileMain 
			cmp .data/text.txt ./data/text.outº:wq
	}

      /**
		* @param patro: string of patter to searh
        * @param fileIn: path of the file to read
        * @param fileOut: path to file to write
        * @return int number of lines
        */
        public int fileFiltraOut(String patro, String fileIn, String fileOut) {
        //TODO
		// recórre el fitxer línia a línia
		// per cada línia que conté el patró de recerca 
			desar la línia en el fitxer de sortida
		// retorna el total d'ocurrències (de línies)  del patró
		// Verificar:
			java FileMain
			grep patro ./data/text.txt > ./data/a.out
			diff ./data/text.out ./data/a.out
		// Verificar:
			grep -c patro ./data/text.txt
			wc -l ./data/text.out
}

