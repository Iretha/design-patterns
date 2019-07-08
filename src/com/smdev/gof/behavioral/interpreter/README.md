# The Interpreter Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/interpreter) 

## What problems does it solve? Why to use it?
The interpreter pattern is a design pattern that specifies how to evaluate sentences in some custom language. 
The basic idea is to have a class for each symbol. The symbols are separated by another symbol i.e. "one plus two multiplied-by three".
The syntax tree of a sentence in the language can be represented via the composite pattern.

Glossary:
- Grammar - syntax or set of rules of the language
- Expression - the expression you want to interpret
- Context - the common data, shared between expressions
- Non-terminal expression - may contain other non-terminal expression, you can recursively evaluate them
- Terminal expression - the last expression that will return the result
- Every symbol extends expression - you can separate the symbols with space or another delimiter (one plus two multiplied-by three)
- Infix - when the operators are between the left and the right expression: 5 + 1
- Postfix - the operator follows the left and the right expression: 5 1 +
- Prefix - the operator precedes the left and the right expression + 5 1

## When to use it?
- To build a simple grammar as an abstract syntax tree like structure and run the evaluation
- To produce different outputs (like file formats etc)
- To create simple language processors
- To create simple domain-specific search engines

## Pros:
- You can define implementation for a language with simple grammar (syntax or rules)

## Cons:
- Requires a lot of validations and error checking
- Not good for complex grammar, because it gets very hard to maintain

## Examples from Java API
JVM have both compiler and interpreter. Because the compiler compiles the code and generates byte code. 
After that the interpreter converts byte code to machine understandable code. 

Recognizable by behavioral methods returning a structurally different instance/type of the given 
instance/type; note that parsing/formatting is not part of the pattern, determining the pattern and how to apply it is
```
java.util.Pattern
java.text.Normalizer
All subclasses of java.text.Format
All subclasses of javax.el.ELResolver
```
## Examples
### Example 1 - How to implement it?
We are going to implement a domain-specific search engine.
