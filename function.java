public class function {
    public static void main(String args[]) {

        function func = new function();
        System.out.println(func.mulNum(1, 2));
        System.out.println(func.devNum(1, 2));
        System.out.println(func.minNum(1, 2));

    }


    static void sayHello() {
        System.out.println("Hello World");


    }

    public int mulNum(int num1, int num2) {
        return num1 * num2;
    }

    public int devNum(int num1, int num2) {
        return num1 / num2;
    }

    public int minNum(int num1, int num2) {
        return num1 - num2;
    }

}