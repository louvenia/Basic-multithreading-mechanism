package ex01;

public class Program {
    public static void main(String[] args) {
        int number = 0;
        Program p = new Program();

        p.validationArguments(args);
        try {
            number = Integer.parseInt(args[0].replace("--count=", ""));
        } catch (NumberFormatException error) {
            error.printStackTrace();
        }

        if (number > 0) {
            Data dt = new Data();
            Thread thread1 = new Thread(new Egg(number, dt));
            Thread thread2 = new Thread(new Hen(number, dt));

            thread1.start();
            thread2.start();
        } else {
            p.errorMessage("The number of answers must be positive and greater than zero");
        }
    }

    private void validationArguments(String[] args) {
        if(args.length != 1) {
            errorMessage("Invalid number of elements");
        } else if(!args[0].startsWith("--count=")) {
            errorMessage("Invalid program argument entry. Example: --count=n (where n is the number of answers)");
        }
    }

    private void errorMessage(String msg) {
        System.err.println(msg);
        System.exit(-1);
    }
}
