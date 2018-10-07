#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>

/*
Adam Iannaci, Ian Gerics
*/

int main(int argc, char *argv[]){

        int readWrite[2];
        pid_t child; 
        FILE *file, *file2;
        char readBuffer[100000];
        char fileBuffer[100000]; 


        pipe(readWrite); 

        child = fork();

        if(child < 0){
                perror("Fork error\n");
                exit(1);
        }

        if(child == 0){
                

                close(readWrite[0]); 
                
                file = fopen(argv[1], "r");
	

		while(fgets(fileBuffer, sizeof(fileBuffer), file) != NULL){

			write(readWrite[1], fileBuffer, strlen(fileBuffer)); 
		}

		fclose(file);

        }else{
                
                close(readWrite[1]); 
                
		file2 = fopen(argv[2], "w");
		
		               
		read(readWrite[0], readBuffer, sizeof(readBuffer));

		fprintf(file2, "%s", readBuffer);
				
		fclose(file2);

        }

}
