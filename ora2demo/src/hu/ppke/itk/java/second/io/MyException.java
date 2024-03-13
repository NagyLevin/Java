package hu.ppke.itk.java.second.io;

class MyException extends Exception {


    public MyException(String myException) {
        System.out.println(myException);

    }
}
class ExceptionTest1 {
    public static void main(String[] args) {
        try {
            throw new Exception("Exception");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            throw new MyException("MyException");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}