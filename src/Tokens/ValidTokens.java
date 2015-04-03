package Tokens;

public interface ValidTokens {
	
	/*
	 * Estas cadenas solo sirve para aquellos tokens cuyo contenido sea mayor o 
	 * igual a dos carácteres, sin importar el tipo de token que sean
	 * */
	String[] stringTokens = {
			"Inicio-de-programa", "Fin-de-programa", "Leer", "Escribir", "Si", 
			"Entonces", "Mientras", "Inicio", "Fin","Identificador", ">=", "<=", "==", "!="
	};	
	/*
	 * Estos carácteres solo sirven para aquellos tokens cuyo contenido sea
	 * igual a un carácter 
	 * */
	String charTokens = "+-*/%><_:";
	
	/*
	 * Estos son los tipos de tokens que se puede encontrar durante el proceso,
	 * se separan en dos arreglos para diferenciar a los del stringTokens y
	 * charTokens
	 * */
	TokenType[][] tokenType = { {
			TokenType.INICIO_PROGRAMA, TokenType.FIN_PROGRAMA, TokenType.LEER,
			TokenType.ESCRIBIR, TokenType.SI, TokenType.ENTONCES, TokenType.MIENTRAS,
			TokenType.INICIO, TokenType.FIN, TokenType.OPERADOR_RELACIONAL, 
			TokenType.OPERADOR_RELACIONAL, TokenType.OPERADOR_RELACIONAL, 
			TokenType.OPERADOR_RELACIONAL
		},
		{
			TokenType.OPERADOR_ARITMETICO, TokenType.OPERADOR_ARITMETICO, TokenType.OPERADOR_ARITMETICO,
			TokenType.OPERADOR_ARITMETICO, TokenType.OPERADOR_ARITMETICO, TokenType.OPERADOR_RELACIONAL,
			TokenType.OPERADOR_RELACIONAL, TokenType.GUION_BAJO,TokenType.DOS_PUNTOS, TokenType.COMENTARIO
	} };
	
	TokenType tokenError = TokenType.ERROR;
}
