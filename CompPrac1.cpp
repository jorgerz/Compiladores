 /*
Renteria Zules Jorge Luis
Compiladores 
practica #1 
*/


#include <stdlib.h>
#include <conio.h>
#include <stdio.h>

int valida_letra(char,int );

int main (void)
{
	int i=0, aceptacion=0;
	char letra[25];
	char opc='A';
	
	printf("Ingresar palabra: ");
	scanf("%s",&letra);
	while(letra[i])
	{
		switch(opc)
		{
			case 'A':	if(letra[i]==105 && i==0)
							{opc='B'; aceptacion=1;}
							else if(letra[i]==46 && i==0)
								opc='E'; 
								else if((letra[i]== 45 || letra[i]==43)&& i==0)
									opc='F';
									else if((letra[i]>=48 && letra[i]<=57)&& i==0)
										{opc='G'; aceptacion=1;}
										else if(valida_letra(letra[i],105)==1)
											{opc='D'; aceptacion=1;}
											else 
												opc='A';
						break;
			case 'B':	if(letra[i]==102&& i==1)
							{opc='C'; aceptacion=1;}
							else if(valida_letra(letra[i],102 && i==1))
								{opc='D'; aceptacion=1;}
								else 
									{opc='B'; aceptacion=1;	}
						break;
			case 'C': 	if(letra[i]>= 97 && letra[i]<=127 || letra[i]>= 65 && letra[i]<=90|| letra[i]>=48 && letra[i]<=57 || letra[i]==95)
							{opc='D'; aceptacion=1;}
						break;
			case 'D':	if(letra[i]>= 97 && letra[i]<=127 || letra[i]>= 65 && letra[i]<=90|| letra[i]>=48 && letra[i]<=57)
							{opc='D'; aceptacion=1;}		
						break;
			case 'E': 	if(letra[i]>=48 && letra[i]<=57)
							{opc='H'; aceptacion=1;}
						break;
			case 'F':	if(letra[i]==46)
							{opc='E'; aceptacion=1;}
						if(letra[i]>=48&&letra[i]<=57)
							{opc='G'; aceptacion=1;}
						break;
			case 'G': 	if(letra[i]==46)
							{opc='H'; aceptacion=1;}
						if(letra[i]>=48&&letra[i]<=57)
							{opc='G'; aceptacion=1;}
						if(letra[i]==69 || letra[i]==101)
							{opc='I'; aceptacion=0;}
						break;
			case 'H':	if(letra[i]==69||letra[i]==101)
							{opc='I'; aceptacion=0;}
						if(letra[i]>=48&&letra[i]<=57)
							{opc='H'; aceptacion=1;}
						break;
			case 'I': 	if(letra[i]>=48&&letra[i]<=57)
							{opc='K'; aceptacion=1;}
						if(letra[i]== 45 || letra[i]==43)
							{opc='J'; aceptacion=0;}
						break;
			case 'J':	if(letra[i]>=48&&letra[i]<=57)
							{opc='K'; aceptacion=1;}
						break;
			case 'K': 	if(letra[i]>=48&&letra[i]<=57)
							{opc='K'; aceptacion=1;}
						break;
						
		}
		printf("Camino que siguio: %c",opc);
		if(aceptacion==1)
			printf("----Aceptacion \n");
		else 
			printf("----No aceptacion \n");
		i++;
	}
}

int valida_letra(char letra,int dato)
{
	int flag=0;
	
	if(letra>= 97 && letra<=127 || letra>= 65 && letra<=90|| letra>=48 && letra<=57 || letra==95)
		if(letra!=dato)
			flag=1;
	return flag;
}

