grammar MiniJava;

program:
    mainClass (classDeclaration)*;

mainClass:
    'public'? 'class' Identifier '{' 'public' 'static' 'void' 'main' '(' 'String' '[' ']' Identifier ')' '{' statement '}' '}';

classDeclaration:
    'public'? 'class' Identifier ('extends' Identifier)? '{' (varDeclaration)* (methodDeclaration)* '}';

varDeclaration:
    type Identifier ';';

type:
    'int' '[' ']'
    | 'boolean'
    | 'int'
    | Identifier;

methodDeclaration:
    'public' type Identifier '(' (type Identifier (',' type Identifier)*)? ')' '{' (varDeclaration)* (statement)* 'return' expression ';' '}';

statement:
    '{' (statement)* '}'
    | 'if' '(' expression ')' statement 'else' statement
    | 'while' '(' expression ')' statement
    | 'System.out.println' '(' expression? ')' ';'
    | Identifier '=' expression ';'
    | Identifier '[' expression ']' '=' expression ';';

expression:
    expression ('&&' | '<' | '+' | '-' | '*') expression
    | expression '[' expression ']'
    | expression '.' 'length'
    | expression '.' Identifier '(' (expression (',' expression)*)? ')'
    | 'new' 'int' '[' expression ']'
    | 'new' Identifier '(' ')'
    | '!' expression
    | '(' expression ')'
    | NUMBER
    | 'true'
    | 'false'
    | Identifier
    | 'this';

Identifier:
    [a-zA-Z] ([a-zA-Z0-9_])*;

NUMBER:
    '-'? INT ('.' [0-9] +)?;

fragment INT:
    '0' | [1-9] [0-9]*;

WS:
    [ \t\n\r] + -> skip;