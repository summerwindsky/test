package jdk.memory;

public class IntrospectorTest {
    private static class ObjectC {  
        ObjectD[] array = new ObjectD[2];  
    }  

    private static class ObjectD {  
        int value;  
    }  
    
    public static void main(String[] args) throws IllegalAccessException {
        final ClassIntrospector ci = new ClassIntrospector();
        ObjectC objectC = new ObjectC();
        objectC.array = new ObjectD[]{new ObjectD()};
        ObjectInfo res = ci.introspect(objectC);
        System.out.println( res.getDeepSize() );
    }
}