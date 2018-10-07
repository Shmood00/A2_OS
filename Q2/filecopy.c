#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>

/*
Adam Iannaci, Ian Gerics

*****NOTE*****
Cristina, you told us to leave you a note about our program.
For some reason, our program will occasionally only copy over
the first line of the input file. For example, if my input file has:
test
1
2

The copy file will sometimes only contain "test" and not the full 
contents of the file.

*/

int main(int argc, char *argv[]){

        int readWrite[2]; /* Define array for pipe */
        pid_t child; /* Define process identifier */
        FILE *file, *file2;
        char readBuffer[100000]; /* Define buffer for reading */
        char fileBuffer[100000]; /* Define file buffer */

	if((argc-1) != 2){
		fprintf(stderr, "%s", "Error, must enter in 2 arguments.\n");
		fprintf(stderr, "%s", "Usage: filecopy arg1 arg2.\n");
		exit(1);
	}

        pipe(readWrite); /* Create pipe */

        child = fork(); /* Start process */

        if(child < 0){
                perror("Fork error\n");
                exit(1);
        }

        if(child == 0){
                /* Child */
                /* Writing */

                close(readWrite[0]); /* Close input side of pipe */
                
                file = fopen(argv[1], "r");
		
		if(file == NULL){
			fprintf(stderr, "%s", "File not found.\n");
		}else{

		        while(fgets(fileBuffer, sizeof(fileBuffer), file) != NULL){ /* Loop through file to read */

		                write(readWrite[1], fileBuffer, strlen(fileBuffer)); /* Write to pipe */

		        }

		        fclose(file); /* Close file */
		}

        }else{
                /* Parent */
                /* Reading */
                close(readWrite[1]); /* Close output side of pipe */
                
		file2 = fopen(argv[2], "w");
		
		if(file2 == NULL){
			fprintf(stderr, "%s", "Error opening file.\n");		
		}else{
		               
			read(readWrite[0], readBuffer, sizeof(readBuffer)); /* Read from pipe */

			fprintf(file2, "%s", readBuffer); /* Write to file */
				
			fclose(file2); /* Close file */
		}

        }

}
