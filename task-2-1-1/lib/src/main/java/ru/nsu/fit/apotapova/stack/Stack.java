package ru.nsu.fit.apotapova.stack;


public class Stack {
    int size;
    private Object stack[]=new Object[size];
    private int top=-1;

    public Stack() {
    }

    void push(Object element)
    {
        stack[++top]=element;
    }
    void pushStack(Object [] elements)
    {
        int n=elements.length;
        while (--n!=0) stack[++top]=elements;
    }
    Object pop()
    {
        return stack[top--];
    }
    Stack pushStack(int quantity)
    {
        Stack container = new Stack();
        Object cont[]=new Object[quantity];
        int i=0;
        while (--quantity!=0)
        {
            conteiner.stack[container.top++]=stack[--top];
        }
        return container;
    }
    int count()
    {
        return top;
    }
}